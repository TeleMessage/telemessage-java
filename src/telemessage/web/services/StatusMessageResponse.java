package telemessage.web.services;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

/**
 * @author Grinfeld Mikhail
 * @since Mar 1, 2010 2:48:39 PM
 */
@RestClass
public class StatusMessageResponse extends MessageResponse {
    @RestField private RecipientStatus[] recipients;

    public StatusMessageResponse(int resultCode, String resultDescription, long messageID, String messageKey) {
        super(resultCode, resultDescription, messageID, messageKey);
    }

    public StatusMessageResponse(int resultCode, String resultDescription, long messageID, String messageKey, RecipientStatus recipients[]) {
        super(resultCode, resultDescription, messageID, messageKey);
        this.recipients = recipients;
    }

    public StatusMessageResponse() {}

    public RecipientStatus[] getRecipients() { return recipients; }
    public void setRecipients(RecipientStatus[] recipients) { this.recipients = recipients; }
}