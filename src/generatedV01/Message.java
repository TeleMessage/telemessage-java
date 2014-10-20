/**
 * Message.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package generatedV01;

public class Message  implements java.io.Serializable {
    private generatedV01.FileMessage[] fileMessages;

    private generatedV01.Property[] properties;

    private generatedV01.Recipient[] recipients;

    private generatedV01.Schedule schedule;

    private java.lang.String subject;

    private java.lang.String textMessage;

    public Message() {
    }

    public Message(
           generatedV01.FileMessage[] fileMessages,
           generatedV01.Property[] properties,
           generatedV01.Recipient[] recipients,
           generatedV01.Schedule schedule,
           java.lang.String subject,
           java.lang.String textMessage) {
           this.fileMessages = fileMessages;
           this.properties = properties;
           this.recipients = recipients;
           this.schedule = schedule;
           this.subject = subject;
           this.textMessage = textMessage;
    }


    /**
     * Gets the fileMessages value for this Message.
     * 
     * @return fileMessages
     */
    public generatedV01.FileMessage[] getFileMessages() {
        return fileMessages;
    }


    /**
     * Sets the fileMessages value for this Message.
     * 
     * @param fileMessages
     */
    public void setFileMessages(generatedV01.FileMessage[] fileMessages) {
        this.fileMessages = fileMessages;
    }


    /**
     * Gets the properties value for this Message.
     * 
     * @return properties
     */
    public generatedV01.Property[] getProperties() {
        return properties;
    }


    /**
     * Sets the properties value for this Message.
     * 
     * @param properties
     */
    public void setProperties(generatedV01.Property[] properties) {
        this.properties = properties;
    }


    /**
     * Gets the recipients value for this Message.
     * 
     * @return recipients
     */
    public generatedV01.Recipient[] getRecipients() {
        return recipients;
    }


    /**
     * Sets the recipients value for this Message.
     * 
     * @param recipients
     */
    public void setRecipients(generatedV01.Recipient[] recipients) {
        this.recipients = recipients;
    }


    /**
     * Gets the schedule value for this Message.
     * 
     * @return schedule
     */
    public generatedV01.Schedule getSchedule() {
        return schedule;
    }


    /**
     * Sets the schedule value for this Message.
     * 
     * @param schedule
     */
    public void setSchedule(generatedV01.Schedule schedule) {
        this.schedule = schedule;
    }


    /**
     * Gets the subject value for this Message.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this Message.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the textMessage value for this Message.
     * 
     * @return textMessage
     */
    public java.lang.String getTextMessage() {
        return textMessage;
    }


    /**
     * Sets the textMessage value for this Message.
     * 
     * @param textMessage
     */
    public void setTextMessage(java.lang.String textMessage) {
        this.textMessage = textMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Message)) return false;
        Message other = (Message) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fileMessages==null && other.getFileMessages()==null) || 
             (this.fileMessages!=null &&
              java.util.Arrays.equals(this.fileMessages, other.getFileMessages()))) &&
            ((this.properties==null && other.getProperties()==null) || 
             (this.properties!=null &&
              java.util.Arrays.equals(this.properties, other.getProperties()))) &&
            ((this.recipients==null && other.getRecipients()==null) || 
             (this.recipients!=null &&
              java.util.Arrays.equals(this.recipients, other.getRecipients()))) &&
            ((this.schedule==null && other.getSchedule()==null) || 
             (this.schedule!=null &&
              this.schedule.equals(other.getSchedule()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.textMessage==null && other.getTextMessage()==null) || 
             (this.textMessage!=null &&
              this.textMessage.equals(other.getTextMessage())));
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
        if (getFileMessages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFileMessages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFileMessages(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProperties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
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
        if (getSchedule() != null) {
            _hashCode += getSchedule().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getTextMessage() != null) {
            _hashCode += getTextMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Message.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "Message"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileMessages");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileMessages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "FileMessage"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("properties");
        elemField.setXmlName(new javax.xml.namespace.QName("", "properties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "Property"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipients");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipients"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "Recipient"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule");
        elemField.setXmlName(new javax.xml.namespace.QName("", "schedule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://telemessage.com/soap/schemas", "Schedule"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "textMessage"));
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
