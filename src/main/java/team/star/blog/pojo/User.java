package team.star.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * @author mystic
 * @date 2019-12-15 19:43
 */
@Data
@Builder(toBuilder = true)
@JsonDeserialize(builder = User.UserBuilder.class)
public class User {
    private int id;
    /**
     * user name, uniqueness
     */
    private String name;
    /**
     * user password
     */
    private String password;
    /**
     * user mail, uniqueness
     */
    private String mail;
    /**
     * user homepage
     */
    private String url;
    /**
     * user screen name, i.e. nickname
     */
    private String screenName;
    /**
     * user account created time
     */
    private Instant created;
    /**
     * last active time
     */
    private Instant activated;
    /**
     * last login time
     */
    private Instant logged;
    /**
     * user group
     */
    private String groupName;
    /**
     * user authentication code
     */
    private String authCode;
}
