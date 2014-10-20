/**
 * Schedule.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generatedV01;

public class Schedule  implements java.io.Serializable {
    private long expiredAt;

    private long sendAt;

    public Schedule() {
    }

    public Schedule(
           long expiredAt,
           long sendAt) {
           this.expiredAt = expiredAt;
           this.sendAt = sendAt;
    }


    /**
     * Gets the expiredAt value for this Schedule.
     * 
     * @return expiredAt
     */
    public long getExpiredAt() {
        return expiredAt;
    }


    /**
     * Sets the expiredAt value for this Schedule.
     * 
     * @param expiredAt
     */
    public void setExpiredAt(long expiredAt) {
        this.expiredAt = expiredAt;
    }


    /**
     * Gets the sendAt value for this Schedule.
     * 
     * @return sendAt
     */
    public long getSendAt() {
        return sendAt;
    }


    /**
     * Sets the sendAt value for this Schedule.
     * 
     * @param sendAt
     */
    public void setSendAt(long sendAt) {
        this.sendAt = sendAt;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Schedule)) return false;
        Schedule other = (Schedule) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.expiredAt == other.getExpiredAt() &&
            this.sendAt == other.getSendAt();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Long(getExpiredAt()).hashCode();
        _hashCode += new Long(getSendAt()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Schedule.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "Schedule"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiredAt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expiredAt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendAt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendAt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
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
