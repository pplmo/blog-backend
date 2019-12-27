package team.star.blog.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author mystic
 * @date 2019-12-15 19:45
 */
@Data
@Table("t_metadata")
public class Metadata {
    @Id
    private int id;
    private String name;
    private String slug;
    private String type;
    private String description;
    private int count;
    private int order;
}
