
package examples;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import telemessage.TeleMessage;
import telemessage.web.services.Recipient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Grinfeld Mikhail
 * @since 5/26/2014.
 */
public class HttpPostURLExample {
    public static void main(String...args) {
        TeleMessage tm = new TeleMessage();
        tm.setSubject("Hello World");
        tm.setText("HTTPS URL sample");
        tm.addRecipient(new Recipient("+1xxxxxxxxxx", "SMS", null));
        
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost get = new HttpPost(tm.getSendURL(TeleMessage.Interface.HTTPS));
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            TeleMessage.URLResult res = (TeleMessage.URLResult)tm.write("xxxxxxxxxxxx", "xxxxxxxx", TeleMessage.Interface.HTTPS, by);
            get.setEntity(new UrlEncodedFormEntity(Arrays.asList(res.getParams())));
            HttpResponse resp = httpclient.execute(get);

            if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
                resp.getEntity().writeTo(System.out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
