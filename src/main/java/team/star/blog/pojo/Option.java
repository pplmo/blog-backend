package team.star.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

/**
 * @author mystic
 * @date 2019-12-15 19:44
 */
@Data
@Builder(toBuilder = true)
@JsonDeserialize(builder = Option.OptionBuilder.class)
public class Option {
    private int id;
    private String name;
    private int userId;
    private String value;
}
