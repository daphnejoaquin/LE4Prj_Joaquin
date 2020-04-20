<%-- 
    Document   : error
    Created on : 04 21, 20, 3:50:00 AM
    Author     : Daphne Joaquin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <center>
            <h1><%out.println(getServletContext().getInitParameter("Header"));%> </h1>
        </center>    
        <style>
            body {
                padding-top: 150px;
                text-align: center;
                color: red;
            }
            
        </style>
    </head>
    <body>
        <h1><%=request.getAttribute("error")%></h1>
    </body>
    <footer>  
        <center><br/>
            <p> Copyright: <%out.println(getServletContext().getInitParameter("Footer"));%> | Time Accessed : <%= new Date() %> </p>
        </center>
    </footer>   
</html>
