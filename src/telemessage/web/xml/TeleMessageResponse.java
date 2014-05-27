package telemessage.web.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import telemessage.web.services.MessageResponse;

/**
 * @author Grinfeld Mikhail
 * @since 5/27/2014.
 */
@Root (name = "TELEMESSAGE")
public class TeleMessageResponse {

    public MessageResponse convert() {
        TeleMessageResponse.TeleMessageContent.Response r = this.teleMessageContent.response;
        MessageResponse resp = new MessageResponse();
        resp.setResultCode(r.status);
        resp.setResultDescription(r.desc);
        resp.setMessageID(r.messageId);
        resp.setMessageKey(r.messageKey);
        return resp;
    }
    @Element (name = "VERSION", required = false) int version;
    @Element (name = "TELEMESSAGE_CONTENT") TeleMessageContent teleMessageContent;

    static class TeleMessageContent {
        @Element (name = "RESPONSE") Response response;

        static class Response {
            @Element (name = "MESSAGE_ID", required = false) long messageId;
            @Element (name = "MESSAGE_KEY", required = false) String messageKey;
            @Element (name = "RESPONSE_STATUS", required = false) int status;
            @Element (name = "RESPONSE_STATUS_DESC", required = false) String desc;
        }

    }
}