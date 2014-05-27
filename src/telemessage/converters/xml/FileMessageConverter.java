package telemessage.converters.xml;

import org.apache.commons.lang3.ArrayUtils;
import org.simpleframework.xml.Element;
import telemessage.web.services.FileMessage;

public class FileMessageConverter implements XMLConverter<FileMessageConverter.FILE_MESSAGE, FileMessage> {


    @Override
    /**
     * Expects first element to be integer
     */
    public FILE_MESSAGE convert(FileMessage fileMessage, Object...args) {
        if (fileMessage != null && !ArrayUtils.isEmpty(args))
            return new FILE_MESSAGE(fileMessage, (Integer)args[0]);
        return null;
    }

    static class FILE_MESSAGE {

        public FILE_MESSAGE() {
        }

        public FILE_MESSAGE(FileMessage fileMessage, int index) {
            this.fileName = fileMessage.getFileName();
            this.mimeType = fileMessage.getMimeType();
            this.messageIndex = index;
            this.fileData = new FileData(fileMessage.getValue());
        }

        @Element (name = "MESSAGE_INDEX") int messageIndex;
        @Element (name = "FILE_NAME") String fileName;
        @Element (name = "MIME_TYPE") String mimeType;
        @Element (name = "FILE_DATA") FileData fileData;

        private static class FileData {

            FileData() {
            }

            FileData(String data) {
                this.base64 = data;
            }

            @Element (name = "FILE_DATA_BASE64") String base64;
        }
    }
}
