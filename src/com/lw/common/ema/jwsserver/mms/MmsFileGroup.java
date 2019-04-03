/**
 * MmsFileGroup.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.mms;

public class MmsFileGroup  implements java.io.Serializable {
    private java.lang.String audio_FileData;

    private java.lang.String audio_FileName;

    private java.lang.String image_FileData;

    private java.lang.String image_FileName;

    private java.lang.Integer playTime;

    private java.lang.String text_FileData;

    private java.lang.String text_FileName;

    private java.lang.Integer txtPosition;

    public MmsFileGroup() {
    }

    public MmsFileGroup(
           java.lang.String audio_FileData,
           java.lang.String audio_FileName,
           java.lang.String image_FileData,
           java.lang.String image_FileName,
           java.lang.Integer playTime,
           java.lang.String text_FileData,
           java.lang.String text_FileName,
           java.lang.Integer txtPosition) {
           this.audio_FileData = audio_FileData;
           this.audio_FileName = audio_FileName;
           this.image_FileData = image_FileData;
           this.image_FileName = image_FileName;
           this.playTime = playTime;
           this.text_FileData = text_FileData;
           this.text_FileName = text_FileName;
           this.txtPosition = txtPosition;
    }


    /**
     * Gets the audio_FileData value for this MmsFileGroup.
     * 
     * @return audio_FileData
     */
    public java.lang.String getAudio_FileData() {
        return audio_FileData;
    }


    /**
     * Sets the audio_FileData value for this MmsFileGroup.
     * 
     * @param audio_FileData
     */
    public void setAudio_FileData(java.lang.String audio_FileData) {
        this.audio_FileData = audio_FileData;
    }


    /**
     * Gets the audio_FileName value for this MmsFileGroup.
     * 
     * @return audio_FileName
     */
    public java.lang.String getAudio_FileName() {
        return audio_FileName;
    }


    /**
     * Sets the audio_FileName value for this MmsFileGroup.
     * 
     * @param audio_FileName
     */
    public void setAudio_FileName(java.lang.String audio_FileName) {
        this.audio_FileName = audio_FileName;
    }


    /**
     * Gets the image_FileData value for this MmsFileGroup.
     * 
     * @return image_FileData
     */
    public java.lang.String getImage_FileData() {
        return image_FileData;
    }


    /**
     * Sets the image_FileData value for this MmsFileGroup.
     * 
     * @param image_FileData
     */
    public void setImage_FileData(java.lang.String image_FileData) {
        this.image_FileData = image_FileData;
    }


    /**
     * Gets the image_FileName value for this MmsFileGroup.
     * 
     * @return image_FileName
     */
    public java.lang.String getImage_FileName() {
        return image_FileName;
    }


    /**
     * Sets the image_FileName value for this MmsFileGroup.
     * 
     * @param image_FileName
     */
    public void setImage_FileName(java.lang.String image_FileName) {
        this.image_FileName = image_FileName;
    }


    /**
     * Gets the playTime value for this MmsFileGroup.
     * 
     * @return playTime
     */
    public java.lang.Integer getPlayTime() {
        return playTime;
    }


    /**
     * Sets the playTime value for this MmsFileGroup.
     * 
     * @param playTime
     */
    public void setPlayTime(java.lang.Integer playTime) {
        this.playTime = playTime;
    }


    /**
     * Gets the text_FileData value for this MmsFileGroup.
     * 
     * @return text_FileData
     */
    public java.lang.String getText_FileData() {
        return text_FileData;
    }


    /**
     * Sets the text_FileData value for this MmsFileGroup.
     * 
     * @param text_FileData
     */
    public void setText_FileData(java.lang.String text_FileData) {
        this.text_FileData = text_FileData;
    }


    /**
     * Gets the text_FileName value for this MmsFileGroup.
     * 
     * @return text_FileName
     */
    public java.lang.String getText_FileName() {
        return text_FileName;
    }


    /**
     * Sets the text_FileName value for this MmsFileGroup.
     * 
     * @param text_FileName
     */
    public void setText_FileName(java.lang.String text_FileName) {
        this.text_FileName = text_FileName;
    }


    /**
     * Gets the txtPosition value for this MmsFileGroup.
     * 
     * @return txtPosition
     */
    public java.lang.Integer getTxtPosition() {
        return txtPosition;
    }


    /**
     * Sets the txtPosition value for this MmsFileGroup.
     * 
     * @param txtPosition
     */
    public void setTxtPosition(java.lang.Integer txtPosition) {
        this.txtPosition = txtPosition;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MmsFileGroup)) return false;
        MmsFileGroup other = (MmsFileGroup) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.audio_FileData==null && other.getAudio_FileData()==null) || 
             (this.audio_FileData!=null &&
              this.audio_FileData.equals(other.getAudio_FileData()))) &&
            ((this.audio_FileName==null && other.getAudio_FileName()==null) || 
             (this.audio_FileName!=null &&
              this.audio_FileName.equals(other.getAudio_FileName()))) &&
            ((this.image_FileData==null && other.getImage_FileData()==null) || 
             (this.image_FileData!=null &&
              this.image_FileData.equals(other.getImage_FileData()))) &&
            ((this.image_FileName==null && other.getImage_FileName()==null) || 
             (this.image_FileName!=null &&
              this.image_FileName.equals(other.getImage_FileName()))) &&
            ((this.playTime==null && other.getPlayTime()==null) || 
             (this.playTime!=null &&
              this.playTime.equals(other.getPlayTime()))) &&
            ((this.text_FileData==null && other.getText_FileData()==null) || 
             (this.text_FileData!=null &&
              this.text_FileData.equals(other.getText_FileData()))) &&
            ((this.text_FileName==null && other.getText_FileName()==null) || 
             (this.text_FileName!=null &&
              this.text_FileName.equals(other.getText_FileName()))) &&
            ((this.txtPosition==null && other.getTxtPosition()==null) || 
             (this.txtPosition!=null &&
              this.txtPosition.equals(other.getTxtPosition())));
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
        if (getAudio_FileData() != null) {
            _hashCode += getAudio_FileData().hashCode();
        }
        if (getAudio_FileName() != null) {
            _hashCode += getAudio_FileName().hashCode();
        }
        if (getImage_FileData() != null) {
            _hashCode += getImage_FileData().hashCode();
        }
        if (getImage_FileName() != null) {
            _hashCode += getImage_FileName().hashCode();
        }
        if (getPlayTime() != null) {
            _hashCode += getPlayTime().hashCode();
        }
        if (getText_FileData() != null) {
            _hashCode += getText_FileData().hashCode();
        }
        if (getText_FileName() != null) {
            _hashCode += getText_FileName().hashCode();
        }
        if (getTxtPosition() != null) {
            _hashCode += getTxtPosition().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MmsFileGroup.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mms.jwsserver.server.ema.ctc.com/", "mmsFileGroup"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("audio_FileData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "audio_FileData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("audio_FileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "audio_FileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("image_FileData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "image_FileData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("image_FileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "image_FileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("playTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "playTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text_FileData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "text_FileData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text_FileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "text_FileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txtPosition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "txtPosition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
