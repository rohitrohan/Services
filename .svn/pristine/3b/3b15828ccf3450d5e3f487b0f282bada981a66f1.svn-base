/**
 * GetIdFromNameResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class GetIdFromNameResponse  implements java.io.Serializable {
    private java.lang.String getIdFromNameReturn;

    public GetIdFromNameResponse() {
    }

    public GetIdFromNameResponse(
           java.lang.String getIdFromNameReturn) {
           this.getIdFromNameReturn = getIdFromNameReturn;
    }


    /**
     * Gets the getIdFromNameReturn value for this GetIdFromNameResponse.
     * 
     * @return getIdFromNameReturn
     */
    public java.lang.String getGetIdFromNameReturn() {
        return getIdFromNameReturn;
    }


    /**
     * Sets the getIdFromNameReturn value for this GetIdFromNameResponse.
     * 
     * @param getIdFromNameReturn
     */
    public void setGetIdFromNameReturn(java.lang.String getIdFromNameReturn) {
        this.getIdFromNameReturn = getIdFromNameReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetIdFromNameResponse)) return false;
        GetIdFromNameResponse other = (GetIdFromNameResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getIdFromNameReturn==null && other.getGetIdFromNameReturn()==null) || 
             (this.getIdFromNameReturn!=null &&
              this.getIdFromNameReturn.equals(other.getGetIdFromNameReturn())));
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
        if (getGetIdFromNameReturn() != null) {
            _hashCode += getGetIdFromNameReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetIdFromNameResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.manheim.com", ">getIdFromNameResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getIdFromNameReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.manheim.com", "getIdFromNameReturn"));
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
