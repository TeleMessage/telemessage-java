package examples.xml;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import telemessage.TeleMessage;
import telemessage.web.services.RecipientStatus;
import telemessage.web.services.StatusMessageResponse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author Grinfeld Mikhail
 * @since 11/10/2014.
 */
public class HttpPostQueryStatusXMLExample {
    public static void main(String...args) {
        TeleMessage tm = new TeleMessage();

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(tm.getStatusURL(TeleMessage.Interface.XML));
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            // message id and message key received by sending message
            tm.writeQueryStatus("john_donne", "password", 6698, "404640224957515620220868271364", TeleMessage.Interface.XML, by);

            ByteArrayEntity entity = new ByteArrayEntity(by.toByteArray(), ContentType.create("application/json", "UTF-8"));
            httppost.setEntity(entity);

            HttpResponse resp = httpclient.execute(httppost);
            if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
                InputStream in = resp.getEntity().getContent();
                try {
                    StatusMessageResponse r = tm.readQueryStatusResponse(in, TeleMessage.Interface.XML);
                    System.out.println("Response is " + r.getResultCode() + ", description " + r.getResultDescription() + ", message Id " + r.getMessageID());
                    if (r.getRecipients() != null && r.getRecipients().length > 0) {
                        for (RecipientStatus rs : r.getRecipients()) {
                            System.out.println("Status is " + rs.getStatus() + " and description is \"" + rs.getDescription() + "\" for recipient " + rs.getRecipient().getValue());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Invalid XML or invalid data");
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
