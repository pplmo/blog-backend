package team.star.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;
import team.star.blog.service.UserService;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PatchMapping
    public Mono<User> updateUser(@RequestBody User user) {
        return userService.save(user);
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
