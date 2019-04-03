/**
 * MoMessageResDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.smsinter;

public class MoMessageResDetail  implements java.io.Serializable {
    private java.lang.String content;

    private java.lang.String demo;

    private java.lang.String phoneNumber;

    private java.util.Calendar revTime;

    private java.lang.String subCode;

    public MoMessageResDetail() {
    }

    public MoMessageResDetail(
           java.lang.String content,
           java.lang.String demo,
           java.lang.String phoneNumber,
           java.util.Calendar revTime,
           java.lang.String subCode) {
           this.content = content;
           this.demo = demo;
           this.phoneNumber = phoneNumber;
           this.revTime = revTime;
           this.subCode = subCode;
    }


    /**
     * Gets the content value for this MoMessageResDetail.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this MoMessageResDetail.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }


    /**
     * Gets the demo value for this MoMessageResDetail.
     * 
     * @return demo
     */
    public java.lang.String getDemo() {
        return demo;
    }


    /**
     * Sets the demo value for this MoMessageResDetail.
     * 
     * @param demo
     */
    public void setDemo(java.lang.String demo) {
        this.demo = demo;
    }


    /**
     * Gets the phoneNumber value for this MoMessageResDetail.
     * 
     * @return phoneNumber
     */
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phoneNumber value for this MoMessageResDetail.
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Gets the revTime value for this MoMessageResDetail.
     * 
     * @return revTime
     */
    public java.util.Calendar getRevTime() {
        return revTime;
    }


    /**
     * Sets the revTime value for this MoMessageResDetail.
     * 
     * @param revTime
     */
    public void setRevTime(java.util.Calendar revTime) {
        this.revTime = revTime;
    }


    /**
     * Gets the subCode value for this MoMessageResDetail.
     * 
     * @return subCode
     */
    public java.lang.String getSubCode() {
        return subCode;
    }


    /**
     * Sets the subCode value for this MoMessageResDetail.
     * 
     * @param subCode
     */
    public void setSubCode(java.lang.String subCode) {
        this.subCode = subCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MoMessageResDetail)) return false;
        MoMessageResDetail other = (MoMessageResDetail) obj;
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
              this.phoneNumber.equals(other.getPhoneNumber()))) &&
            ((this.revTime==null && other.getRevTime()==null) || 
             (this.revTime!=null &&
              this.revTime.equals(other.getRevTime()))) &&
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
            _hashCode += getPhoneNumber().hashCode();
        }
        if (getRevTime() != null) {
            _hashCode += getRevTime().hashCode();
        }
        if (getSubCode() != null) {
            _hashCode += getSubCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MoMessageResDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "moMessageResDetail"));
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "revTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
