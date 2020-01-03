package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import team.star.blog.pojo.Option;

/**
 * @author mystic
 */
@Repository
public interface OptionRepository extends R2dbcRepository<Option, Integer> {
}
