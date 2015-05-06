package telemessage;

import com.mikerusoft.jsonable.parser.JsonWriter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.core.Persister;
import telemessage.converters.xml.MessageConverter;
import telemessage.web.services.*;
import telemessage.web.xml.TeleMessageResponse;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * This class represents container for message to be send via TeleMessage.
 * Class wraps message and converts it to chosen format, i.e. XML, JSON or URL
 * Receiving message is possible only for XML and JSON
 * @author Grinfeld Mikhail
 * @since 5/26/2014.
 */
public class TeleMessage {

    private static final String XML_PATH = "https://rest.telemessage.com/partners/xmlMessage.jsp";
    private static final String JSON_PATH_SEND = "https://rest.telemessage.com/rest/message/send/";
    private static final String JSON_PATH_STATUS = "https://rest.telemessage.com/rest/message/status/";
    private static final String SEND_TO_URL_PATH = "https://secure.telemessage.com/jsp/receiveSMS.jsp";

    /**
     * Supported Interfaces
     */
    public enum Interface {
        /**
         * XML API
         */
        XML(XML_PATH, XML_PATH),
        /**
         * REST API
         */
        JSON(JSON_PATH_SEND, JSON_PATH_STATUS),
        /**
         * HTTP API
         */
        SEND_TO_URL(SEND_TO_URL_PATH, null);

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

    /**
     * Adds recipient to TeleMessage
     * @param r recipient to send message to
     * @return TeleMessage
     */
    public TeleMessage addRecipient(Recipient r) {
        recipients.add(r);
        return this;
    }

    /**
     * Removes recipient from TeleMessage
     * @param r recipient to remove
     * @return TeleMessage
     */
    public TeleMessage removeRecipient(Recipient r) {
        recipients.remove(r);
        return this;
    }

    /**
     * Adds list of recipients to TeleMessage
     * @param rs List of recipients to be added
     * @return TeleMessage
     */
    public TeleMessage addRecipients(List<Recipient> rs) {
        recipients.addAll(rs);
        return this;
    }

    /**
     * Clears recipients
     * @return TeleMessage
     */
    public TeleMessage clearRecipients() {
        recipients.clear();
        return this;
    }

    /**
     * Adds file message to be sent
     * @param f file message to be sent
     * @return TeleMessage
     */
    public TeleMessage addFileMessage(FileMessage f) {
        fileMessages.put(f.getFileName(), f);
        return this;
    }

    /**
     * Removes file from TeleMessage
     * @param r file message to be removed
     * @return TeleMessage
     */
    public TeleMessage removeFileMessage(FileMessage r) {
        fileMessages.remove(r.getFileName());
        return this;
    }

    /**
     * Clears file messages
     * @return TeleMessage
     */
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
     * Prepares message request to be sent. There are 3 options (indicated by anInterface {@link Interface} parameter):
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
     * Prepares status request to be sent. There are 2 options (indicated by anInterface {@link Interface} parameter):
     * 1. XML - writes xml into OutputStream out
     * 2. JSON - writes json into
     * @param username user's username to get status for
     * @param password user's password to get status for
     * @param messageID message id, received by sending message
     * @param messageKey message key, received by sending message
     * @param anInterface interface to use (REST or XML)
     * @param out output stream to write result in
     * @return Result if TeleMessage server responded to request
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public Result writeQueryStatus(String username, String password, long messageID, String messageKey, Interface anInterface, OutputStream out) throws IOException, IllegalArgumentException {
        Result res = new Result(false);
        AuthenticationDetails auth = new AuthenticationDetails(username, password);
        switch (anInterface) {
            case XML:
                writeQueryStatusXML(messageID, messageKey, out);
                res.success = true;
                break;
            case JSON:
                writeQueryStatusJson(auth, messageID, messageKey, out);
                res.success = true;
                break;
        }
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
        switch (anInterface) {
            case XML:
                return readXMLSendResponse(response);
            case JSON:
                return readJsonResponse(new ByteArrayInputStream(response.getBytes()), MessageResponse.class);
            case SEND_TO_URL:
                break;
        }
        return null;
    }



    /**
     * Reads TeleMessage immediate response after sending message
     * @param response from TeleMessage
     * @param anInterface interface to read response from
     * @return TeleMessage immediate response after sending message
     * @throws Exception while parsing response error
     */
    public MessageResponse readSendResponse(InputStream response, Interface anInterface) throws Exception {
        switch (anInterface) {
            case XML:
                return readXMLSendResponse(response);
            case JSON:
                return readJsonResponse(response, MessageResponse.class);
            case SEND_TO_URL:
                break;
        }
        return null;
    }

    private MessageResponse readXMLSendResponse(String response) throws Exception {
        return (new Persister().read(TeleMessageResponse.class, response)).convert();
    }

    private MessageResponse readXMLSendResponse(InputStream response) throws Exception {
        return (new Persister().read(TeleMessageResponse.class, response)).convert();
    }


    /**
     * Reads TeleMessage response after querying status
     * @param response from TeleMessage
     * @param anInterface interface to read response from
     * @return TeleMessage immediate response after sending message
     * @throws Exception while parsing response error
     */
    public MessageResponse readQueryStatusResponse(String response, Interface anInterface) throws Exception {
        switch (anInterface) {
            case XML:
                return readXMLQueryStatusResponse(response);
            case JSON:
                return readJsonResponse(new ByteArrayInputStream(response.getBytes()), StatusMessageResponse.class);
            case SEND_TO_URL:
                break;
        }
        return null;
    }

    /**
     * Reads TeleMessage response after querying status
     * @param response from TeleMessage
     * @param anInterface interface to read response from
     * @return TeleMessage response after sending message
     * @throws Exception while parsing response error
     */
    public StatusMessageResponse readQueryStatusResponse(InputStream response, Interface anInterface) throws Exception {
        switch (anInterface) {
            case XML:
                return readXMLQueryStatusResponse(response);
            case JSON:
                return new StatusMessageResponse(readJsonResponse(response, Response.class));
            case SEND_TO_URL:
                break;
        }
        return null;
    }

    private <T> T readJsonResponse(InputStream response, Class<T> clazz) throws IOException {
        return readJsonResponseFromArray(response, clazz);
    }

    private <T> T readJsonResponseFromArray(InputStream response, Class<T> clazz) throws IOException {
        Object obj = com.mikerusoft.jsonable.parser.JsonReader.read(response);
        if (obj == null)
            return null;
        if (obj.getClass().isArray())
            return (T)Arrays.asList(obj).get(0);

        if (List.class.isAssignableFrom(obj.getClass()))
            return (T)((List)obj).get(0);
        throw new IllegalArgumentException("Invalid response");
    }

    private StatusMessageResponse readXMLQueryStatusResponse(String response) throws Exception {
        return (StatusMessageResponse)(new Persister().read(TeleMessageResponse.class, response)).convert();
    }

    private StatusMessageResponse readXMLQueryStatusResponse(InputStream response) throws Exception {
        return (StatusMessageResponse)(new Persister().read(TeleMessageResponse.class, response)).convert();
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

    private void writeQueryStatusXML(long messageID, String messageKey, OutputStream out) throws IOException {
        try {
            new Persister().write(new MessageConverter().convert(messageID, messageKey), out);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private void writeSendXML(AuthenticationDetails auth, OutputStream out) throws IOException {
        try {
            new Persister().write(new MessageConverter().convert(auth, message), out);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private void writeSendJson(AuthenticationDetails auth, OutputStream out) throws IOException {
        try {
            Object tr = new Object[] {auth, message};
            JsonWriter.write(tr, out);
        } catch (IllegalAccessException e) {
            throw new IOException(e);
        } catch (InvocationTargetException e) {
            throw new IOException(e);
        }
    }


    private void writeQueryStatusJson(AuthenticationDetails auth, long messageID, String messageKey, OutputStream out) throws IOException {
        try {
            Object tr = new Object[] {auth, messageID, messageKey};
            JsonWriter.write(tr, out);
        } catch (IllegalAccessException e) {
            throw new IOException(e);
        } catch (InvocationTargetException e) {
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
