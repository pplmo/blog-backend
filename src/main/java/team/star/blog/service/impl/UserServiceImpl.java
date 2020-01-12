package team.star.blog.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;
import team.star.blog.repository.UserRepository;
import team.star.blog.service.UserService;

import javax.annotation.Resource;

/**
 * @author mystic
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> findByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteByName(String name) {
        return userRepository.deleteUserByName(name);
    }
}
