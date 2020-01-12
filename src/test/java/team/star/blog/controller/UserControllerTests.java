package team.star.blog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
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
public class UserControllerTests {
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
                .build();

        User u1 = User.builder().id(1).name("Ran").build();
        User u2 = User.builder().id(2).name("Mystic").build();

        when(userService.findAll()).thenReturn(Flux.fromIterable(List.of(u1, u2)));
        when(userService.findById(Mockito.anyInt())).thenReturn(Mono.just(u1));
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
        client.get().uri("/user/").exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class).hasSize(2)
                .consumeWith(document("findAllUsers"));
    }
}
