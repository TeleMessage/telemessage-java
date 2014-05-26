package telemessage.web.services;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

/**
 * @author mikhail
 * @since Feb 25, 2010 3:43:12 PM
 */
@RestClass
public class AuthenticationDetails {
    @RestField private String username;
    @RestField private String password;

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