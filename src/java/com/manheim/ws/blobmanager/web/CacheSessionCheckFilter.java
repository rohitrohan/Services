/*
 * CacheSessionCheckFilter.java
 *
 * Created on August 22, 2006, 2:01 PM
 */

package com.manheim.ws.blobmanager.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author  rcooper
 * @version
 */

public class CacheSessionCheckFilter implements Filter {
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;
    
    public CacheSessionCheckFilter() {
    }
   
    /**
     *
     * @param request The servlet request we are processing
     * @param result The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
       
        
      
        
        Throwable problem = null;
        
        try {
            chain.doFilter(request, response);
        }
        catch(Throwable t) {
        //
        // If an exception is thrown somewhere down the filter chain,
        // we still want to execute our after processing, and then
        // rethrow the problem after that.
        //
        problem = t;
        t.printStackTrace();
        }
        
      
        throw new ServletException(problem);
    }
    
    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }
    
    
    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        
        this.filterConfig = filterConfig;
    }
    
    /**
     * Destroy method for this filter
     *
     */
    public void destroy() {
    }
    
    
    /**
     * Init method for this filter
     *
     */
    public void init(FilterConfig filterConfig) {
        
        this.filterConfig = filterConfig;
        
    }
    
    /**
     * Return a String representation of this object.
     */
    public String toString() {
        
        if (filterConfig == null) return ("CacheSessionCheckFilter()");
        StringBuffer sb = new StringBuffer("CacheSessionCheckFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
        
    }
}    
    