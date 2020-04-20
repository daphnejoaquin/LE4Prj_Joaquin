<%-- 
    Document   : index
    Created on : 04 21, 20, 2:50:26 AM
    Author     : Daphne Joaquin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
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
        <form method="POST" action="Signup.do"
            <p>Create Username:</p>
            <input type="text" name="uname">
            
            <br>
            <br>
            <br>

            <label for="role">Choose a role:</label>
            <select id="role" name="roles">
                <option value="admin">Admin</option>
                <option value="guest">Guest</option>
            </select>
            
            <br>
            <br>
         
            
            <p>Enter Password:</p>
            <input type="password" name="pass">
            <p>Confirm Password:</p>
            <input type="password" name="conpass">

            <br>
            <br>

            <div class="center">
                <input type="submit" name="button" value="Sign Up">
            </div>

        </form>
    </body>
</html>
