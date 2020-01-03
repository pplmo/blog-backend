package team.star.blog.service;

import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;

/**
 * @author mystic
 */
public interface UserService extends R2dbcService<User, Integer> {
    /**
     * find user by name
     *
     * @param name name
     * @return User
     */
    Mono<User> findByName(String name);

    /**
     * delete user by username
     *
     * @param name name
     * @return the result of delete
     */
    Mono<Void> deleteByName(String name);
}
