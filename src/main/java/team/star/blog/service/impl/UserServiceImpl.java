package team.star.blog.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import team.star.blog.pojo.User;
import team.star.blog.repository.UserRepository;
import team.star.blog.service.UserService;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * @author mystic
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;


    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user)
                .onErrorResume(err -> userRepository.findUserByName(user.getName())
                        .flatMap(userFoundInDb -> {
                            user.setId(userFoundInDb.getId());
                            return userRepository.save(user);
                        })
                );
    }

    @Override
    public Mono<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Flux<User> fetchByIds(List<Integer> ids) {
        return Flux.fromIterable(ids)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .flatMap(this::findById)
                .ordered(Comparator.comparingInt(User::getId));
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<User> findByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public Mono<Void> deleteByName(String name) {
        return userRepository.deleteUserByName(name);
    }
}
