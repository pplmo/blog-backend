package team.star.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * @author mystic
 * @date 2019-12-15 19:43
 */
@Data
@Builder(toBuilder = true)
@JsonDeserialize(builder = User.UserBuilder.class)
@Table("t_user")
public class User {
    @Id
    private int id;
    /**
     * user name, uniqueness
     */
    @NotEmpty
    private String name;
    /**
     * user password
     */
    private String password;
    /**
     * user mail, uniqueness
     */
    @Email
    @NotEmpty
    private String mail;
    /**
     * user homepage
     */
    @URL
    private String url;
    /**
     * user screen name, i.e. nickname
     */
    private String screenName;
    /**
     * user account created time
     */
    @PastOrPresent
    @CreatedDate
    private Instant created;
    /**
     * last active time
     */
    @PastOrPresent
    private Instant activated;
    /**
     * last login time
     */
    @Past
    @LastModifiedDate
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
