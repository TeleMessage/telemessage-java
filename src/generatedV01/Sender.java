/**
 * Sender.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generatedV01;

public interface Sender extends java.rmi.Remote {
    public generatedV01.MessageResponse sendMessage(generatedV01.AuthenticationDetails loginDetails, generatedV01.Message message) throws java.rmi.RemoteException;
    public generatedV01.SingleStatusMessageResponse queryStatusSingle(generatedV01.AuthenticationDetails loginDetails, long message_id, java.lang.String messageKey, java.lang.String recipient) throws java.rmi.RemoteException;
}
