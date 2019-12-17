package team.star.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * @author mystic
 * @date 2019-12-15 19:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {
    @Id
    private int id;
    private String name;
    private String password;
    private String mail;
    private String url;
    private String screenName;
    private Date created;
    private Date activated;
    private Date logged;
    private String group;
    private String authCode;
}
