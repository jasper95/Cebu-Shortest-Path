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
            html, body, #map{
          height: 100%;
          margin: 0px;
          padding: 0px
        }
        #panel {
          position: absolute;
          top: 5px;
          left: 50%;
          margin-left: -180px;
          z-index: 5;
          background-color: #fff;
          padding: 5px;
          border: 1px solid #999;
        }
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
        <div id="panel">
            <b>Start: </b>
            <select id="start">
                <option value="-1">-</option>
            </select>
            <b>End: </b>
            <select id="end">
                <option value="-1">-</option>
            </select>
        </div>
        <div id='map'></div>
          &nbsp;
        <div id="warnings_panel" style="width:100%;height:10%;text-align:center"></div>
        <script src="${js}app/test.js"></script>
        <script> </script>
    </body>
</html>