<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Dump</title>
    </head>
    <body>

    <h1>Header Dump</h1>
    
    <% 
    Enumeration enu = request.getHeaderNames();
    while( enu.hasMoreElements() ){ 
        String headerName = enu.nextElement().toString();
    %>
    <dl>
    <dt><%=headerName%></dt>
    <%
        Enumeration headerValues = request.getHeaders( headerName );
        while( headerValues.hasMoreElements() ) { 
    %>
    <dd><%=headerValues.nextElement()%></dd>
    <%
        }
    %>
    </dl>
    <%
    }
    %>    
    </body>
</html>
