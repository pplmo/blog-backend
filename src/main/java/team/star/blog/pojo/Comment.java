package team.star.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * @author mystic
 * @date 2019-12-15 19:45
 */
@Data
@Builder(toBuilder = true)
@JsonDeserialize(builder = Comment.CommentBuilder.class)
@Table("t_comment")
public class Comment {
    @Id
    private int id;
    private int contentId;
    private Instant created;
    private int commentAuthorId;
    private String mail;
    private String url;
    private String ip;
    private String agent;
    private String text;
    private String type;
    private String status;
    private int parentCommentId;
}
