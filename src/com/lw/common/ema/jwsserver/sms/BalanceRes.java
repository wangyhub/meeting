/**
 * BalanceRes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.sms;

public class BalanceRes  implements java.io.Serializable {
    private java.lang.String demo;

    private java.lang.String revStat;

    private java.lang.String revStatDes;

    public BalanceRes() {
    }

    public BalanceRes(
           java.lang.String demo,
           java.lang.String revStat,
           java.lang.String revStatDes) {
           this.demo = demo;
           this.revStat = revStat;
           this.revStatDes = revStatDes;
    }


    /**
     * Gets the demo value for this BalanceRes.
     * 
     * @return demo
     */
    public java.lang.String getDemo() {
        return demo;
    }


    /**
     * Sets the demo value for this BalanceRes.
     * 
     * @param demo
     */
    public void setDemo(java.lang.String demo) {
        this.demo = demo;
    }


    /**
     * Gets the revStat value for this BalanceRes.
     * 
     * @return revStat
     */
    public java.lang.String getRevStat() {
        return revStat;
    }


    /**
     * Sets the revStat value for this BalanceRes.
     * 
     * @param revStat
     */
    public void setRevStat(java.lang.String revStat) {
        this.revStat = revStat;
    }


    /**
     * Gets the revStatDes value for this BalanceRes.
     * 
     * @return revStatDes
     */
    public java.lang.String getRevStatDes() {
        return revStatDes;
    }


    /**
     * Sets the revStatDes value for this BalanceRes.
     * 
     * @param revStatDes
     */
    public void setRevStatDes(java.lang.String revStatDes) {
        this.revStatDes = revStatDes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BalanceRes)) return false;
        BalanceRes other = (BalanceRes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.demo==null && other.getDemo()==null) || 
             (this.demo!=null &&
              this.demo.equals(other.getDemo()))) &&
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
        if (getDemo() != null) {
            _hashCode += getDemo().hashCode();
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
        new org.apache.axis.description.TypeDesc(BalanceRes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "balanceRes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("demo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "demo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
