/**
 * MtMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.sms;

public class MtMessage  implements java.io.Serializable {
    private java.lang.String content;

    private java.lang.String demo;

    private java.lang.String[] phoneNumber;

    private java.util.Calendar sendTime;

    private java.lang.String smsId;

    private java.lang.String subCode;

    public MtMessage() {
    }

    public MtMessage(
           java.lang.String content,
           java.lang.String demo,
           java.lang.String[] phoneNumber,
           java.util.Calendar sendTime,
           java.lang.String smsId,
           java.lang.String subCode) {
           this.content = content;
           this.demo = demo;
           this.phoneNumber = phoneNumber;
           this.sendTime = sendTime;
           this.smsId = smsId;
           this.subCode = subCode;
    }


    /**
     * Gets the content value for this MtMessage.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this MtMessage.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }


    /**
     * Gets the demo value for this MtMessage.
     * 
     * @return demo
     */
    public java.lang.String getDemo() {
        return demo;
    }


    /**
     * Sets the demo value for this MtMessage.
     * 
     * @param demo
     */
    public void setDemo(java.lang.String demo) {
        this.demo = demo;
    }


    /**
     * Gets the phoneNumber value for this MtMessage.
     * 
     * @return phoneNumber
     */
    public java.lang.String[] getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phoneNumber value for this MtMessage.
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(java.lang.String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public java.lang.String getPhoneNumber(int i) {
        return this.phoneNumber[i];
    }

    public void setPhoneNumber(int i, java.lang.String _value) {
        this.phoneNumber[i] = _value;
    }


    /**
     * Gets the sendTime value for this MtMessage.
     * 
     * @return sendTime
     */
    public java.util.Calendar getSendTime() {
        return sendTime;
    }


    /**
     * Sets the sendTime value for this MtMessage.
     * 
     * @param sendTime
     */
    public void setSendTime(java.util.Calendar sendTime) {
        this.sendTime = sendTime;
    }


    /**
     * Gets the smsId value for this MtMessage.
     * 
     * @return smsId
     */
    public java.lang.String getSmsId() {
        return smsId;
    }


    /**
     * Sets the smsId value for this MtMessage.
     * 
     * @param smsId
     */
    public void setSmsId(java.lang.String smsId) {
        this.smsId = smsId;
    }


    /**
     * Gets the subCode value for this MtMessage.
     * 
     * @return subCode
     */
    public java.lang.String getSubCode() {
        return subCode;
    }


    /**
     * Sets the subCode value for this MtMessage.
     * 
     * @param subCode
     */
    public void setSubCode(java.lang.String subCode) {
        this.subCode = subCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MtMessage)) return false;
        MtMessage other = (MtMessage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.demo==null && other.getDemo()==null) || 
             (this.demo!=null &&
              this.demo.equals(other.getDemo()))) &&
            ((this.phoneNumber==null && other.getPhoneNumber()==null) || 
             (this.phoneNumber!=null &&
              java.util.Arrays.equals(this.phoneNumber, other.getPhoneNumber()))) &&
            ((this.sendTime==null && other.getSendTime()==null) || 
             (this.sendTime!=null &&
              this.sendTime.equals(other.getSendTime()))) &&
            ((this.smsId==null && other.getSmsId()==null) || 
             (this.smsId!=null &&
              this.smsId.equals(other.getSmsId()))) &&
            ((this.subCode==null && other.getSubCode()==null) || 
             (this.subCode!=null &&
              this.subCode.equals(other.getSubCode())));
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
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getDemo() != null) {
            _hashCode += getDemo().hashCode();
        }
        if (getPhoneNumber() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPhoneNumber());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPhoneNumber(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSendTime() != null) {
            _hashCode += getSendTime().hashCode();
        }
        if (getSmsId() != null) {
            _hashCode += getSmsId().hashCode();
        }
        if (getSubCode() != null) {
            _hashCode += getSubCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MtMessage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "mtMessage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("demo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "demo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smsId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "smsId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
