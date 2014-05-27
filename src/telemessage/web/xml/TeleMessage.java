package telemessage.web.xml;

import telemessage.web.services.AuthenticationDetails;
import telemessage.web.services.Message;

/**
 * Wrapper for main Element for XML serialization
 *
 * @author Grinfeld Mikhail
 * @since 5/26/2014.
 */
public class TeleMessage {
    AuthenticationDetails authenticationDetails;
    Message message;

    public TeleMessage(AuthenticationDetails authenticationDetails, Message message) {
        this.authenticationDetails = authenticationDetails;
        this.message = message;
    }

    public AuthenticationDetails getAuthenticationDetails() { return authenticationDetails; }
    public void setAuthenticationDetails(AuthenticationDetails authenticationDetails) { this.authenticationDetails = authenticationDetails; }
    public Message getMessage() { return message; }
    public void setMessage(Message message) { this.message = message; }
}
