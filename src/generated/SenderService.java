/**
 * SenderService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generated;

public interface SenderService extends javax.xml.rpc.Service {
    public java.lang.String gettelemessageAddress();

    public generated.Sender gettelemessage() throws javax.xml.rpc.ServiceException;

    public generated.Sender gettelemessage(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
