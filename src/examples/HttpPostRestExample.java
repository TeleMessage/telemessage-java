package examples;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import telemessage.TeleMessage;
import telemessage.web.services.Recipient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Grinfeld Mikhail
 * @since 5/26/2014.
 */
public class HttpPostRestExample {

    public static void main(String...args) {
        TeleMessage tm = new TeleMessage();
        tm.setSubject("Hello World");
        tm.setText("Rest sample");
        tm.addRecipient(new Recipient("+1xxxxxxxxxx", "SMS", null));
        
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(tm.getSendURL(TeleMessage.Interface.REST));
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            tm.write("xxxxxxxxxx", "xxxxxxxx", TeleMessage.Interface.REST, by);

            StringEntity entity = new StringEntity(
                    new String(by.toByteArray()),
                    ContentType.create("text/plain", "UTF-8")
            );
            httppost.setEntity(entity);

            HttpResponse resp = httpclient.execute(httppost);
            if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
                resp.getEntity().writeTo(System.out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
