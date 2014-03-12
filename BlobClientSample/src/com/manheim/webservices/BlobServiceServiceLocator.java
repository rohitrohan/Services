/**
 * BlobServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public class BlobServiceServiceLocator extends org.apache.axis.client.Service implements com.manheim.webservices.BlobServiceService {

    public BlobServiceServiceLocator() {
    }


    public BlobServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BlobServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BlobService
    private java.lang.String BlobService_address = "http://localhost:8080/BlobServerWebService-1.1.0/services/BlobService";

    public java.lang.String getBlobServiceAddress() {
        return BlobService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BlobServiceWSDDServiceName = "BlobService";

    public java.lang.String getBlobServiceWSDDServiceName() {
        return BlobServiceWSDDServiceName;
    }

    public void setBlobServiceWSDDServiceName(java.lang.String name) {
        BlobServiceWSDDServiceName = name;
    }

    public com.manheim.webservices.BlobService getBlobService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BlobService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBlobService(endpoint);
    }

    public com.manheim.webservices.BlobService getBlobService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.manheim.webservices.BlobServiceSoapBindingStub _stub = new com.manheim.webservices.BlobServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getBlobServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBlobServiceEndpointAddress(java.lang.String address) {
        BlobService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.manheim.webservices.BlobService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.manheim.webservices.BlobServiceSoapBindingStub _stub = new com.manheim.webservices.BlobServiceSoapBindingStub(new java.net.URL(BlobService_address), this);
                _stub.setPortName(getBlobServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BlobService".equals(inputPortName)) {
            return getBlobService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservices.manheim.com", "BlobServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservices.manheim.com", "BlobService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BlobService".equals(portName)) {
            setBlobServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
