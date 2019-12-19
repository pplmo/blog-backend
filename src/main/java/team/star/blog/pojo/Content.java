package team.star.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * @author mystic
 * @date 2019-12-15 19:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_content")
public class Content {

    @Id
    private int id;
    /**
     * content title
     */
    private String title;
    /**
     * content slug
     */
    private String slug;
    private Instant created;
    private Instant modified;
    private String text;
    private int order;
    private int authorId;
    private String template;
    private String type;
    private String status;
    private String password;

}
