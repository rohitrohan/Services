/**
 * BlobServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public interface BlobServiceService extends javax.xml.rpc.Service {
    public java.lang.String getBlobServiceAddress();

    public com.manheim.webservices.BlobService getBlobService() throws javax.xml.rpc.ServiceException;

    public com.manheim.webservices.BlobService getBlobService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
