/**
 * SingleStatusMessageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generatedV01;

public class SingleStatusMessageResponse  extends generatedV01.MessageResponse  implements java.io.Serializable {
    private java.lang.String description;

    private int status;

    private long statusDate;

    public SingleStatusMessageResponse() {
    }

    public SingleStatusMessageResponse(
           java.lang.Long contentSize,
           int resultCode,
           java.lang.String resultDescription,
           long messageID,
           java.lang.String messageKey,
           java.lang.String description,
           int status,
           long statusDate) {
        super(
            contentSize,
            resultCode,
            resultDescription,
            messageID,
            messageKey);
        this.description = description;
        this.status = status;
        this.statusDate = statusDate;
    }


    /**
     * Gets the description value for this SingleStatusMessageResponse.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SingleStatusMessageResponse.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the status value for this SingleStatusMessageResponse.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SingleStatusMessageResponse.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the statusDate value for this SingleStatusMessageResponse.
     * 
     * @return statusDate
     */
    public long getStatusDate() {
        return statusDate;
    }


    /**
     * Sets the statusDate value for this SingleStatusMessageResponse.
     * 
     * @param statusDate
     */
    public void setStatusDate(long statusDate) {
        this.statusDate = statusDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SingleStatusMessageResponse)) return false;
        SingleStatusMessageResponse other = (SingleStatusMessageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.status == other.getStatus() &&
            this.statusDate == other.getStatusDate();
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += getStatus();
        _hashCode += new Long(getStatusDate()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SingleStatusMessageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "SingleStatusMessageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusDate"));
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
