package examples.json;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import telemessage.TeleMessage;
import telemessage.web.services.MessageResponse;
import telemessage.web.services.Recipient;
import telemessage.web.services.RecipientStatus;
import telemessage.web.services.StatusMessageResponse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author Grinfeld Mikhail
 * @since 11/2/2014.
 */
public class HttpPostQueryStatusExample {
    public static void main(String...args) {
        TeleMessage tm = new TeleMessage();

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(tm.getStatusURL(TeleMessage.Interface.JSON));
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            tm.writeQueryStatus("john_donne", "password", 6705, "346840808935768533461928966631", TeleMessage.Interface.JSON, by);

            ByteArrayEntity entity = new ByteArrayEntity(by.toByteArray(), ContentType.create("application/json", "UTF-8"));
            httppost.setEntity(entity);

            HttpResponse resp = httpclient.execute(httppost);
            if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
                InputStream in = resp.getEntity().getContent();
                try {
                    StatusMessageResponse r = tm.readQueryStatusResponse(in, TeleMessage.Interface.JSON);
                    System.out.println("Response is " + r.getResultCode() + ", description " + r.getResultDescription() + ", message Id " + r.getMessageID() + ", message key " + r.getMessageKey());
                    if (r.getRecipients() != null && r.getRecipients().length > 0) {
                        for (RecipientStatus rs : r.getRecipients()) {
                            System.out.println("Status is " + rs.getStatus() + " and description is \"" + rs.getDescription() + "\" for recipient " + rs.getRecipient().getValue());
                        }
                    }
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
