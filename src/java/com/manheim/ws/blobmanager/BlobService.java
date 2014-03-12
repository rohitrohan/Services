/*
 * BlobManager.java
 *
 * Created on March 28, 2006, 12:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.manheim.ws.blobmanager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Set;

import org.apache.log4j.Logger;

import com.manheim.blobmanager.BlobManager;
import com.manheim.blobmanager.BlobMetadata;
import com.manheim.blobmanager.data.databean.Blob;
import com.manheim.db.DataSourceAccess;
import com.manheim.ws.core.application.ApplicationContext;
import com.manheim.ws.core.application.ApplicationContextFactory;
import com.manheim.ws.core.application.Attachment;

/**
 *
 * @author rcooper
 */
public class BlobService {
	private static final Logger LOG = Logger.getLogger( BlobService.class );

	private static BlobManager createManager() {
		LOG.debug("createManager");
		try{
			BlobManager bm = new BlobManager();
			// Changed for Jira BM:-205
			//these 2 lines should be removed, since the storage type is controlled by the JVM settings, the BlobManager constructor is already doing that. 
			//we can not inject new values here again, otherwise, we again could run into the inconsistent issue. 
//			String dataSource = "blobs-" + (System.getProperty("system.filepath", "true").toLowerCase().equalsIgnoreCase("true") ? "file" : "blobdata");
//			bm.setConnection( DataSourceAccess.getInstance().getDataSource( dataSource).getConnection() );
//			System.out.println("***************DataSource="+dataSource);			
//			System.out.println("***Prop system.filepath="+System.getProperty("system.filepath", "true"));
			return bm;
		} catch (Exception e){
			LOG.error( "Could not instantiate the BlobManager!", e );			
			// @TODO email someone?
		}
		return null;
	}

	/** Creates a new instance of BlobManager */
	public BlobService() {
		super();
	}
	/**
	 * Find the most recent version of the named blob.
	 * 
	 * @param blobName
	 * @return the matching BlobId
	 * @throws BlobException
	 */
	public String getIdFromName(String blobName) throws BlobException {
		BlobManager bm = null;
		try{
			LOG.debug("getIdFromName invoked");
			bm = BlobService.createManager();
			return bm.getIdFromName(  blobName );
		} catch(Exception e) {
			LOG.warn( "Unexpected exception.", e);
			throw new BlobException( e );
		}
		finally
		{
			this.closeConnection(bm);
		}
	}

	/**
	 * Find the specified version of the named blob.
	 * 
	 * @param blobName
	 * @return the matching BlobId
	 * @throws BlobException
	 */
	public String getIdFromNameAndVersion(String blobName, long version) throws BlobException {
		LOG.debug("getIdFromNameAndVersion invoked");
		BlobManager bm = BlobService.createManager();
		try {
			//bm.getConnection().close();
			return bm.getIdFromName(  blobName, version );
		} catch (Exception e) {
			LOG.warn( "Unexpected exception.", e);
			throw new BlobException( e );
		}
		finally
		{
			this.closeConnection(bm);
		}            
	}

	/**
	 * Store a blob, and return a reference for finding it later.
	 * 
	 * @param in
	 *            the InputStream to be saved.
	 * @param name
	 *            the name of an existing blob that should be updated with a new version, or null if this is a new blob.
	 * @param meta
	 *            additional keywords to associate with the blob, for later searching.
	 * @param blobType
	 *            the type of blob file being saved (txt, pdf, jpg, etc)
	 * @return a reference to find the blob later.
	 * @throws BlobException
	 */
	public Blob store(Source in, String name, BlobMetadata meta, String blobType, String transactionId, String expiryPolicy) throws BlobException {
		BlobManager bm = null;
		try{

			System.err.println("Testing for Break Points");
			LOG.debug("store invoked");
			ApplicationContext ctx = ApplicationContextFactory.getInstance();
			bm = BlobService.createManager();
			LOG.debug("connection found");
			InputStream is = null;
			if( in.getUrl() != null ){
				LOG.debug("getUrl");
				is = new URL( in.getUrl() ).openStream();
			} else if( in.getData() != null ){ 
				LOG.debug("getdata");
				is = new ByteArrayInputStream( in.getData() );
			} else if( in.getAttachmentId() != null ) {
				LOG.debug("get attachment");
				Attachment a[] = ctx.receiveAttachments();
				for( int i =0; i < a.length ; i++ ){
					if( a[i].getId().equals( in.getAttachmentId() ) ){
						is = a[i].getContent();
						break;
					}
				}
			} else {
				throw new BlobException( "No Source Data Found." );
			} 
			if( is == null ){
				throw new BlobException( "No Source Data Found." );
			}

			System.out.println("Value of the return is:");
			LOG.debug("call store the metadata");
			return bm.store(is, name, meta, blobType, transactionId, expiryPolicy) ;
		} catch(Exception e) {
			LOG.warn( "Unexpected exception.", e);
			throw new BlobException( e );
		}
		finally
		{
			this.closeConnection(bm);
		}
	}

	public Blob getById(final String blobId) throws BlobException {
		BlobManager bm = null;
		try{
			LOG.debug("getById invoked");
			bm = BlobService.createManager();
			return bm.getById(  blobId );
		} catch(Exception e) {
			LOG.warn( "Unexpected exception.", e);
			throw new BlobException( e );
		}
		finally
		{
			this.closeConnection(bm);
		}
	}
	/**
	 * Find all blobs that match a specified metadata. Matches are returned as a Set of BlobId objects.
	 * 
	 * @param meta
	 *            the key/value pairs to search for.
	 * @return Set of matching BlobId objects.
	 * @throws SQLException
	 */
	public Set getBlobsByMetadata(BlobMetadata meta) throws BlobException {
		BlobManager bm = null;
		try{
			LOG.debug("getBlobsByMetaData invoked");
			bm = BlobService.createManager();
			return bm.getBlobsByMetadata(meta);
		} catch(Exception e) {
			LOG.warn( "Unexpected exception.", e);
			if( e instanceof BlobException ){
				throw (BlobException) e;
			} else {
				throw new BlobException( e );
			}
		}
		finally
		{
			this.closeConnection(bm);
		}
	}

	/**
	 * Find the specific blob that match a specified metadata. Matches are returned as a Blob.
	 * 
	 * @param meta
	 *            the key/value pairs to search for.
	 * @return Set of matching BlobId objects.
	 * @throws SQLException
	 */
	public Blob getBlobByMetadata(BlobMetadata meta) throws BlobException {
		BlobManager bm = null;
		try{
			LOG.debug("getBlobByMetaData invoked");
			bm = BlobService.createManager();
			return bm.getBlobByMetadata(meta);
		} catch(Exception e) {
			LOG.warn( "Unexpected exception.", e);
			throw new BlobException( e );
		}
		finally
		{
			this.closeConnection(bm);
		}
	}

	/**
	 * Common function to close the connection
	 * 
	 * @param Blobmanager object
	 *            
	 * @return void
	 * @throws SQLException
	 */
	private void closeConnection(BlobManager bm)throws BlobException    
	{
		try {
			if(bm.getConnection() != null)
			{
				bm.getConnection().close();
			}
		} catch (SQLException sqe) {
			throw new BlobException(
					"Error while closing connection. "+sqe.getMessage());
		}
	}
}
