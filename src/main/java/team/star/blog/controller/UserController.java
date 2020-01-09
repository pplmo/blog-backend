package team.star.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;
import team.star.blog.service.UserService;

import javax.annotation.Resource;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public Mono<User> save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public Mono<User> findUserById(@PathVariable int id) {
        return userService.findById(id);
    }
}