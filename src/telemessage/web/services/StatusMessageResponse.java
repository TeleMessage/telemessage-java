package telemessage.web.services;


import com.mikerusoft.jsonable.annotations.JsonClass;
import com.mikerusoft.jsonable.annotations.JsonField;

/**
 * @author Grinfeld Mikhail
 * @since Mar 1, 2010 2:48:39 PM
 */
@JsonClass
public class StatusMessageResponse extends MessageResponse {
    @JsonField
    private RecipientStatus[] recipients;

    public StatusMessageResponse() {}

    public StatusMessageResponse(int resultCode, String resultDescription, long messageID, String messageKey) {
        init(resultCode, resultDescription, messageID, messageKey);
    }

    public StatusMessageResponse(int resultCode, String resultDescription, long messageID, String messageKey, RecipientStatus recipients[]) {
        init(resultCode, resultDescription, messageID, messageKey, recipients);
    }

    public StatusMessageResponse(Response response) {
        super(response);
        if (response instanceof StatusMessageResponse)
            init((StatusMessageResponse)response);
        else if (response instanceof MessageResponse)
            init((MessageResponse)response);

    }

    public void init(int resultCode, String resultDescription, long messageID, String messageKey, RecipientStatus recipients[]) {
        init(resultCode, resultDescription, messageID, messageKey);
        this.recipients = recipients;
    }

    public void init(StatusMessageResponse response) {
        init(response.getResultCode(), response.getResultDescription(), response.getMessageID(), response.getMessageKey(), response.getRecipients());
    }


    public void init(MessageResponse response) {
        init(response.getResultCode(), response.getResultDescription(), response.getMessageID(), response.getMessageKey(), null);
    }


    public RecipientStatus[] getRecipients() { return recipients; }
    public void setRecipients(RecipientStatus[] recipients) { this.recipients = recipients; }
}