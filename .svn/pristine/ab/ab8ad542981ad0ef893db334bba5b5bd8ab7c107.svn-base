/*
 * SimpleUpload.java
 *
 * Created on August 29, 2006, 9:46 AM
 */

package com.manheim.ws.blobmanager.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
public class SimpleUpload extends HttpServlet implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger( SimpleUpload.class );
    private static AuthChallengeUtility AUTH_UTIL = null;
    
    private static BlobManager createManager() {
        try{
            BlobManager bm = new BlobManager();
            String dataSource = "blobs-" + (System.getProperty("system.filepath", "blobdata").toLowerCase().equalsIgnoreCase("blobdata") ? "blobdata" : "file");
            bm.setConnection( DataSourceAccess.getInstance().getDataSource( dataSource).getConnection() );
            return bm;
        } catch (Exception e){
            LOG.error( "Could not instantiate the BlobManager!", e );
            // @TODO email someone?
        }
        return null;
    }
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        IUser u = null;
        try {
            u = AUTH_UTIL.authenticate( request, response );
        } catch( BreakException be ){
            return;
        } 
        if( u == null ){
            response.setStatus( HttpServletResponse.SC_FORBIDDEN );
            return;
        }
        
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        BlobManager bm = null;
        try{
            bm = createManager();
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List  items = upload.parseRequest(request);
            Iterator it = items.iterator();
            BlobMetadata md = new BlobMetadata();
            InputStream is = null;
            while( it.hasNext() ){
                FileItem item = (FileItem) it.next();
                if( item.isFormField() ){
                    md.put( item.getName(), item.getString() );
                } else {
                    is = item.getInputStream();
                }
            }
            Blob b = bm.store(is, md, "jpg" );
            out.println( b.getName() );
        }catch(Exception e){
            throw new ServletException(e);
        } finally{
            try{
                bm.getConnection().close();
            } catch(Exception e){
                ;//let it go by
            }
        }
        out.close();
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
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try{
            AUTH_UTIL = new AuthChallengeUtility();
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
}
