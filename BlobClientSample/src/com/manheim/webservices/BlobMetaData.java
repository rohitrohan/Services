/**
 * BlobMetaData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class BlobMetaData  implements java.io.Serializable {
    private com.manheim.webservices.MetaDataItem[] items;

    public BlobMetaData() {
    }

    public BlobMetaData(
           com.manheim.webservices.MetaDataItem[] items) {
           this.items = items;
    }


    /**
     * Gets the items value for this BlobMetaData.
     * 
     * @return items
     */
    public com.manheim.webservices.MetaDataItem[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this BlobMetaData.
     * 
     * @param items
     */
    public void setItems(com.manheim.webservices.MetaDataItem[] items) {
        this.items = items;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BlobMetaData)) return false;
        BlobMetaData other = (BlobMetaData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems())));
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
        if (getItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BlobMetaData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", "BlobMetaData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", "MetaDataItem"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webservices.manheim.com", "item"));
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
