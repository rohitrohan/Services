/*
 * BreakException.java
 *
 * Created on August 22, 2006, 10:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.manheim.ws.blobmanager.web;

import java.io.Serializable;

/**
 *
 * @author rcooper
 */
public class BreakException extends Exception implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Creates a new instance of BreakException */
    public BreakException() {
        super();
    }
    
}
