/**
 * BlobService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manheim.webservices;

public interface BlobService extends java.rmi.Remote {
    public com.manheim.webservices.Blob store(com.manheim.webservices.Source in, java.lang.String name, com.manheim.webservices.BlobMetaData meta, java.lang.String blobType, java.lang.String transactionId, java.lang.String expiryPolicy) throws java.rmi.RemoteException, com.manheim.blobmanager.exceptions.BlobException;
    public java.lang.String getIdFromName(java.lang.String blobName) throws java.rmi.RemoteException, com.manheim.blobmanager.exceptions.BlobException;
    public java.lang.String getIdFromNameAndVersion(java.lang.String blobName, long version) throws java.rmi.RemoteException, com.manheim.blobmanager.exceptions.BlobException;
    public com.manheim.webservices.Blob getById(java.lang.String blobId) throws java.rmi.RemoteException, com.manheim.blobmanager.exceptions.BlobException;
    public java.lang.Object[] getBlobsByMetadata(com.manheim.webservices.BlobMetaData meta) throws java.rmi.RemoteException, com.manheim.blobmanager.exceptions.BlobException;
    public com.manheim.webservices.Blob getBlobByMetadata(com.manheim.webservices.BlobMetaData meta) throws java.rmi.RemoteException, com.manheim.blobmanager.exceptions.BlobException;
}
