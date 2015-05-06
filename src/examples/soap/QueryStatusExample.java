package examples.soap;

import generated.*;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * @author Grinfeld Mikhail
 * @since 11/10/2014.
 */
public class QueryStatusExample {
    public static void main(String...args) {
        try {
            Sender s = new SenderServiceLocator().gettelemessage();
            AuthenticationDetails a = new AuthenticationDetails("password","john_donne");
            // message id and message key received by sending message
            StatusMessageResponse sr = s.queryStatus(a, 6707, "046527383931461541589632292670");
            System.out.println("result status: " + sr.getResultCode());
            if (sr.getResultCode() == 0 || sr.getResultCode() == 100) {
                for (RecipientStatus rt : sr.getRecipients()) {
                    System.out.println("Date: " + (new Date(rt.getStatusDate())) + ", message status: " + rt.getStatus() + ", status description: " + rt.getDescription() + " for recipient " + rt.getRecipient().getValue());
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
