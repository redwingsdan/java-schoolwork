<%-- 
    Document   : newjsp
    Created on : Mar 29, 2017, 5:49:06 PM
    Author     : djp217
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% session.setAttribute("r", request); %>
        <p>
            $(r.method)
            <!-- or $(pageContext.request.method)-->
        </p>
    </body>
</html>
