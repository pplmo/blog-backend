package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import team.star.blog.pojo.Relationship;

/**
 * @author mystic
 */
@Repository
public interface RelationshipRepository extends R2dbcRepository<Relationship, Integer> {
}
