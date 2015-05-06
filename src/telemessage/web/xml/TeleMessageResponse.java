package telemessage.web.xml;

import telemessage.web.services.Recipient;
import telemessage.web.services.RecipientStatus;
import telemessage.web.services.StatusMessageResponse;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import telemessage.web.services.MessageResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Grinfeld Mikhail
 * @since 5/27/2014.
 */
@Root (name = "TELEMESSAGE")
public class TeleMessageResponse {

    public MessageResponse convert() {
        if (this.teleMessageContent != null) {
            return this.teleMessageContent.convert();
        }
        return null;
    }

    @Element (name = "VERSION", required = false) int version;
    @Element (name = "TELEMESSAGE_CONTENT") TeleMessageContent teleMessageContent;

    static class TeleMessageContent {
        @Element (name = "RESPONSE", required = false) Response response;
        @Element (name = "MESSAGE_STATUS", required = false) MessageStatus status;

        public MessageResponse convert() {
            if (status != null ) {
                return status.convert();
            } else if (response != null) {
                return response.convert();
            }
            return null;
        }

        static class Response {

            public MessageResponse convert() {
                MessageResponse resp = new MessageResponse();
                resp.setResultCode(this.status);
                resp.setResultDescription(this.desc);
                resp.setMessageID(this.messageId);
                resp.setMessageKey(this.messageKey);
                return resp;
            }

            @Element (name = "MESSAGE_ID", required = false) long messageId;
            @Element (name = "MESSAGE_KEY", required = false) String messageKey;
            @Element (name = "RESPONSE_STATUS", required = false) int status;
            @Element (name = "RESPONSE_STATUS_DESC", required = false) String desc;
        }

        static class MessageStatus {
            public StatusMessageResponse convert() {
                StatusMessageResponse resp = new StatusMessageResponse();
                resp.setResultCode(this.statusId);
                resp.setResultDescription(this.statusDescription);
                resp.setMessageID(this.messageId);
                resp.setMessageKey(this.messageKey);
                if (recipientStatuses != null && recipientStatuses.size() > 0) {
                    telemessage.web.services.RecipientStatus[] statuses = new telemessage.web.services.RecipientStatus[recipientStatuses.size()];
                    int i = 0;
                    for (RecipientStatus r : recipientStatuses) {
                        statuses[i] = r.convert();
                        i++;
                    }
                    resp.setRecipients(statuses);
                }
                return resp;
            }

            @Element (name = "STATUS_ID") int statusId;
            @Element (name = "STATUS_DESCRIPTION") String statusDescription;
            @Element (name = "MESSAGE_ID") long messageId;
            @Element (name = "MESSAGE_KEY", required = false) String messageKey;
            @ElementList(inline = true, required = false) List<RecipientStatus> recipientStatuses;
        }

        @Root (name = "RECIPIENT_STATUS")
        static class RecipientStatus {

            telemessage.web.services.RecipientStatus convert() {
                telemessage.web.services.RecipientStatus rs = new telemessage.web.services.RecipientStatus();
                Recipient r = new Recipient();
                r.setValue(device.value);
                r.setDescription(this.recipientName);
                r.setType(Recipient.Type.valueOf(Recipient.Type.name(device.type)));
                rs.setStatus(device.status);
                SimpleDateFormat dt = new SimpleDateFormat("yyyyymmdd hh:mm:ss");
                if (device.status_date != null && device.status_date.length() > 0) {
                    try {
                        rs.setStatusDate(dt.parse(device.status_date).getTime());
                    } catch (ParseException ignore) {
                    }
                }
                rs.setDescription(device.description);
                rs.setRecipient(r);
                return rs;
            }

            @Element (name = "RECIPIENT_NAME") String recipientName;
            @Element (name = "DEVICE") Device device;
        }

        static class Device {
            @Element (name = "ID", required = false) String id;
            @Element (name = "TYPE") int type;
            @Element (name = "VALUE") String value;
            @Element (name = "STATUS") int status;
            @Element (name = "DESCRIPTION", required = false) String description;
            @Element (name = "STATUS_DATE") String status_date;
            @Element (name = "ANSWER", required = false) String answer;
        }

    }
}