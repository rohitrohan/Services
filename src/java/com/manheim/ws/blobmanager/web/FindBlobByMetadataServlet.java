/*
 * FindBlobByMetadataServlet.java
 *
 * Created on September 17, 2007
 */
package com.manheim.ws.blobmanager.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
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
public class FindBlobByMetadataServlet extends FileServlet implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    private static final Logger LOG = Logger.getLogger(FindByMetadataServlet.class);
    private final String WIDTH="width";
    private final String HEIGHT="height";
    private final String IMAGE_CTX_PATH="/image/";
    private final String FILE_CTX_PATH="/file/";
    private final String INTERNAL_CTX_PATH="/internal";    
    private final String FILE_SEPARATOR="/";
    private final String DOT_SEPARATOR=".";
    private final String UNDERSCORE="_";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
    	Enumeration enu = request.getParameterNames();
        BlobMetadata mt = new BlobMetadata();
        StringBuffer url = new StringBuffer();
        String urlWidth = null;
        String urlHeight = null;
        String domainName=null;
        while(enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            LOG.debug("FindByMetadataServlet:processRequest - name = " + name);
            if(WIDTH.equals(name)) 
            {
            	urlWidth=request.getParameter(name);
            }
            else if(HEIGHT.equals(name))
            {
            	urlHeight=request.getParameter(name);
            }
            else {
            	mt.put(name, request.getParameter(name));
            }
        }
        
        
        BlobManager bm = null;
        try {
            bm = FileServlet.createManager();
            
            Blob b = bm.getBlobByMetadata(mt);
            if(b == null) {
            	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
            	
            	domainName = System.getProperty("system.domainname");
            	String servletPath = request.getServletPath();
            	url.append(domainName);
            	url.append(request.getContextPath());
            	if(servletPath.indexOf(INTERNAL_CTX_PATH) != -1)
            	{
            		url.append(INTERNAL_CTX_PATH);
            	}
            	
            	if(urlWidth != null & urlHeight != null)
                {
            		//URL building to redirect to the Image Servlet            		
            		
            		url.append(IMAGE_CTX_PATH);
                	url.append(urlWidth);
                	url.append(FILE_SEPARATOR);
                	url.append(urlHeight);
                	url.append(FILE_SEPARATOR);
                	url.append(UNDERSCORE);
                	url.append(b.getId());
                	url.append(DOT_SEPARATOR);
                	url.append(b.getType());
                }
            	else
            	{
            		//URL building to redirect to the File Servlet
            		url.append(FILE_CTX_PATH);
            		url.append(UNDERSCORE);
            		url.append(b.getId());
            	}
            	System.out.println("FindBlobByMetadataServlet Value of url is:"+url.toString());

            	response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("</head>");
                out.println("<body>");
                out.println("<iframe src =\""+url.toString()+"\" WIDTH=\"100%\" HEIGHT=\"100%\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\" >");
                out.println("</iframe>"); 
                out.println("</body>");
                out.println("</html>");
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
