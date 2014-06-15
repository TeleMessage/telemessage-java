/**
 * Sender.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generated;

public interface Sender extends java.rmi.Remote {
    public generated.MessageResponse sendMessage(generated.AuthenticationDetails loginDetails, generated.Message message) throws java.rmi.RemoteException;
    public generated.StatusMessageResponse queryStatus(generated.AuthenticationDetails loginDetails, long message_id, java.lang.String messageKey) throws java.rmi.RemoteException;
}
