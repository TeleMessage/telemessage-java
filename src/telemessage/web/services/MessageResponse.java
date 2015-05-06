package telemessage.web.services;


import com.mikerusoft.jsonable.annotations.JsonClass;
import com.mikerusoft.jsonable.annotations.JsonField;

/**
 * TeleMessage Response for sending messages
 * @author Grinfeld Mikhail
 * @since Feb 28, 2010 11:45:17 AM
 */
@JsonClass
public class MessageResponse extends Response {

    @JsonField private long messageID;
    @JsonField private String messageKey;

    public MessageResponse(int resultCode, String resultDescription, long messageID, String messageKey) {
        super(resultCode, resultDescription);
        this.messageID = messageID;
        this.messageKey = messageKey;
    }

    public MessageResponse(Response response) {
        super(response.getResultCode(), response.getResultDescription());
    }

    protected void init(int resultCode, String resultDescription, long messageID, String messageKey) {
        super.init(resultCode, resultDescription);
        this.messageID = messageID;
        this.messageKey = messageKey;
    }

    public MessageResponse(int resultCode, String resultDescription) {
        this(resultCode, resultDescription, -1, null);
    }

    public MessageResponse() {
        this(-1, "", -1L, null);
    }

    public long getMessageID() { return messageID; }
    public void setMessageID(long messageID) { this.messageID = messageID; }
    public String getMessageKey() { return messageKey; }
    public void setMessageKey(String messageKey) { this.messageKey = messageKey; }
}