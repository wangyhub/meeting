/**
 * ReportMessageResDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.sms;

public class ReportMessageResDetail  implements java.io.Serializable {
    private java.lang.String phoneNumber;

    private java.lang.String smsId;

    private java.lang.String stat;

    private java.lang.String statDes;

    public ReportMessageResDetail() {
    }

    public ReportMessageResDetail(
           java.lang.String phoneNumber,
           java.lang.String smsId,
           java.lang.String stat,
           java.lang.String statDes) {
           this.phoneNumber = phoneNumber;
           this.smsId = smsId;
           this.stat = stat;
           this.statDes = statDes;
    }


    /**
     * Gets the phoneNumber value for this ReportMessageResDetail.
     * 
     * @return phoneNumber
     */
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phoneNumber value for this ReportMessageResDetail.
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Gets the smsId value for this ReportMessageResDetail.
     * 
     * @return smsId
     */
    public java.lang.String getSmsId() {
        return smsId;
    }


    /**
     * Sets the smsId value for this ReportMessageResDetail.
     * 
     * @param smsId
     */
    public void setSmsId(java.lang.String smsId) {
        this.smsId = smsId;
    }


    /**
     * Gets the stat value for this ReportMessageResDetail.
     * 
     * @return stat
     */
    public java.lang.String getStat() {
        return stat;
    }


    /**
     * Sets the stat value for this ReportMessageResDetail.
     * 
     * @param stat
     */
    public void setStat(java.lang.String stat) {
        this.stat = stat;
    }


    /**
     * Gets the statDes value for this ReportMessageResDetail.
     * 
     * @return statDes
     */
    public java.lang.String getStatDes() {
        return statDes;
    }


    /**
     * Sets the statDes value for this ReportMessageResDetail.
     * 
     * @param statDes
     */
    public void setStatDes(java.lang.String statDes) {
        this.statDes = statDes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportMessageResDetail)) return false;
        ReportMessageResDetail other = (ReportMessageResDetail) obj;
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
            ((this.smsId==null && other.getSmsId()==null) || 
             (this.smsId!=null &&
              this.smsId.equals(other.getSmsId()))) &&
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
        if (getSmsId() != null) {
            _hashCode += getSmsId().hashCode();
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
        new org.apache.axis.description.TypeDesc(ReportMessageResDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "reportMessageResDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
