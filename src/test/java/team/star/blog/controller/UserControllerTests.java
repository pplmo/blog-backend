package team.star.blog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;
import team.star.blog.service.UserService;

import java.util.List;

/**
 * @author mystic
 */
@WebFluxTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private WebTestClient client;
    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        User u1 = new User();
        User u2 = new User();
        u1.setId(1);
        u2.setId(2);
        u1.setName("cc");
        Mockito.when(userService.findAll()).thenReturn(Flux.fromIterable(List.of(u1, u2)));
        Mockito.when(userService.findById(Mockito.anyInt())).thenReturn(Mono.just(u1));
    }

    @Test
    void getUserById() {
        client.get().uri("/user/1").exchange()
                .expectStatus().isOk()
                .expectBody(User.class);
    }
}
