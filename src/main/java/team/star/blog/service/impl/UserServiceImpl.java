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
    private UserRepository dao;

    @Override
    public Mono<User> save(User user) {
        return null;
    }

    @Override
    public Mono<User> findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Mono<User> findByName(String name) {
        return null;
    }

    @Override
    public Flux<User> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteByName(String name) {
        return null;
    }
}
