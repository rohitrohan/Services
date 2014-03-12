package com.manheim.ws.blobmanager.authentication;

import com.manheim.authentication.Authentication;
import com.manheim.authentication.AuthenticationConstants;
import com.manheim.authentication.AuthenticationServiceException;
import com.manheim.authentication.GrantedAuthority;
import com.manheim.authentication.userdetails.User;

import com.manheim.ws.blobmanager.AuthenticationService;
import com.manheim.ws.blobmanager.IUser;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


/**
 *
 * @author rcooper
 */
public class BMAuthenticationService implements AuthenticationService {
    private static final Logger LOG = Logger.getLogger(BMAuthenticationService.class);

    /*static{
        initContext();
    }*/

    /** Creates a new instance of USMAuthenticationService */
    public BMAuthenticationService() {
        super();
    }

    public IUser authenticateUser(HttpServletRequest request) {
        Authentication existingAuth = (Authentication) (request.getSession()
                                                               .getAttribute(
                AuthenticationConstants.AUTH_SESSION_TOKEN_KEY));

        AuthUser value = null;
        String name = null;
        ArrayList groups = null;

        if((existingAuth != null) && existingAuth.isAuthenticated()) {
            try {
                User user = (User) existingAuth.getPrincipal();
                name = user.getName();

                String password = (String) existingAuth.getCredentials();
                String dealerId = user.getUniversalNumber();
                BMAuthenticationService.LOG.debug("established dealerId - " + dealerId);

                // get user permissions
                GrantedAuthority[] auths = user.getAuthorities();
                groups = new ArrayList();

                BMAuthenticationService.LOG.debug("auth check");

                for(int i = 0; i < auths.length; i++) {
                    groups.add(auths[i].getAuthority());
                    LOG.debug(" group roles & perms: " + auths[i].getAuthority());
                }

                BMAuthenticationService.LOG.debug("permissions check");

                value = new AuthUser();
                value.setName(name);
                value.setPassword(password);
                value.setRoles((String[]) groups.toArray(new String[groups.size()]));
            } catch(AuthenticationServiceException e) {
                BMAuthenticationService.LOG.debug("authentication failed: " + name, e);
            }
        }

        return value;
    }
}
