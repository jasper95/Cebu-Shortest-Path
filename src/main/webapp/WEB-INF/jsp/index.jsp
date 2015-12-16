<%-- 
    Document   : index
    Created on : Dec 15, 2015, 7:57:10 AM
    Author     : Bert
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/resources/js/" var="js"/>
        <style>
            html, body { height: 100%; margin: 0; padding: 0; }
            #map { height: 100%; }
        </style>
        <script src="${js}app.js"></script>
        <script src="${js}require.js"></script>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body { margin:0; padding:0; }
            #map { position:absolute; top:0; bottom:0; width:100%; }
        </style>
    </head>
    <body>
        <div id='map'></div>
        <script src="${js}app/test.js"></script>
    </body>
</html>