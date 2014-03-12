/*
 * FileServlet.java
 *
 * Created on March 24, 2006, 12:22 PM
 */

package com.manheim.ws.blobmanager.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.manheim.blobmanager.BlobManager;
import com.manheim.blobmanager.BlobMetadata;
import com.manheim.blobmanager.data.databean.Blob;
import com.manheim.db.DataSourceAccess;
import com.manheim.ws.blobmanager.IUser;


/**
 *
 * @author rcooper
 * @version
 */
public class FileServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger( FileServlet.class );
	protected AuthChallengeUtility authUtil = null;

	public static BlobManager createManager() {
		try{
			BlobManager bm = new BlobManager();
			//Changed for Jira BM:-205
			String dataSource = "blobs-" + (System.getProperty("system.filepath", "false").toLowerCase().equalsIgnoreCase("false") ? "blobdata" : "file");            
			bm.setConnection( DataSourceAccess.getInstance().getDataSource( dataSource).getConnection() );
			return bm;
		} catch (Exception e){
			LOG.error( "Could not instantiate the BlobManager!", e );
			// @TODO email someone?
		}
		return null;
	}

	/** Copies the data from an InputStream object to an OutputStream object.
	 * @param sourceStream The input stream to be read.
	 * @param destinationStream The output stream to be written to.
	 * @return int value of the number of bytes copied.
	 * @exception IOException from java.io calls.
	 */
	public static int copyStream(InputStream sourceStream,OutputStream destinationStream) throws IOException {
		int bytesRead = 0;
		int totalBytes = 0;
		byte[] buffer = new byte[1024];

		while(bytesRead >= 0) {
			bytesRead = sourceStream.read(buffer,0,buffer.length);

			if(bytesRead > 0) {
				destinationStream.write(buffer,0,bytesRead);
			}

			totalBytes += bytesRead;
		}

		return totalBytes;
	}

	/** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		LOG.debug("processRequest invoked");
		String pathInfo = request.getPathInfo();
		ServletContext application = this.getServletContext();
		int dot = pathInfo.lastIndexOf('.');
		dot = (dot == -1 )?  pathInfo.length() : dot;
		String blobName = pathInfo.substring( 1, dot );
		String extension = pathInfo.substring( dot, pathInfo.length() ).toLowerCase();
		String url = "";
		BlobManager bm = null;
		InputStream source = null;
		Blob info = null;
		//String domainName=null;
		String mainURL = null;
		String offlineURL = "/images/offline";
		String invalidURL = "/images/invalid";
		try{			
			bm = FileServlet.createManager();						
			if( blobName.startsWith("_") ){
				info = bm.getById(  blobName.substring(1, blobName.length()  ) );
			} else {
				info = bm.getByName( blobName );
			}
			
			if(info == null)
			{
				System.out.println("No Data in DB : FileServlet: No file found for the requested Blob");
				if(extension == null || extension.equals(""))
					extension = ".jpg";	
				mainURL = request.getContextPath()+invalidURL+extension;
				System.out.println("FileServlet -- Value of offlineURL is:"+mainURL);
				response.sendRedirect( mainURL );
				return;
			}
			url = "/cache/"+ info.getName() + "/" + info.getVersion()+"/" + info.getName()+"."+info.getType();
			
			// Do auth check..
			BlobMetadata meta = bm.getMetadata( info );
			
			LOG.debug("check for auth ok");
			if( !authOk(meta, request, response) ){
				try{
					bm.getConnection().close();
				} catch(Exception e){}
				return;
			}
			request.getSession().setAttribute( info.getName(), new Boolean(true));

		} catch(Exception e){			
			System.out.println("In Servlet Exception:FileServlet: No Data in DB for the requested Blob");
			LOG.error( "Exception getting info." , e );
			response.setStatus( HttpServletResponse.SC_NOT_FOUND );
			try{
				bm.getConnection().close();
			} catch(Exception ee){}
			return;
		}
		File file = new File( application.getRealPath( url ) );
		if( ! file.exists() ){
			try{
				file.getParentFile().mkdirs();
				source = bm.retrieveOriginalById(info.getId() );
				FileOutputStream fos = new FileOutputStream( file );

				FileServlet.copyStream( source , fos );
				fos.flush();
				fos.close();

			}catch(Exception e){
				System.out.println("In FileNotFound Exception:FileServlet: No file found for the requested Blob");
				mainURL = request.getContextPath()+offlineURL+"."+info.getType();
				System.out.println("FileServlet -- Value of offlineURL is:"+mainURL);
				file.delete();
				LOG.error( e );
				response.sendRedirect( mainURL );
				try{
					bm.getConnection().close();
					return;
				} catch(Exception ee){}
				throw new ServletException( e );		
			}
		} else if( file.lastModified() < System.currentTimeMillis() - ( 24 * 60 * 60 * 1000) ){
			file.setLastModified( System.currentTimeMillis() );
		}
		//domainName = System.getProperty("system.domainname");
		//mainURL = domainName+request.getContextPath()+url;
		mainURL = request.getContextPath()+url;
		System.out.println("FileServlet -- Value of mainURL is:"+mainURL);
		response.sendRedirect( mainURL );
		try{
			bm.getConnection().close();
		} catch(Exception e){}

	}

	protected boolean authOk( BlobMetadata meta, HttpServletRequest request, HttpServletResponse response){
		LOG.debug("authOk invoked");
		if( meta.get( "_REQUIRE_LOGIN") != null ){
			try{
				IUser u = authUtil.authenticate( request, response );
				if( u == null ) {
					response.setStatus( HttpServletResponse.SC_FORBIDDEN );
					return false;
				}
			} catch( BreakException e ){
				return false;
			}
			return true;
		} else if( meta.get("_REQUIRE_USER") != null ){
			StringTokenizer tok = new StringTokenizer( meta.get("_REQUIRE_USER"), "," );
			try{
				IUser u = authUtil.authenticate( request, response );
				if( u == null ) {
					response.setStatus( HttpServletResponse.SC_FORBIDDEN );
				}
				ArrayList users = new ArrayList();
				while( tok.hasMoreTokens() ){
					users.add( tok.nextToken() );
				}
				if( !users.contains( u.getName() ) ){
					response.setStatus( HttpServletResponse.SC_FORBIDDEN );
					return false;
				}
			} catch( BreakException e ){
				return false;
			}
			return true;
		} else if( meta.get("_REQUIRE_ROLE") != null ){
			StringTokenizer tok = new StringTokenizer( meta.get("_REQUIRE_ROLE"), "," );
			ArrayList roles = new ArrayList();
			while( tok.hasMoreTokens() ){
				roles.add( tok.nextToken() );
			}
			try{
				IUser u = authUtil.authenticate( request, response );
				if( u == null ) {
					response.setStatus( HttpServletResponse.SC_FORBIDDEN );
				}
				if( !FileServlet.userInRoles( u, (String[]) roles.toArray( new String[ roles.size()] )) ){
					response.setStatus( HttpServletResponse.SC_FORBIDDEN );
					return false;
				}
			} catch( BreakException e ){
				return false;
			}
			return true;
		}
		return true;
	}

	protected static boolean userInRoles( IUser user, String[] role ){
		for( int i=0; i < role.length ; i++ ){
			for( int j=0; j < user.getRoles().length ; j++ ){
				if( role[i].equals( user.getRoles()[j])){
					return true;
				}
			}
		}
		return false;
	}
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/** Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}

	/** Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}

	/** Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "The regular versioned file service.";
	}
	// </editor-fold>

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try{
			authUtil = new AuthChallengeUtility();
		} catch(Exception e){
			throw new ServletException( e );
		}
	}
}
