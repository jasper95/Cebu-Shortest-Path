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
        <spring:url value="/resources/css/" var="css"/>
        <spring:url value="/resources/images/" var="images"/>
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
        <link href="${css}index.css" rel="stylesheet">
        <link href="${css}bootstrap.min.css" rel="stylesheet">
        <script src="${js}app.js"></script>
        <script src="${js}require.js"></script>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INDEX</title>
        <style>
            body { margin:0; padding:0; }
            #map { position:absolute; top:0; bottom:0; width:100%; }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
            <div class="container topnav">
                
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand topnav logo" href="#"><img src="${images}logo.png" class="logo-up"></a>
                </div>
                
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>  
        <div id="panel">
            <b>Start: </b>
            <select id="start">
                <option value="-">-</option>
                <option value="UP Cebu, Gorordo Avenue, Cebu City, Central Visayas">UP Cebu</option>
            </select>
            <b>End: </b>
            <select id="end" onchange="calcRoute();">
                <option value="-">-</option>
                <option value="10.3224, 123.9003">Node 0</option>
                <option value="Marc and Mattheau's Bakeshop, Cor. Molave St., 459 Gorordo Ave, Dakbayan sa Sugbu 6000">Node 1</option>
                <option value="PAG-IBIG Fund Cebu Office, Cebu City">Pag-IBIG</option>
            </select>
        </div>
        <div id='map'></div>
          &nbsp;
        <div id="warnings_panel" style="width:100%;height:10%;text-align:center"></div>
        <script src="${js}app/test.js"></script>
    </body>
</html>