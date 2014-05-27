package examples.url;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import telemessage.TeleMessage;
import telemessage.URLResult;
import telemessage.web.services.Recipient;

import java.io.IOException;
import java.util.Map;

/**
 * @author Grinfeld Mikhail
 * @since 5/26/2014.
 */
public class HttpGetURLExample {
    public static void main(String...args) {
        TeleMessage tm = new TeleMessage();
        tm.setSubject("Hello World");
        tm.setText("SEND TO URL sample");
        tm.addRecipient(new Recipient("+1xxxxxxxxxx", "SMS", null));
        
        try {

            URLResult res = (URLResult)tm.writeSend("xxxxxxxxxxxx", "xxxxxxxx", TeleMessage.Interface.SEND_TO_URL, null);

            RequestBuilder builder = RequestBuilder.get().setUri(tm.getSendURL(TeleMessage.Interface.SEND_TO_URL));
            if (res.getParams() != null) {
                for (Map.Entry<String, String> entry : res.getParams().entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }

            HttpResponse resp = HttpClients.createDefault().execute(builder.build());

            if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
                resp.getEntity().writeTo(System.out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
