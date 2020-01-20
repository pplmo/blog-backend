package team.star.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author mystic
 * @date 2019-12-15 19:44
 */
@Data
@Builder(toBuilder = true)
@JsonDeserialize(builder = Option.OptionBuilder.class)
@Table("t_option")
public class Option {
    @Id
    private int id;
    private String name;
    private int userId;
    private String value;
}
