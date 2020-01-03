package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import team.star.blog.pojo.Content;

/**
 * @author mystic
 */
@Repository
public interface ContentRepository extends R2dbcRepository<Content, Integer> {
}
