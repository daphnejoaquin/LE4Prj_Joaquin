<%-- 
    Document   : index
    Created on : 04 21, 20, 1:10:08 AM
    Author     : justi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <head class="container-fluid">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
         <center>
            <h1><%out.println(getServletContext().getInitParameter("Header"));%> </h1>
        </center>    
        <style>
            
            body {
                text-align: center; 
                
            }
            
            form {
                padding-top: 150px;
                display: inline-block;
            }
            
        </style>
    </head>
    <body>
        <div class="content">
            <form method="POST" action="loginServlet"
                  
                <p>Username:</p>
                <input type="text" name="username">
                <p>Password:</p>
                <input type="password" name="password">

                <br>
                <br>
                <div class="center">
                    <input type="submit" name="button" value="Login">
                </div>

            </form>
            <form action ="signup.jsp">
                    <input type="submit" name="button" value="Sign Up">
            </form>
            <div>
    </body>
    <footer>  
        <center><br/>
            <p> Copyright: <%out.println(getServletContext().getInitParameter("Footer"));%> | Time Accessed : <%= new Date() %> </p>
        </center>
    </footer>   
</html>
