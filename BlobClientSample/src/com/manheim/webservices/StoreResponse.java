/**
 * StoreResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class StoreResponse  implements java.io.Serializable {
    private com.manheim.webservices.Blob storeReturn;

    public StoreResponse() {
    }

    public StoreResponse(
           com.manheim.webservices.Blob storeReturn) {
           this.storeReturn = storeReturn;
    }


    /**
     * Gets the storeReturn value for this StoreResponse.
     * 
     * @return storeReturn
     */
    public com.manheim.webservices.Blob getStoreReturn() {
        return storeReturn;
    }


    /**
     * Sets the storeReturn value for this StoreResponse.
     * 
     * @param storeReturn
     */
    public void setStoreReturn(com.manheim.webservices.Blob storeReturn) {
        this.storeReturn = storeReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StoreResponse)) return false;
        StoreResponse other = (StoreResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.storeReturn==null && other.getStoreReturn()==null) || 
             (this.storeReturn!=null &&
              this.storeReturn.equals(other.getStoreReturn())));
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
        if (getStoreReturn() != null) {
            _hashCode += getStoreReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StoreResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", ">storeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storeReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "storeReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", "Blob"));
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
