package telemessage.web.services;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

/**
 * @author Grinfeld Mikhail
 * @since Feb 25, 2010 6:14:06 PM
 */
@RestClass
public class FileMessage {

    @RestField private String fileName;
    @RestField private String mimeType;

    /**
     * only if type == FILE_DATA data stored in value
     */
    @RestField private String value;

    // field for future use. Now supported only 0 - so we don't expose it
    @RestField private int type = 0;

    public FileMessage(){}

    public FileMessage(String mimeType, int type, String value, String fileName, String preview) {
        this.mimeType = mimeType;
        this.type = type;
        this.value = value;
        this.fileName = fileName;
    }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getMimeType() { return mimeType; }
    public void setMimeType(String mimeType) { this.mimeType = mimeType; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}