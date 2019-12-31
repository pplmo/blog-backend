package team.star.blog.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author mystic
 * @date 2019-12-15 19:44
 */
@Data
@Table("t_relationship")
public class Relationship {
    @Id
    private int contentId;
    @Id
    private int metadataId;
}
