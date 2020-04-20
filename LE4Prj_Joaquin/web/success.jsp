<%-- 
    Document   : success
    Created on : 04 21, 20, 1:54:40 AM
    Author     : Daphne Joaquin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <center>
            <h1><%out.println(getServletContext().getInitParameter("Header"));%> </h1>
        </center>    
        <style>

            body {
                margin: 150px;
                display: inline-block;
            }
            
             form {
                padding-top: 150px;
                display: inline-block;
            }
        </style>
    </head>
    <body>
        <h1>Welcome:<%=request.getAttribute("username") %> </h1>
        <h1>Role:<%=request.getAttribute("role") %> </h1>
        <form action="login.jsp">
         <button type="submit">Back</button>
      </form>
    </body>
     <footer>  
        <center><br/>
            <p> Copyright: <%out.println(getServletContext().getInitParameter("Footer"));%> | Time Accessed : <%= new Date() %> </p>
        </center>
    </footer>   
</html>
