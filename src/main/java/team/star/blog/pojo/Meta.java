package team.star.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

/**
 * @author mystic
 * @date 2019-12-15 19:45
 */
@Data
@Builder(toBuilder = true)
@JsonDeserialize(builder = Meta.MetaBuilder.class)
public class Meta {
    private int id;
    private String name;
    private String slug;
    private String type;
    private String description;
    private int count;
    private int orderNum;
}
