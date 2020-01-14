package team.star.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;
import team.star.blog.service.UserService;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping
    public Mono<User> updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/{id}")
    public Mono<User> findUserById(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping
    public Flux<User> findAllUsers() {
        return userService.findAll();
    }
}
