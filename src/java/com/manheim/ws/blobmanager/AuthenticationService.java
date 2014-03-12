/*
 * AuthenticationService.java
 *
 * Created on August 22, 2006, 10:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.manheim.ws.blobmanager;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rcooper
 */
public interface AuthenticationService {
    
    public IUser authenticateUser(HttpServletRequest request);
    
}
