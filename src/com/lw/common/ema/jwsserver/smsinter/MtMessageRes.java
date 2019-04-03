/**
 * MtMessageRes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.smsinter;

public class MtMessageRes  implements java.io.Serializable {
    private com.lw.common.ema.jwsserver.smsinter.MtMessageResDetail[] resDetail;

    private java.lang.String smsId;

    private java.lang.String subStat;

    private java.lang.String subStatDes;

    public MtMessageRes() {
    }

    public MtMessageRes(
           com.lw.common.ema.jwsserver.smsinter.MtMessageResDetail[] resDetail,
           java.lang.String smsId,
           java.lang.String subStat,
           java.lang.String subStatDes) {
           this.resDetail = resDetail;
           this.smsId = smsId;
           this.subStat = subStat;
           this.subStatDes = subStatDes;
    }


    /**
     * Gets the resDetail value for this MtMessageRes.
     * 
     * @return resDetail
     */
    public com.lw.common.ema.jwsserver.smsinter.MtMessageResDetail[] getResDetail() {
        return resDetail;
    }


    /**
     * Sets the resDetail value for this MtMessageRes.
     * 
     * @param resDetail
     */
    public void setResDetail(com.lw.common.ema.jwsserver.smsinter.MtMessageResDetail[] resDetail) {
        this.resDetail = resDetail;
    }

    public com.lw.common.ema.jwsserver.smsinter.MtMessageResDetail getResDetail(int i) {
        return this.resDetail[i];
    }

    public void setResDetail(int i, com.lw.common.ema.jwsserver.smsinter.MtMessageResDetail _value) {
        this.resDetail[i] = _value;
    }


    /**
     * Gets the smsId value for this MtMessageRes.
     * 
     * @return smsId
     */
    public java.lang.String getSmsId() {
        return smsId;
    }


    /**
     * Sets the smsId value for this MtMessageRes.
     * 
     * @param smsId
     */
    public void setSmsId(java.lang.String smsId) {
        this.smsId = smsId;
    }


    /**
     * Gets the subStat value for this MtMessageRes.
     * 
     * @return subStat
     */
    public java.lang.String getSubStat() {
        return subStat;
    }


    /**
     * Sets the subStat value for this MtMessageRes.
     * 
     * @param subStat
     */
    public void setSubStat(java.lang.String subStat) {
        this.subStat = subStat;
    }


    /**
     * Gets the subStatDes value for this MtMessageRes.
     * 
     * @return subStatDes
     */
    public java.lang.String getSubStatDes() {
        return subStatDes;
    }


    /**
     * Sets the subStatDes value for this MtMessageRes.
     * 
     * @param subStatDes
     */
    public void setSubStatDes(java.lang.String subStatDes) {
        this.subStatDes = subStatDes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MtMessageRes)) return false;
        MtMessageRes other = (MtMessageRes) obj;
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
            ((this.smsId==null && other.getSmsId()==null) || 
             (this.smsId!=null &&
              this.smsId.equals(other.getSmsId()))) &&
            ((this.subStat==null && other.getSubStat()==null) || 
             (this.subStat!=null &&
              this.subStat.equals(other.getSubStat()))) &&
            ((this.subStatDes==null && other.getSubStatDes()==null) || 
             (this.subStatDes!=null &&
              this.subStatDes.equals(other.getSubStatDes())));
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
        if (getSmsId() != null) {
            _hashCode += getSmsId().hashCode();
        }
        if (getSubStat() != null) {
            _hashCode += getSubStat().hashCode();
        }
        if (getSubStatDes() != null) {
            _hashCode += getSubStatDes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MtMessageRes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "mtMessageRes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "mtMessageResDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smsId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "smsId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subStat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subStat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subStatDes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subStatDes"));
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
