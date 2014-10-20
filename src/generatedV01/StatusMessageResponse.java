/**
 * StatusMessageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generatedV01;

public class StatusMessageResponse  extends generatedV01.MessageResponse  implements java.io.Serializable {
    private generatedV01.RecipientStatus[] recipients;

    public StatusMessageResponse() {
    }

    public StatusMessageResponse(
           java.lang.Long contentSize,
           int resultCode,
           java.lang.String resultDescription,
           long messageID,
           java.lang.String messageKey,
           generatedV01.RecipientStatus[] recipients) {
        super(
            contentSize,
            resultCode,
            resultDescription,
            messageID,
            messageKey);
        this.recipients = recipients;
    }


    /**
     * Gets the recipients value for this StatusMessageResponse.
     * 
     * @return recipients
     */
    public generatedV01.RecipientStatus[] getRecipients() {
        return recipients;
    }


    /**
     * Sets the recipients value for this StatusMessageResponse.
     * 
     * @param recipients
     */
    public void setRecipients(generatedV01.RecipientStatus[] recipients) {
        this.recipients = recipients;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusMessageResponse)) return false;
        StatusMessageResponse other = (StatusMessageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.recipients==null && other.getRecipients()==null) || 
             (this.recipients!=null &&
              java.util.Arrays.equals(this.recipients, other.getRecipients())));
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
        if (getRecipients() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecipients());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecipients(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusMessageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "StatusMessageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipients");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipients"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "RecipientStatus"));
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
