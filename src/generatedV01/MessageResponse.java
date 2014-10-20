/**
 * MessageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generatedV01;

public class MessageResponse  extends generatedV01.Response  implements java.io.Serializable {
    private long messageID;

    private java.lang.String messageKey;

    public MessageResponse() {
    }

    public MessageResponse(
           java.lang.Long contentSize,
           int resultCode,
           java.lang.String resultDescription,
           long messageID,
           java.lang.String messageKey) {
        super(
            contentSize,
            resultCode,
            resultDescription);
        this.messageID = messageID;
        this.messageKey = messageKey;
    }


    /**
     * Gets the messageID value for this MessageResponse.
     * 
     * @return messageID
     */
    public long getMessageID() {
        return messageID;
    }


    /**
     * Sets the messageID value for this MessageResponse.
     * 
     * @param messageID
     */
    public void setMessageID(long messageID) {
        this.messageID = messageID;
    }


    /**
     * Gets the messageKey value for this MessageResponse.
     * 
     * @return messageKey
     */
    public java.lang.String getMessageKey() {
        return messageKey;
    }


    /**
     * Sets the messageKey value for this MessageResponse.
     * 
     * @param messageKey
     */
    public void setMessageKey(java.lang.String messageKey) {
        this.messageKey = messageKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageResponse)) return false;
        MessageResponse other = (MessageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.messageID == other.getMessageID() &&
            ((this.messageKey==null && other.getMessageKey()==null) || 
             (this.messageKey!=null &&
              this.messageKey.equals(other.getMessageKey())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += new Long(getMessageID()).hashCode();
        if (getMessageKey() != null) {
            _hashCode += getMessageKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "MessageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
