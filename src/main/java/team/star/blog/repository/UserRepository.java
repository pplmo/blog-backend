package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;

/**
 * @author mystic
 */
@Repository
public interface UserRepository extends R2dbcRepository<User, Integer> {
    /**
     * find User by name
     * @param name username
     * @return User
     */
    Mono<User> findUserByName(String name);

    /**
     * delete user by name
     * @param name username
     * @return delete
     */
    Mono<Void> deleteUserByName(String name);

}
