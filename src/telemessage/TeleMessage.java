package telemessage;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.core.Persister;
import telemessage.converters.xml.MessageConverter;
import telemessage.rest.reader.JsonReader;
import telemessage.rest.transform.TransformerFactory;
import telemessage.web.services.*;
import telemessage.web.xml.TeleMessageResponse;

import java.io.*;
import java.util.*;

/**
 * This class represents container for message to be send via TeleMessage.
 * Class wraps message and converts it to chosen format, i.e. XML, JSON or URL
 * @author Grinfeld Mikhail
 * @since 5/26/2014.
 */
public class TeleMessage {

    private static final String XML_PATH = "https://secure.telemessage.com/partners/xmlMessage.jsp";
    private static final String JSON_PATH_SEND = "https://rest.telemessage.com/rest/message/send/";
    private static final String JSON_PATH_STATUS = "https://rest.telemessage.com/rest/message/status/";
    private static final String SEND_TO_URL_PATH = "https://secure.telemessage.com/jsp/receiveSMS.jsp";

    /**
     * Supported
     */
    public enum Interface {
        XML(XML_PATH, XML_PATH),
        JSON(JSON_PATH_SEND, JSON_PATH_STATUS),
        SEND_TO_URL(SEND_TO_URL_PATH,null);

        private String send;
        private String status;

        Interface(String send, String status) {
            this.send = send;
            this.status = status;
        }
    }

    Message message = new Message();
    Map<String, FileMessage> fileMessages = new HashMap<String, FileMessage>();
    List<Recipient> recipients = new ArrayList<Recipient>();

    /**
     * Sets subject
     * @param subject for message
     * @return TeleMessage
     */
    public TeleMessage setSubject(String subject) {
        message.setSubject(subject);
        return this;
    }

    /**
     * sets textual message
     * @param text to be sent
     * @return TeleMessage
     */
    public TeleMessage setText(String text) {
        message.setTextMessage(text);
        return this;
    }

    /**
     * set schedule for sending message. Date must be in GMT+0
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
        fileMessages.put(r.getFileName(), r);
        return this;
    }

    public TeleMessage removeFileMessage(FileMessage r) {
        fileMessages.remove(r.getFileName());
        return this;
    }

    public TeleMessage clearFileMessages() {
        fileMessages.clear();
        return this;
    }

    /**
     * returns URL for sending message according to {@link Interface}
     * @param i interface to use
     * @return URL for sending message
     */
    public String getSendURL(Interface i) {
        return i.send;
    }

    /**
     * returns URL for receiving message status according to {@link Interface}
     * <strong>Note:</strong> will be implemented soon
     * @param i interface to use
     * @return URL for receiving message status
     */
    public String getStatusURL(Interface i) {
        return i.status;
    }

    /**
     * Prepares message to be sent. There are 3 options (indicated by anInterface {@link Interface} parameter):
     * 1. XML - writes xml into OutputStream out
     * 2. JSON - writes json into
     * 3. SEND_TO_URL - populate and return result with Map of parameters to be sent over URL. This options doesn't support multi recipients and files
     * @param username TeleMessage username
     * @param password TeleMessage password
     * @param anInterface interface to prepare message for
     * @param out output stream to write into
     * @return result
     * @throws IOException if any IO exception thrown
     * @throws IllegalArgumentException if message is absence some data
     */
    public Result writeSend(String username, String password, Interface anInterface, OutputStream out) throws IOException, IllegalArgumentException {
        Result res = new Result(false);
        AuthenticationDetails auth = new AuthenticationDetails(username, password);
        validateBeforeSend(auth);
        prepareMessage();
        switch (anInterface) {
            case XML:
                writeSendXML(auth, out);
                res.success = true;
                break;
            case JSON:
                writeSendJson(auth, out);
                res.success = true;
                break;
            case SEND_TO_URL:
                res = writeSendToURL(auth);
                res.success = true;
                break;
        }
        message.setRecipients(null);
        message.setFileMessages(null);
        return res;
    }

    /**
     * Reads TeleMessage immediate response after sending message
     * @param response from TeleMessage
     * @param anInterface interface to read response from
     * @return TeleMessage immediate response after sending message
     * @throws Exception while parsing response error
     */
    public MessageResponse readSendResponse(String response, Interface anInterface) throws Exception {
        MessageResponse resp = null;
        switch (anInterface) {
            case XML:
                return readXMLSendResponse(response);
            case JSON:
                return JsonReader.read(new ByteArrayInputStream(response.getBytes()), MessageResponse.class);
            case SEND_TO_URL:
                break;
        }
        return resp;
    }

    /**
     * Reads TeleMessage immediate response after sending message
     * @param response from TeleMessage
     * @param anInterface interface to read response from
     * @return TeleMessage immediate response after sending message
     * @throws Exception while parsing response error
     */
    public MessageResponse readSendResponse(InputStream response, Interface anInterface) throws Exception {
        MessageResponse resp = null;
        switch (anInterface) {
            case XML:
                return readXMLSendResponse(response);
            case JSON:
                return JsonReader.read(response, MessageResponse.class);
            case SEND_TO_URL:
                break;
        }
        return resp;
    }

    public MessageResponse readXMLSendResponse(String response) throws Exception {
        return (new Persister().read(TeleMessageResponse.class, response)).convert();
    }

    public MessageResponse readXMLSendResponse(InputStream response) throws Exception {
        return (new Persister().read(TeleMessageResponse.class, response)).convert();
    }

    /**
     * Clears any stored data
     */
    public void clearMessage() {
        message = new Message();
        recipients = new ArrayList<Recipient>();
        fileMessages = new HashMap<String, FileMessage>();
    }


    private void validateBeforeSend(AuthenticationDetails auth) throws IllegalArgumentException {
        if (auth == null)
            throw new IllegalArgumentException("Authentication details couldn't be empty");
        if (StringUtils.isEmpty(auth.getUsername()))
            throw new IllegalArgumentException("User name required");
        if (StringUtils.isEmpty(auth.getPassword()))
            throw new IllegalArgumentException("Password required");
        if ( (fileMessages == null || fileMessages.size() <=0) && StringUtils.isEmpty(message.getTextMessage()))
            throw new IllegalArgumentException("Message could not be empty");
        if (recipients == null || recipients.size() <= 0) {
            throw new IllegalArgumentException("You need at least one recipient");
        }
    }

    private void prepareMessage() throws IOException {
        message.setRecipients(recipients.toArray(new Recipient[recipients.size()]));
        if (fileMessages != null) {
            telemessage.web.services.FileMessage[] files = new telemessage.web.services.FileMessage[fileMessages.size()];
            int i=0;
            for (FileMessage f : fileMessages.values()) {
                files[i] = new telemessage.web.services.FileMessage(f.getMimeType(),
                        Base64.encodeBase64String(org.apache.commons.io.IOUtils.toByteArray(f.getInputStream())),
                        f.getFileName());
                i++;
            }
            message.setFileMessages(files);
        }

    }

    private void writeSendXML(AuthenticationDetails auth, OutputStream out) throws IOException {
        try {
            new Persister().write(new MessageConverter().convert(new telemessage.web.xml.TeleMessage(auth, message)), out);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private void writeSendJson(AuthenticationDetails auth, OutputStream out) throws IOException {
        try {
            Object tr = new Object[] {auth, message};
            TransformerFactory.get(tr).transform(tr, out);
        } catch (IOException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw new IOException(e);
        }
    }

    private URLResult writeSendToURL(AuthenticationDetails auth) throws IOException, IllegalArgumentException {
        if (StringUtils.isEmpty(message.getTextMessage()))
            throw new IllegalArgumentException("Message could not be empty");
        URLResult res = new URLResult(false);
        res.addParam("userid", auth.getUsername());
        res.addParam("password", auth.getPassword());
        res.addParam("to", message.getRecipients()[0].getValue()); //each request supports a single recipient
        res.addParam("text", message.getTextMessage());
        return res;
    }

}
