package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.Query;
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
    @Query("SELECT * FROM t_user WHERE name = :name")
    Mono<User> findUserByName(String name);

    /**
     * delete user by name
     * @param name username
     * @return delete
     */
    @Query("DELETE FROM t_user WHERE name = :name")
    Mono<Void> deleteUserByName(String name);

}
