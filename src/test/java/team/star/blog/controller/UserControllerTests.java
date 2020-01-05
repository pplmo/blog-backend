package team.star.blog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
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
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        User u1 = new User();
        User u2 = new User();
        u1.setId(1);
        u2.setId(2);
        Mono.when(userService.findAll()).thenReturn(List.of(u1, u2)).block();
    }

    @Test
    void getAllUsers() {
        client.get().uri("/user/").exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class).hasSize(2);
    }
}
