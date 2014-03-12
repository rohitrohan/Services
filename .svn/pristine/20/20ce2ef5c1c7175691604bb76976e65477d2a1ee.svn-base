/**
 * GetBlobsByMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class GetBlobsByMetadata  implements java.io.Serializable {
    private com.manheim.webservices.BlobMetaData meta;

    public GetBlobsByMetadata() {
    }

    public GetBlobsByMetadata(
           com.manheim.webservices.BlobMetaData meta) {
           this.meta = meta;
    }


    /**
     * Gets the meta value for this GetBlobsByMetadata.
     * 
     * @return meta
     */
    public com.manheim.webservices.BlobMetaData getMeta() {
        return meta;
    }


    /**
     * Sets the meta value for this GetBlobsByMetadata.
     * 
     * @param meta
     */
    public void setMeta(com.manheim.webservices.BlobMetaData meta) {
        this.meta = meta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBlobsByMetadata)) return false;
        GetBlobsByMetadata other = (GetBlobsByMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.meta==null && other.getMeta()==null) || 
             (this.meta!=null &&
              this.meta.equals(other.getMeta())));
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
        if (getMeta() != null) {
            _hashCode += getMeta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBlobsByMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", ">getBlobsByMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "meta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", "BlobMetaData"));
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
