package telemessage.web.services;


import com.mikerusoft.jsonable.annotations.JsonClass;
import com.mikerusoft.jsonable.annotations.JsonField;

/**
 * @author Grinfeld Mikhail
 * @since Feb 28, 2010 11:53:21 AM
 */
@JsonClass
public class Message {
    @JsonField private String textMessage;
    @JsonField private FileMessage[] fileMessages;
    @JsonField private Property[] properties;
    @JsonField private Recipient[] recipients;
    @JsonField private String subject;
    @JsonField private Schedule schedule;

    public String getTextMessage() { return textMessage; }
    public void setTextMessage(String textMessage) { this.textMessage = textMessage; }
    public FileMessage[] getFileMessages() { return fileMessages; }
    public void setFileMessages(FileMessage[] fileMessages) { this.fileMessages = fileMessages; }
    public Property[] getProperties() { return properties; }
    public void setProperties(Property[] properties) { this.properties = properties; }
    public Recipient[] getRecipients() { return recipients; }
    public void setRecipients(Recipient[] recipients) { this.recipients = recipients; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }
}