package telemessage.web.services;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

/**
 * @author Grinfeld Mikhail
 * @since Mar 1, 2010 2:53:05 PM
 */
@RestClass
public class RecipientStatus {
    @RestField private Recipient recipient;
    @RestField private int status;
    @RestField private String description;
    @RestField private long statusDate;

    public RecipientStatus() {}

    public RecipientStatus(Recipient recipient, int status, String description, long statusDate) {
        this.recipient = recipient;
        this.status = status;
        this.description = description;
        this.statusDate = statusDate;
    }

    public Recipient getRecipient() { return recipient; }
    public void setRecipient(Recipient recipient) { this.recipient = recipient; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public long getStatusDate() { return statusDate; }
    public void setStatusDate(long statusDate) { this.statusDate = statusDate; }
}