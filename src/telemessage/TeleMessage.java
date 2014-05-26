package telemessage;

import org.apache.http.message.BasicNameValuePair;
import org.simpleframework.xml.core.Persister;
import telemessage.converters.xml.MessageConverter;
import telemessage.rest.transform.TransformerFactory;
import telemessage.web.services.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Grinfeld Mikhail
 * @since 5/26/2014.
 */
public class TeleMessage {

    public static final String XML_PATH = "https://secure.telemessage.com/partners/xmlMessage.jsp";
    public static final String REST_PATH_SEND = "https://secure.telemessage.com/rest/message/send/";
    public static final String REST_PATH_STATUS = "https://secure.telemessage.com/rest/message/status/";
    public static final String HTTPS_PATH = "https://secure.telemessage.com/jsp/receiveSMS.jsp";

    public enum Interface {
        XML(XML_PATH, XML_PATH),
        REST(REST_PATH_SEND, REST_PATH_STATUS),
        HTTPS(HTTPS_PATH,null);

        private String send;
        private String status;

        Interface(String send, String status) {
            this.send = send;
            this.status = status;
        }
    }

    Message message = new Message();
    List<FileMessage> fileMessages = new ArrayList<FileMessage>();
    List<Recipient> recipients = new ArrayList<Recipient>();

    public TeleMessage setSubject(String subject) {
        message.setSubject(subject);
        return this;
    }

    public TeleMessage setText(String text) {
        message.setTextMessage(text);
        return this;
    }

    /**
     *
     * @param date to send message at. Date must be in GMT+0
     * @return TeleMessage
     */
    public TeleMessage setScheduled(Date date) {
        message.setSchedule(new Schedule(date.getTime(), 0));
        return this;
    }

    public TeleMessage addRecipient(Recipient r) {
        recipients.add(r);
        return this;
    }

    public TeleMessage removeRecipient(Recipient r) {
        recipients.remove(r);
        return this;
    }

    public TeleMessage addRecipients(List<Recipient> rs) {
        recipients.addAll(rs);
        return this;
    }

    public TeleMessage clearRecipients() {
        recipients.clear();
        return this;
    }


    public TeleMessage addFileMessage(FileMessage r) {
        fileMessages.add(r);
        return this;
    }

    public TeleMessage removeFileMessage(FileMessage r) {
        fileMessages.remove(r);
        return this;
    }

    public TeleMessage addFileMessages(List<FileMessage> rs) {
        fileMessages.addAll(rs);
        return this;
    }

    public TeleMessage clearFileMessages() {
        fileMessages.clear();
        return this;
    }

    public String getSendURL(Interface i) {
        return i.send;
    }

    public String getStatusURL(Interface i) {
        return i.status;
    }

    public Result write(String username, String password, Interface i, OutputStream out) throws IOException {
        Result res = new Result(false);
        prepareMessage();
        switch (i) {
            case XML:
                writeXML(new AuthenticationDetails(username, password), out);
                res.result = true;
                break;
            case REST:
                writeRest(new AuthenticationDetails(username, password), out);
                res.result = true;
                break;
            case HTTPS:
                res = writeHTTPS(new AuthenticationDetails(username, password));
                res.result = true;
                break;
        }
        message.setRecipients(null);
        message.setFileMessages(null);
        return res;
    }

    private void prepareMessage() {
       message.setRecipients(recipients.toArray(new Recipient[recipients.size()]));
       message.setFileMessages(fileMessages.toArray(new FileMessage[fileMessages.size()]));
    }

    private void writeXML(AuthenticationDetails auth, OutputStream out) throws IOException {
        try {
            new Persister().write(new MessageConverter().convert(new telemessage.web.xml.TeleMessage(auth, message)), out);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private void writeRest(AuthenticationDetails auth, OutputStream out) throws IOException {
        try {
            Object tr = new Object[] {auth, message};
            TransformerFactory.get(tr).transform(tr, out);
        } catch (IOException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw new IOException(e);
        }
    }

    private URLResult writeHTTPS(AuthenticationDetails auth) throws IOException {
        URLResult res = new URLResult(false);
        res.params = new BasicNameValuePair[] {
                new BasicNameValuePair("userid", auth.getUsername()),
                new BasicNameValuePair("password", auth.getPassword()),
                new BasicNameValuePair("to", message.getRecipients()[0].getValue()), //each request supports a single recipient
                new BasicNameValuePair("text", message.getTextMessage())
            };
        return res;
    }

    public static class Result {
        boolean result;

        public Result(boolean result) {
            this.result = result;
        }

        public boolean isResult() { return result; }
    }

    public static class URLResult extends Result {
        BasicNameValuePair[] params;

        public URLResult(boolean result) {
            super(result);
        }

        public BasicNameValuePair[] getParams() {
            return params;
        }
    }

}
