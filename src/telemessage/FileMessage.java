package telemessage;

import java.io.InputStream;

/**
 * File message to be sent via TeleMessage
 */
public class FileMessage {
    private String fileName;
    private String mimeType;
    private InputStream in;

    /**
     *
     * @param fileName file name to be sent
     * @param mimeType mime type
     * @param in file input stream
     */
    public FileMessage(String fileName, String mimeType, InputStream in) {
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.in = in;
    }

    public String getFileName() { return fileName; }
    public String getMimeType() { return mimeType; }
    public InputStream getInputStream() { return in; }
}
