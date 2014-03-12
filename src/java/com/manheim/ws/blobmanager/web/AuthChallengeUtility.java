/*
 * AuthChallengeUtility.java
 *
 * Created on August 22, 2006, 10:29 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.manheim.ws.blobmanager.web;

import com.manheim.ws.blobmanager.AuthenticationService;
import com.manheim.ws.blobmanager.IUser;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author rcooper
 */
public class AuthChallengeUtility {
    
    private static final String BASIC_AUTH_SERVER_RESPONSE_HEADER = "WWW-Authenticate";
    private static final String BASIC_AUTH_CLIENT_REQUEST_HEADER = "authorization";
    private static final String BASIC_AUTH_REALM = " Realm=\"WallyWorld\"";
    private static final Logger LOG = Logger.getLogger(AuthChallengeUtility.class);
    private static AuthenticationService AUTH_SERVICE;
    
    /** Creates a new instance of AuthChallengeUtility */
    public AuthChallengeUtility() throws Exception {
        super();
        if( AUTH_SERVICE == null){
            Properties props = new Properties();
            props.load( AuthChallengeUtility.class.getResourceAsStream( "/authentication.properties"));
            AUTH_SERVICE = (AuthenticationService) Class.forName( props.getProperty("authenticationservice") ).newInstance();
        }
    }
    
    public IUser authenticate(HttpServletRequest request, HttpServletResponse response ) throws BreakException {
        // check if Basic Auth header is present, if not then send 401 and ask for it
        String authLine = request.getHeader(BASIC_AUTH_CLIENT_REQUEST_HEADER);      
        LOG.debug("authLine = " + authLine);
        if( authLine == null ){
            try{
                response.setHeader(BASIC_AUTH_SERVER_RESPONSE_HEADER, HttpServletRequest.BASIC_AUTH + BASIC_AUTH_REALM);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ERROR - request contains no HTTP Basic Authentication header");
                response.flushBuffer();
            } catch(IOException e ){
                LOG.warn( "Exception sending challenge", e);
            }
            throw new BreakException();
        } else {
            return AUTH_SERVICE.authenticateUser( request );
        }
    }
}
