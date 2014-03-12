/**
 * Store.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class Store  implements java.io.Serializable {
    private com.manheim.webservices.Source in;

    private java.lang.String name;

    private com.manheim.webservices.BlobMetaData meta;

    private java.lang.String blobType;

    private java.lang.String transactionId;

    private java.lang.String expiryPolicy;

    public Store() {
    }

    public Store(
           com.manheim.webservices.Source in,
           java.lang.String name,
           com.manheim.webservices.BlobMetaData meta,
           java.lang.String blobType,
           java.lang.String transactionId,
           java.lang.String expiryPolicy) {
           this.in = in;
           this.name = name;
           this.meta = meta;
           this.blobType = blobType;
           this.transactionId = transactionId;
           this.expiryPolicy = expiryPolicy;
    }


    /**
     * Gets the in value for this Store.
     * 
     * @return in
     */
    public com.manheim.webservices.Source getIn() {
        return in;
    }


    /**
     * Sets the in value for this Store.
     * 
     * @param in
     */
    public void setIn(com.manheim.webservices.Source in) {
        this.in = in;
    }


    /**
     * Gets the name value for this Store.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Store.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the meta value for this Store.
     * 
     * @return meta
     */
    public com.manheim.webservices.BlobMetaData getMeta() {
        return meta;
    }


    /**
     * Sets the meta value for this Store.
     * 
     * @param meta
     */
    public void setMeta(com.manheim.webservices.BlobMetaData meta) {
        this.meta = meta;
    }


    /**
     * Gets the blobType value for this Store.
     * 
     * @return blobType
     */
    public java.lang.String getBlobType() {
        return blobType;
    }


    /**
     * Sets the blobType value for this Store.
     * 
     * @param blobType
     */
    public void setBlobType(java.lang.String blobType) {
        this.blobType = blobType;
    }


    /**
     * Gets the transactionId value for this Store.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this Store.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the expiryPolicy value for this Store.
     * 
     * @return expiryPolicy
     */
    public java.lang.String getExpiryPolicy() {
        return expiryPolicy;
    }


    /**
     * Sets the expiryPolicy value for this Store.
     * 
     * @param expiryPolicy
     */
    public void setExpiryPolicy(java.lang.String expiryPolicy) {
        this.expiryPolicy = expiryPolicy;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Store)) return false;
        Store other = (Store) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.in==null && other.getIn()==null) || 
             (this.in!=null &&
              this.in.equals(other.getIn()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.meta==null && other.getMeta()==null) || 
             (this.meta!=null &&
              this.meta.equals(other.getMeta()))) &&
            ((this.blobType==null && other.getBlobType()==null) || 
             (this.blobType!=null &&
              this.blobType.equals(other.getBlobType()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId()))) &&
            ((this.expiryPolicy==null && other.getExpiryPolicy()==null) || 
             (this.expiryPolicy!=null &&
              this.expiryPolicy.equals(other.getExpiryPolicy())));
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
        if (getIn() != null) {
            _hashCode += getIn().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getMeta() != null) {
            _hashCode += getMeta().hashCode();
        }
        if (getBlobType() != null) {
            _hashCode += getBlobType().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        if (getExpiryPolicy() != null) {
            _hashCode += getExpiryPolicy().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Store.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", ">store"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("in");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "in"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", "Source"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "meta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", "BlobMetaData"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blobType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "blobType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "transactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiryPolicy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "expiryPolicy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
