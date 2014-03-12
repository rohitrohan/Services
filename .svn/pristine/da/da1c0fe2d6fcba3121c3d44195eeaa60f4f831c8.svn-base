/**
 * GetByIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class GetByIdResponse  implements java.io.Serializable {
    private com.manheim.webservices.Blob getByIdReturn;

    public GetByIdResponse() {
    }

    public GetByIdResponse(
           com.manheim.webservices.Blob getByIdReturn) {
           this.getByIdReturn = getByIdReturn;
    }


    /**
     * Gets the getByIdReturn value for this GetByIdResponse.
     * 
     * @return getByIdReturn
     */
    public com.manheim.webservices.Blob getGetByIdReturn() {
        return getByIdReturn;
    }


    /**
     * Sets the getByIdReturn value for this GetByIdResponse.
     * 
     * @param getByIdReturn
     */
    public void setGetByIdReturn(com.manheim.webservices.Blob getByIdReturn) {
        this.getByIdReturn = getByIdReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetByIdResponse)) return false;
        GetByIdResponse other = (GetByIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getByIdReturn==null && other.getGetByIdReturn()==null) || 
             (this.getByIdReturn!=null &&
              this.getByIdReturn.equals(other.getGetByIdReturn())));
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
        if (getGetByIdReturn() != null) {
            _hashCode += getGetByIdReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetByIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", ">getByIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getByIdReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "getByIdReturn"));
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
