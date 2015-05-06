package examples.xml;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import telemessage.FileMessage;
import telemessage.TeleMessage;
import telemessage.web.services.MessageResponse;
import telemessage.web.services.Recipient;

import java.io.*;

/**
 * @author Grinfeld Mikhail
 * @since 5/26/2014.
 */
public class HttpPostFileXMLExample {
    public static void main(String...args) {
        try {
            TeleMessage tm = new TeleMessage();
            tm.setSubject("Hello World");
            tm.setText("XML sample");
            tm.addRecipient(new Recipient("someemail@somedomain.com", "EMAIL", null));

            File f = new File(HttpPostFileXMLExample.class.getResource("file.png").getPath());
            FileInputStream fi = new FileInputStream(f);

            tm.addFileMessage(new FileMessage(f.getName(), "image/png", fi));

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(tm.getSendURL(TeleMessage.Interface.XML));
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            tm.writeSend("john_donne", "password", TeleMessage.Interface.XML, by);

            ByteArrayEntity entity = new ByteArrayEntity(by.toByteArray(), ContentType.create("text/xml", "UTF-8"));
            httppost.setEntity(entity);

            HttpResponse resp = httpclient.execute(httppost);
            if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
                InputStream in = resp.getEntity().getContent();
                try {
                    MessageResponse r = tm.readSendResponse(in, TeleMessage.Interface.XML);
                    System.out.println("Response is " + r.getResultCode() + ", description " + r.getResultDescription() + ", message Id " + r.getMessageID() + ", message key " + r.getMessageKey());
                } finally {
                    if (in != null)
                        IOUtils.closeQuietly(in);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
