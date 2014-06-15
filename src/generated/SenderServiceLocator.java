/**
 * SenderServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generated;

public class SenderServiceLocator extends org.apache.axis.client.Service implements generated.SenderService {

    public SenderServiceLocator() {
    }


    public SenderServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SenderServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for telemessage
    private java.lang.String telemessage_address = "https://secure.telemessage.com/services/telemessage";

    public java.lang.String gettelemessageAddress() {
        return telemessage_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String telemessageWSDDServiceName = "telemessage";

    public java.lang.String gettelemessageWSDDServiceName() {
        return telemessageWSDDServiceName;
    }

    public void settelemessageWSDDServiceName(java.lang.String name) {
        telemessageWSDDServiceName = name;
    }

    public generated.Sender gettelemessage() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(telemessage_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return gettelemessage(endpoint);
    }

    public generated.Sender gettelemessage(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            generated.TelemessageSoapBindingStub _stub = new generated.TelemessageSoapBindingStub(portAddress, this);
            _stub.setPortName(gettelemessageWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void settelemessageEndpointAddress(java.lang.String address) {
        telemessage_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (generated.Sender.class.isAssignableFrom(serviceEndpointInterface)) {
                generated.TelemessageSoapBindingStub _stub = new generated.TelemessageSoapBindingStub(new java.net.URL(telemessage_address), this);
                _stub.setPortName(gettelemessageWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("telemessage".equals(inputPortName)) {
            return gettelemessage();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "SenderService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "telemessage"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("telemessage".equals(portName)) {
            settelemessageEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
