<%-- 
    Document   : register
    Created on : Apr 4, 2017, 5:50:18 PM
    Author     : clotifoth
--%>

<%@page import="java.lang.reflect.Method"%>
<%@page import="java.lang.reflect.Field"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!-- Set up the bean defs to store state serverside -->
<jsp:useBean id="rb" class="app.RegisterBean" scope="application" />
<jsp:setProperty name="rb" property="*"/>
<!-- JSTL import -->
<%@ taglib prefix="t" uri= "/WEB-INF/tlds/customtag_library" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
﻿<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href="./developerWorks_registration_files/debug.css" rel="stylesheet" title="www" type="text/css" />
        <title>Debug</title>
    </head>
    <body id="ibm-com">
        <h2 id="headers">HTTP headers</h2>
        <table>
            <jstl:forEach var="a" items="${headerValues}">
                <tr>
                    <td class="key">${a.key}</td>
                    
                    <jstl:forEach items="${a.value}" var="headerValue">
                        <td class="value">${headerValue}</td>
                    </jstl:forEach>
                </tr>
            </jstl:forEach>
        </table>
        <h2 id="session">sessionScope data</h2>
        <table>
            <jstl:forEach var="a" items="${sessionScope}">
                <tr>
                    <td class="key">${a.key}</td>
                    <td class="value">${a.value}</td>
                </tr>
            </jstl:forEach>
        </table>
        <h2 id="servletContext">servletContext data</h2>
        <table>
            <jstl:forEach var="a" items="${servletContext}">
                <tr>
                    <td class="key">${a.key}</td>
                    <td class="value">${a.value}</td>
                </tr>
            </jstl:forEach>
        </table>
        <h2 id="pageScope">pageScope data</h2>
        <table>
            <jstl:forEach var="a" items="${pageScope}">
                <tr>
                    <td class="key">${a.key}</td>
                    <td class="value">${a.value}</td>
                </tr>
            </jstl:forEach>
        </table>
        
        <h2 id="requestScope">requestScope data</h2>
        <table>
            <jstl:forEach var="a" items="${requestScope}">
                <tr>
                    <td class="key">${a.key}</p>
                    <td class="value">${a.value}</p>
                </tr>
            </jstl:forEach>
        </table>
        
        <h2 id="bean">Bean data</h2>
        <table>
            <t:hello bean="${rb}" />
        </table>
    </body>
</html>
