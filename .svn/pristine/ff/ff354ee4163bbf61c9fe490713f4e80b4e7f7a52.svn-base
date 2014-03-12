/*
 * ImageServlet.java
 *
 * Created on March 26, 2006, 3:20 PM
 */

package com.manheim.ws.blobmanager.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.manheim.blobmanager.BlobManager;
import com.manheim.blobmanager.BlobMetadata;
import com.manheim.blobmanager.data.databean.Blob;
import com.manheim.image.Resize;

/**
 *
 * @author rcooper
 * @version
 */
public class ImageServlet extends FileServlet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger( ImageServlet.class );
	private static final HashMap EXTENSION_TO_ENCODING_LOOKUP = new HashMap();
	static{
		EXTENSION_TO_ENCODING_LOOKUP.put( ".jpg", "JPEG" );
		EXTENSION_TO_ENCODING_LOOKUP.put( ".jpeg", "JPEG" );
		EXTENSION_TO_ENCODING_LOOKUP.put( ".gif", "GIF" );
		EXTENSION_TO_ENCODING_LOOKUP.put( ".png", "PNG" );
		EXTENSION_TO_ENCODING_LOOKUP.put( ".wbmp", "WBMP" );
		EXTENSION_TO_ENCODING_LOOKUP.put( ".tiff", "TIFF" );
		EXTENSION_TO_ENCODING_LOOKUP.put( ".tif", "TIFF" );
	}
	/** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		ServletContext application = this.getServletContext();
		String[] values = pathInfo.substring( 1, pathInfo.length() ).split( "/");
		int width = Integer.parseInt( values[0] );
		int height = Integer.parseInt( values[1] );		
		int dot = values[2].lastIndexOf('.');
		dot = (dot == -1 )?  values[2].length() : dot;		
		String blobName = values[2].substring( 0, dot );		
		String extension = values[2].substring( dot, values[2].length() ).toLowerCase();		
		String url = "";
		BlobManager bm = null;
		InputStream source = null;
		//String domainName=null;
		//Connection conn = null;
		Blob info = null;
		String mainURL = null;
		String offlineURL = "/images/offline";
		String invalidURL = "/images/invalid";
		try{
			bm = ImageServlet.createManager();			
			if( blobName.startsWith("_") ){
				info = bm.getById(  blobName.substring(1, blobName.length()  ) );
			} else {
				info = bm.getByName( blobName );
			}
			
			if(info == null)
			{
				System.out.println("No Data in DB : ImageServlet: No file found for the requested Blob");
				if(extension == null || extension.equals(""))
					extension = ".jpg";	
				mainURL = request.getContextPath()+invalidURL+extension;
				System.out.println("ImageServlet -- Value of offlineURL is:"+mainURL);
				response.sendRedirect( mainURL );
				return;
			}
			// Do auth check..
			BlobMetadata meta = bm.getMetadata( info );

			//If this method returns false, either a security challenge has been issued
			// or the FORBIDDEN message has been returned.
			LOG.debug("check authOK");
			if( !authOk(meta, request, response) ){
				return;
			}
			
			//Added for Jira BM:- 200
			if(extension == null || extension.equals(""))
				extension = "."+info.getType();

			request.getSession().setAttribute( info.getName(), new Boolean(true));
			url = "/cache/"+ info.getName() + "/" + info.getVersion()+"/" + info.getName()+"_"+width+"_"+height+extension;
			LOG.debug("url is " + url);

		} catch(Exception e){
			System.out.println("In Servlet Exception:ImageServlet: No Data in DB for the requested Blob");
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
				Resize resize = new Resize( source, null );
				FileOutputStream fos = new FileOutputStream( file );

                //Added for Jira BM:- 200
				if(extension == null || extension.equals(""))
					extension = "."+info.getType();

				FileServlet.copyStream( resize.process( width, height, (String)EXTENSION_TO_ENCODING_LOOKUP.get(extension)),
						fos );
				fos.flush();
				fos.close();
			}catch(Exception e){
				System.out.println("In FileNotFound Exception:ImageServlet: No file found for the requested Blob");
				mainURL = request.getContextPath()+offlineURL+"."+EXTENSION_TO_ENCODING_LOOKUP.get(extension).toString().toLowerCase();
				System.out.println("ImageServlet -- Value of offlineURL is:"+mainURL);
				file.delete();
				LOG.error( e );
				response.sendRedirect( mainURL );
				try{
					bm.getConnection().close();
					return;
				} catch(Exception ee){}				
			}
		} else if( file.lastModified() < System.currentTimeMillis() - ( 24 * 60 * 60 * 1000) ){
			file.setLastModified( System.currentTimeMillis() );
		}
		//domainName = System.getProperty("system.domainname");
		//mainURL = domainName+request.getContextPath()+url;
		mainURL = request.getContextPath()+url;
		System.out.println("ImageServlet -- Value of mainURL is:"+mainURL);
		response.sendRedirect(mainURL);
		try{
			bm.getConnection().close();
		} catch(Exception e){			
		}
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
		return "Short description";
	}
	// </editor-fold>
}
