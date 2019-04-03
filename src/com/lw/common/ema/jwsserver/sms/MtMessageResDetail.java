/**
 * MtMessageResDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.sms;

public class MtMessageResDetail  implements java.io.Serializable {
    private java.lang.String phoneNumber;

    private java.lang.String stat;

    private java.lang.String statDes;

    public MtMessageResDetail() {
    }

    public MtMessageResDetail(
           java.lang.String phoneNumber,
           java.lang.String stat,
           java.lang.String statDes) {
           this.phoneNumber = phoneNumber;
           this.stat = stat;
           this.statDes = statDes;
    }


    /**
     * Gets the phoneNumber value for this MtMessageResDetail.
     * 
     * @return phoneNumber
     */
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phoneNumber value for this MtMessageResDetail.
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Gets the stat value for this MtMessageResDetail.
     * 
     * @return stat
     */
    public java.lang.String getStat() {
        return stat;
    }


    /**
     * Sets the stat value for this MtMessageResDetail.
     * 
     * @param stat
     */
    public void setStat(java.lang.String stat) {
        this.stat = stat;
    }


    /**
     * Gets the statDes value for this MtMessageResDetail.
     * 
     * @return statDes
     */
    public java.lang.String getStatDes() {
        return statDes;
    }


    /**
     * Sets the statDes value for this MtMessageResDetail.
     * 
     * @param statDes
     */
    public void setStatDes(java.lang.String statDes) {
        this.statDes = statDes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MtMessageResDetail)) return false;
        MtMessageResDetail other = (MtMessageResDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.phoneNumber==null && other.getPhoneNumber()==null) || 
             (this.phoneNumber!=null &&
              this.phoneNumber.equals(other.getPhoneNumber()))) &&
            ((this.stat==null && other.getStat()==null) || 
             (this.stat!=null &&
              this.stat.equals(other.getStat()))) &&
            ((this.statDes==null && other.getStatDes()==null) || 
             (this.statDes!=null &&
              this.statDes.equals(other.getStatDes())));
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
        if (getPhoneNumber() != null) {
            _hashCode += getPhoneNumber().hashCode();
        }
        if (getStat() != null) {
            _hashCode += getStat().hashCode();
        }
        if (getStatDes() != null) {
            _hashCode += getStatDes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MtMessageResDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "mtMessageResDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statDes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statDes"));
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
