/**
 * GetIdFromNameAndVersion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class GetIdFromNameAndVersion  implements java.io.Serializable {
    private java.lang.String blobName;

    private long version;

    public GetIdFromNameAndVersion() {
    }

    public GetIdFromNameAndVersion(
           java.lang.String blobName,
           long version) {
           this.blobName = blobName;
           this.version = version;
    }


    /**
     * Gets the blobName value for this GetIdFromNameAndVersion.
     * 
     * @return blobName
     */
    public java.lang.String getBlobName() {
        return blobName;
    }


    /**
     * Sets the blobName value for this GetIdFromNameAndVersion.
     * 
     * @param blobName
     */
    public void setBlobName(java.lang.String blobName) {
        this.blobName = blobName;
    }


    /**
     * Gets the version value for this GetIdFromNameAndVersion.
     * 
     * @return version
     */
    public long getVersion() {
        return version;
    }


    /**
     * Sets the version value for this GetIdFromNameAndVersion.
     * 
     * @param version
     */
    public void setVersion(long version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetIdFromNameAndVersion)) return false;
        GetIdFromNameAndVersion other = (GetIdFromNameAndVersion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.blobName==null && other.getBlobName()==null) || 
             (this.blobName!=null &&
              this.blobName.equals(other.getBlobName()))) &&
            this.version == other.getVersion();
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
        if (getBlobName() != null) {
            _hashCode += getBlobName().hashCode();
        }
        _hashCode += new Long(getVersion()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetIdFromNameAndVersion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", ">getIdFromNameAndVersion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blobName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "blobName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
