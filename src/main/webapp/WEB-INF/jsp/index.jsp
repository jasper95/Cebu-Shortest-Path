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
      
        <link href="${css}index.css" rel="stylesheet">
        <link href="${css}bootstrap.min.css" rel="stylesheet">
        <link href="${css}bootstrap-select.css" rel="stylesheet">
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

        <div id='map'></div>&nbsp;
        
        <div class="row" id="def-margin">
            <div class="col-sm-2">
                <select id="start" class="selectpicker show-tick form-control">
                  <option value="-1">To</option>
                </select>
            </div>
            <div class="col-sm-2">
                <select id="end" class="selectpicker show-tick form-control">
                  <option value="-1">From</option>
                </select>
            </div>
        </div>
        <div id="warnings_panel" style="width:100%;height:10%;text-align:center"></div>
        <script src="${js}app/test.js"></script>
        <script> </script>
    </body>
</html>
