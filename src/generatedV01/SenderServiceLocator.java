/**
 * SenderServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generatedV01;

public class SenderServiceLocator extends org.apache.axis.client.Service implements generatedV01.SenderService {

    public SenderServiceLocator() {
    }


    public SenderServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SenderServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for telemessagesingle
    private java.lang.String telemessagesingle_address = "http://rndtc:8080/services/telemessagesingle";

    public java.lang.String gettelemessagesingleAddress() {
        return telemessagesingle_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String telemessagesingleWSDDServiceName = "telemessagesingle";

    public java.lang.String gettelemessagesingleWSDDServiceName() {
        return telemessagesingleWSDDServiceName;
    }

    public void settelemessagesingleWSDDServiceName(java.lang.String name) {
        telemessagesingleWSDDServiceName = name;
    }

    public generatedV01.Sender gettelemessagesingle() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(telemessagesingle_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return gettelemessagesingle(endpoint);
    }

    public generatedV01.Sender gettelemessagesingle(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            generatedV01.TelemessagesingleSoapBindingStub _stub = new generatedV01.TelemessagesingleSoapBindingStub(portAddress, this);
            _stub.setPortName(gettelemessagesingleWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void settelemessagesingleEndpointAddress(java.lang.String address) {
        telemessagesingle_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (generatedV01.Sender.class.isAssignableFrom(serviceEndpointInterface)) {
                generatedV01.TelemessagesingleSoapBindingStub _stub = new generatedV01.TelemessagesingleSoapBindingStub(new java.net.URL(telemessagesingle_address), this);
                _stub.setPortName(gettelemessagesingleWSDDServiceName());
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
        if ("telemessagesingle".equals(inputPortName)) {
            return gettelemessagesingle();
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
            ports.add(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "telemessagesingle"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("telemessagesingle".equals(portName)) {
            settelemessagesingleEndpointAddress(address);
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
