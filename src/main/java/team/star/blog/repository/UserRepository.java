package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import team.star.blog.pojo.User;

/**
 * @author mystic
 */
@Repository
public interface UserRepository extends R2dbcRepository<User, Integer> {
}
