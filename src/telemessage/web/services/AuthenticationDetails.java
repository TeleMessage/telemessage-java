package telemessage.web.services;

import com.mikerusoft.jsonable.annotations.JsonClass;
import com.mikerusoft.jsonable.annotations.JsonField;

/**
 * @author mikhail
 * @since Feb 25, 2010 3:43:12 PM
 */
@JsonClass
public class AuthenticationDetails {
    @JsonField private String username;
    @JsonField private String password;

    public AuthenticationDetails() {
    }


    public AuthenticationDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}