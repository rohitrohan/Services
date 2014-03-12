/**
 * GetIdFromNameAndVersionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class GetIdFromNameAndVersionResponse  implements java.io.Serializable {
    private java.lang.String getIdFromNameAndVersionReturn;

    public GetIdFromNameAndVersionResponse() {
    }

    public GetIdFromNameAndVersionResponse(
           java.lang.String getIdFromNameAndVersionReturn) {
           this.getIdFromNameAndVersionReturn = getIdFromNameAndVersionReturn;
    }


    /**
     * Gets the getIdFromNameAndVersionReturn value for this GetIdFromNameAndVersionResponse.
     * 
     * @return getIdFromNameAndVersionReturn
     */
    public java.lang.String getGetIdFromNameAndVersionReturn() {
        return getIdFromNameAndVersionReturn;
    }


    /**
     * Sets the getIdFromNameAndVersionReturn value for this GetIdFromNameAndVersionResponse.
     * 
     * @param getIdFromNameAndVersionReturn
     */
    public void setGetIdFromNameAndVersionReturn(java.lang.String getIdFromNameAndVersionReturn) {
        this.getIdFromNameAndVersionReturn = getIdFromNameAndVersionReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetIdFromNameAndVersionResponse)) return false;
        GetIdFromNameAndVersionResponse other = (GetIdFromNameAndVersionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getIdFromNameAndVersionReturn==null && other.getGetIdFromNameAndVersionReturn()==null) || 
             (this.getIdFromNameAndVersionReturn!=null &&
              this.getIdFromNameAndVersionReturn.equals(other.getGetIdFromNameAndVersionReturn())));
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
        if (getGetIdFromNameAndVersionReturn() != null) {
            _hashCode += getGetIdFromNameAndVersionReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetIdFromNameAndVersionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", ">getIdFromNameAndVersionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getIdFromNameAndVersionReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "getIdFromNameAndVersionReturn"));
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
