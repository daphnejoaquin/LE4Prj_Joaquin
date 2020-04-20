<%-- 
    Document   : index
    Created on : 04 21, 20, 2:50:26 AM
    Author     : Daphne Joaquin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
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
        <form method="POST" action="registerServlet"
            <p>Create Username:</p>
            <input type="text" name="username">
            
            <br>
            <br>
            <br>

            <label for="role">Choose a role:</label>
            <select id="role" name="role">
                <option value="Admin">Admin</option>
                <option value="Guest">Guest</option>
            </select>
            
            <br>
            <br>
         
            
            <p>Enter Password:</p>
            <input type="password" name="password">
            <p>Confirm Password:</p>
            <input type="password" name="confirm_password">

            <br>
            <br>

            <div class="center">
                <input type="submit" name="button" value="Sign Up">
            </div>

        </form>
    </body>
    <footer>  
        <center><br/>
            <p> Copyright: <%out.println(getServletContext().getInitParameter("Footer"));%> | Time Accessed : <%= new Date()%> </p>
        </center>
    </footer>   
</html>
