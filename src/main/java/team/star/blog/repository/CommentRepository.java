package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import team.star.blog.pojo.Comment;

/**
 * @author mystic
 */
@Repository
public interface CommentRepository extends R2dbcRepository<Comment, Integer> {
}
