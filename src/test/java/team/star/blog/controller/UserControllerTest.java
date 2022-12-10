package team.star.blog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;
import team.star.blog.service.UserService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

/**
 * @author mystic
 */
@WebFluxTest(UserController.class)
@ExtendWith(RestDocumentationExtension.class)
class UserControllerTest {
    @Autowired
    private ApplicationContext context;
    private WebTestClient client;
    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        client = WebTestClient.bindToApplicationContext(context)
            .configureClient()
            .filter(
                documentationConfiguration(provider)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .baseUrl("/api/v1/")
            .build();

        User u1 = User.builder().id(1).name("Mystic").build();
        User u2 = User.builder().id(2).name("Ran").build();

        when(userService.findAll()).thenReturn(Flux.fromIterable(List.of(u1, u2)));
        when(userService.findById(Mockito.anyInt())).thenReturn(Mono.just(u1));
        when(userService.save(Mockito.any(User.class))).thenReturn(Mono.just(u2));
    }

    @Test
    void findUserById() {
        client.get().uri("/user/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBody(User.class)
            .consumeWith(document("findUserById",
                pathParameters(parameterWithName("id").description("User ID"))
            ));

    }

    @Test
    void findAllUsers() {
        client.get().uri("/user").exchange()
            .expectStatus().isOk()
            .expectBodyList(User.class)
            .consumeWith(document("findAllUsers"));
    }

    @Test
    void createUser() {
        User u3 = User.builder().name("cc").build();
        client.post().uri("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(u3), User.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody()
            .jsonPath("$.id").isEqualTo(2)
            .consumeWith(document("createUser"));
    }

    @Test
    void updateUser() {
        User u2 = User.builder().id(2).name("cc").build();
        client.patch().uri("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(u2), User.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.name").isEqualTo("Ran")
            .consumeWith(document("updateUser"));
    }
}
