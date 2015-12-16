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
        <script src="${js}app.js"></script>
        <script src="${js}require.js"></script>
        <script src="${js}app/test.js"></script>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>