<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.Properties,java.util.ArrayList,java.util.StringTokenizer" %>
<%!
    static class UserData extends Object {
        String name;
        String password;
        String fullyQualifiedName;
        String[] groups;
        String credentials;
    }
%>
<%
    if( !System.getProperty("system.environment", "DEV").equalsIgnoreCase("DEV") ){
        throw new ServletException("This page cannot be accessed outside of a 'DEV' system.environment!");
    }
    
    ArrayList users = (ArrayList) application.getAttribute( "com.manheim.ws.core.security.testusers");
    if( users == null ){
        users = new ArrayList();
    
        Properties usersInfo = new Properties();
        usersInfo.load( this.getClass().getResourceAsStream("/ws-core-testusers.properties") );

        
        for( int counter = 0; usersInfo.getProperty( "testuser."+counter+".name" ) != null ; counter++  ){
            UserData data = new UserData();
            data.name = usersInfo.getProperty( "testuser."+counter+".name" );
            data.password = usersInfo.getProperty( "testuser."+counter+".password" );
            data.fullyQualifiedName = usersInfo.getProperty( "testuser."+counter+".fullyQualifiedName" );
            data.credentials = usersInfo.getProperty( "testuser."+counter+".credentials" );
            String groupsLine = usersInfo.getProperty( "testuser."+counter+".groups" );
            if( groupsLine != null ){
                StringTokenizer tok = new StringTokenizer( groupsLine, ",");
                String[] groups = new String[tok.countTokens()];
                for( int i=0; tok.hasMoreTokens(); i++ ){
                    groups[i] = tok.nextToken().trim();
                }
                data.groups = groups;
            }
            users.add( data );
        }
    }
    
    if( request.getParameter("testUserId") != null ){
        UserData user = (UserData) users.get( Integer.parseInt( request.getParameter("testUserId")));
        application.setAttribute("com.manheim.ws.core.security.testuser.name", user.name );
        application.setAttribute("com.manheim.ws.core.security.testuser.password", user.password);
        application.setAttribute("com.manheim.ws.core.security.testuser.fullyQualifiedName", user.fullyQualifiedName);
        application.setAttribute("com.manheim.ws.core.security.testuser.groups", user.groups );
        application.setAttribute("com.manheim.ws.core.security.testuser.credentials", user.credentials );
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test User</title>
    </head>
    <body>

    <h1>Select Test User</h1>
    <p> This form will let you select a default set of user credentials that requests
    coming into the application will use if there is no WebSEAL user information passed 
    in with the request headers. The list of users are configured in the <code>ws-core-testusers.properties</code>
    file in the <code>src/conf</code> directory of the project template.</p>
    <form action="testUser.jsp" method="post">
    <select name="testUserId">
<%  for( int i=0; i < users.size(); i++ ){ 
        UserData user = (UserData) users.get(i); %>
        <option value="<%=i%>" 
        <% if( user.name.equals( application.getAttribute("com.manheim.ws.core.security.testuser.name" ) ) ) out.print(" selected=\"true\" "); %>
        ><%=user.name%></option>
<%  }%>
    </select>
    <input type="submit" name="submit" value="Change Default User" />
    </form>
    </body>
</html>
