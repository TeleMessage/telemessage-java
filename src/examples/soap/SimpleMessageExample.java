package examples.soap;

import generated.*;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public class SimpleMessageExample {
    public static void main(String...args) {
        try {
            Sender s = new SenderServiceLocator().gettelemessage();
            AuthenticationDetails a = new AuthenticationDetails("password","john_donne");
            Message m = new Message();
            Recipient r = new Recipient();
            r.setValue("+1xxxxxxxxxx");
            r.setType("SMS");
            m.setTextMessage("Hello");
            m.setRecipients(new Recipient[] {r});
            m.setSubject("1st");
            MessageResponse rs = s.sendMessage(a, m);
            System.out.println("Message id: " + rs.getMessageID() + ", key: " + rs.getMessageKey() + ", result status: " + rs.getResultCode());
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
