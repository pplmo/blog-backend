package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import team.star.blog.pojo.Meta;

/**
 * @author mystic
 */
@Repository
public interface MetaRepository extends R2dbcRepository<Meta, Integer> {
}
