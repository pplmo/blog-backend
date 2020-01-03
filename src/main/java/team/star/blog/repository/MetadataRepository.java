package team.star.blog.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import team.star.blog.pojo.Metadata;

/**
 * @author mystic
 */
@Repository
public interface MetadataRepository extends R2dbcRepository<Metadata, Integer> {
}
