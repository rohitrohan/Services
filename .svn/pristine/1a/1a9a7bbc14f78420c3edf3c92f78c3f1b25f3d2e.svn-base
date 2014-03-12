/*
 * FindByMetadataServlet.java
 *
 * Created on April 10, 2006, 11:00 AM
 */
package com.manheim.ws.blobmanager.web;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.manheim.blobmanager.BlobManager;
import com.manheim.blobmanager.BlobMetadata;
import com.manheim.blobmanager.data.databean.Blob;


/**
 *
 * @author rcooper
 * @version
 */
public class FindByMetadataServlet extends FileServlet implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    private static final Logger LOG = Logger.getLogger(FindByMetadataServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Connection conn = null;
        

        Enumeration enu = request.getParameterNames();
        BlobMetadata mt = new BlobMetadata();

        while(enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            LOG.debug("FindByMetadataServlet:processRequest - name = " + name);
            mt.put(name, request.getParameter(name));
        }
        BlobManager bm = null;
        try {
            bm = FileServlet.createManager();
            Blob b = bm.getBlobByMetadata(mt);
            if(b == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().println(b.getName());
            }

           //TODO: Handle this a little more gracefully since its probably failing due
           //to db not present
        } catch(Exception e) {
            throw new ServletException(e);
        } finally {
            try {
            	if(bm.getConnection() != null)
            		bm.getConnection().close();
            } catch(SQLException se) {
               //;
            }
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
