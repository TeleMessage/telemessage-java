package telemessage.web.services;


import com.mikerusoft.jsonable.annotations.JsonClass;
import com.mikerusoft.jsonable.annotations.JsonField;

/**
 * @author Grinfeld Mikhail
 * @since Feb 25, 2010 6:14:06 PM
 */
@JsonClass
public class FileMessage {

    @JsonField private String fileName;
    @JsonField private String mimeType;

    /**
     * only if type == FILE_DATA data stored in value
     */
    @JsonField private String value;

    // field for future use. Now supported only 0 - so we don't expose it
    @JsonField private int type = 0;

    public FileMessage(){}

    public FileMessage(String mimeType, String value, String fileName) {
        this.mimeType = mimeType;
        this.value = value;
        this.fileName = fileName;
    }

    protected FileMessage(String mimeType, int type, String value, String fileName) {
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