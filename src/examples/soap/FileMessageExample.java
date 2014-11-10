package examples.soap;

import generated.*;
import org.apache.commons.codec.binary.Base64;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;

public class FileMessageExample {
    public static void main(String...args) {
            try {
                Sender s = new SenderServiceLocator().gettelemessage();
                AuthenticationDetails a = new AuthenticationDetails("password","john_donne");
                Message m = new Message();
                Recipient r = new Recipient();
                r.setValue("+1xxxxxxxxxx");
                r.setType("FAX");
                m.setTextMessage("Hello");
                m.setRecipients(new Recipient[] {r});
                m.setSubject("1st");

                File f = new File(FileMessageExample.class.getResource("file.png").getPath());
                FileInputStream fi = new FileInputStream(f);
                m.setFileMessages(new FileMessage[] {
                        new FileMessage(f.getName(), "image/png", "", 0, Base64.encodeBase64String(org.apache.commons.io.IOUtils.toByteArray(fi)))
                });
                MessageResponse rs = s.sendMessage(a, m);
                System.out.println("Message id: " + rs.getMessageID() + ", key: " + rs.getMessageKey() + ", result status: " + rs.getResultCode());
            } catch (ServiceException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
