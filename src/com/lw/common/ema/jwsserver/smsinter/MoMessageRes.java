/**
 * MoMessageRes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.smsinter;

public class MoMessageRes  implements java.io.Serializable {
    private com.lw.common.ema.jwsserver.smsinter.MoMessageResDetail[] resDetail;

    private java.lang.String revStat;

    private java.lang.String revStatDes;

    public MoMessageRes() {
    }

    public MoMessageRes(
           com.lw.common.ema.jwsserver.smsinter.MoMessageResDetail[] resDetail,
           java.lang.String revStat,
           java.lang.String revStatDes) {
           this.resDetail = resDetail;
           this.revStat = revStat;
           this.revStatDes = revStatDes;
    }


    /**
     * Gets the resDetail value for this MoMessageRes.
     * 
     * @return resDetail
     */
    public com.lw.common.ema.jwsserver.smsinter.MoMessageResDetail[] getResDetail() {
        return resDetail;
    }


    /**
     * Sets the resDetail value for this MoMessageRes.
     * 
     * @param resDetail
     */
    public void setResDetail(com.lw.common.ema.jwsserver.smsinter.MoMessageResDetail[] resDetail) {
        this.resDetail = resDetail;
    }

    public com.lw.common.ema.jwsserver.smsinter.MoMessageResDetail getResDetail(int i) {
        return this.resDetail[i];
    }

    public void setResDetail(int i, com.lw.common.ema.jwsserver.smsinter.MoMessageResDetail _value) {
        this.resDetail[i] = _value;
    }


    /**
     * Gets the revStat value for this MoMessageRes.
     * 
     * @return revStat
     */
    public java.lang.String getRevStat() {
        return revStat;
    }


    /**
     * Sets the revStat value for this MoMessageRes.
     * 
     * @param revStat
     */
    public void setRevStat(java.lang.String revStat) {
        this.revStat = revStat;
    }


    /**
     * Gets the revStatDes value for this MoMessageRes.
     * 
     * @return revStatDes
     */
    public java.lang.String getRevStatDes() {
        return revStatDes;
    }


    /**
     * Sets the revStatDes value for this MoMessageRes.
     * 
     * @param revStatDes
     */
    public void setRevStatDes(java.lang.String revStatDes) {
        this.revStatDes = revStatDes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MoMessageRes)) return false;
        MoMessageRes other = (MoMessageRes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resDetail==null && other.getResDetail()==null) || 
             (this.resDetail!=null &&
              java.util.Arrays.equals(this.resDetail, other.getResDetail()))) &&
            ((this.revStat==null && other.getRevStat()==null) || 
             (this.revStat!=null &&
              this.revStat.equals(other.getRevStat()))) &&
            ((this.revStatDes==null && other.getRevStatDes()==null) || 
             (this.revStatDes!=null &&
              this.revStatDes.equals(other.getRevStatDes())));
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
        if (getResDetail() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResDetail());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResDetail(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRevStat() != null) {
            _hashCode += getRevStat().hashCode();
        }
        if (getRevStatDes() != null) {
            _hashCode += getRevStatDes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MoMessageRes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "moMessageRes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "moMessageResDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revStat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "revStat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revStatDes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "revStatDes"));
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
