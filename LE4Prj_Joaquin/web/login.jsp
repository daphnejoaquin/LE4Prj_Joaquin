<%-- 
    Document   : login
    Created on : 04 21, 20, 3:11:45 AM
    Author     : Daphne Joaquin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div>
            </body>
            <footer>  
                <center><br/>
                    <p> Copyright: <%out.println(getServletContext().getInitParameter("Footer"));%> | Time Accessed : <%= new Date()%> </p>
                </center>
            </footer>   
            </html>


