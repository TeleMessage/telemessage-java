/**
 * Recipient.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generatedV01;

public class Recipient  implements java.io.Serializable {
    private int addresType;

    private java.lang.String description;

    private long groupId;

    private boolean groupSenderExcluded;

    private boolean isGroupSenderExcluded;

    private int rankId;

    private java.lang.String type;

    private java.lang.String value;

    public Recipient() {
    }

    public Recipient(
           int addresType,
           java.lang.String description,
           long groupId,
           boolean groupSenderExcluded,
           boolean isGroupSenderExcluded,
           int rankId,
           java.lang.String type,
           java.lang.String value) {
           this.addresType = addresType;
           this.description = description;
           this.groupId = groupId;
           this.groupSenderExcluded = groupSenderExcluded;
           this.isGroupSenderExcluded = isGroupSenderExcluded;
           this.rankId = rankId;
           this.type = type;
           this.value = value;
    }


    /**
     * Gets the addresType value for this Recipient.
     * 
     * @return addresType
     */
    public int getAddresType() {
        return addresType;
    }


    /**
     * Sets the addresType value for this Recipient.
     * 
     * @param addresType
     */
    public void setAddresType(int addresType) {
        this.addresType = addresType;
    }


    /**
     * Gets the description value for this Recipient.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Recipient.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the groupId value for this Recipient.
     * 
     * @return groupId
     */
    public long getGroupId() {
        return groupId;
    }


    /**
     * Sets the groupId value for this Recipient.
     * 
     * @param groupId
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


    /**
     * Gets the groupSenderExcluded value for this Recipient.
     * 
     * @return groupSenderExcluded
     */
    public boolean isGroupSenderExcluded() {
        return groupSenderExcluded;
    }


    /**
     * Sets the groupSenderExcluded value for this Recipient.
     * 
     * @param groupSenderExcluded
     */
    public void setGroupSenderExcluded(boolean groupSenderExcluded) {
        this.groupSenderExcluded = groupSenderExcluded;
    }


    /**
     * Gets the isGroupSenderExcluded value for this Recipient.
     * 
     * @return isGroupSenderExcluded
     */
    public boolean isIsGroupSenderExcluded() {
        return isGroupSenderExcluded;
    }


    /**
     * Sets the isGroupSenderExcluded value for this Recipient.
     * 
     * @param isGroupSenderExcluded
     */
    public void setIsGroupSenderExcluded(boolean isGroupSenderExcluded) {
        this.isGroupSenderExcluded = isGroupSenderExcluded;
    }


    /**
     * Gets the rankId value for this Recipient.
     * 
     * @return rankId
     */
    public int getRankId() {
        return rankId;
    }


    /**
     * Sets the rankId value for this Recipient.
     * 
     * @param rankId
     */
    public void setRankId(int rankId) {
        this.rankId = rankId;
    }


    /**
     * Gets the type value for this Recipient.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this Recipient.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the value value for this Recipient.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this Recipient.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Recipient)) return false;
        Recipient other = (Recipient) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.addresType == other.getAddresType() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.groupId == other.getGroupId() &&
            this.groupSenderExcluded == other.isGroupSenderExcluded() &&
            this.isGroupSenderExcluded == other.isIsGroupSenderExcluded() &&
            this.rankId == other.getRankId() &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
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
        _hashCode += getAddresType();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += new Long(getGroupId()).hashCode();
        _hashCode += (isGroupSenderExcluded() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsGroupSenderExcluded() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getRankId();
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Recipient.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "Recipient"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addresType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupSenderExcluded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupSenderExcluded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isGroupSenderExcluded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isGroupSenderExcluded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rankId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rankId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "value"));
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
