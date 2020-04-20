/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import org.apache.commons.codec.binary.*;
/**
 *
 * @author justi
 */
public class registerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
	 private static byte[] key = {
	 	0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 
                0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79
                 // thisisasecretkey
	 };
    
    
    public static String encrypt(String strToEncrypt) {
	String encryptedString = null;
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return encryptedString;
	}

    public static String decrypt(String codeDecrypt){
	String decryptedString = null;
            try{
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                decryptedString = new String(cipher.doFinal(Base64.decodeBase64(codeDecrypt)));
            } catch (Exception e) {
		System.err.println(e.getMessage());
            }
	return decryptedString;
    }	
    
    Connection conn;
    public void init() throws ServletException
    {
    	try {	
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			String username = "app";
			String password = "app";
			String url = 
			  "jdbc:derby://localhost:1527/JAVA DB"; 
			conn = 
			  DriverManager.getConnection(url,username,password);
		} catch (SQLException sqle){
			System.out.println("SQLException error occured - " 
				+ sqle.getMessage());
		} catch (ClassNotFoundException nfe){
			System.out.println("ClassNotFoundException error occured - " 
		        + nfe.getMessage());
		}

    }
    
    public boolean checkUsername(String username) throws SQLException{
        String queryStr = "SELECT * FROM UserDB WHERE username = ? ";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        boolean result = false;
        while(rs.next()){
            if(rs.getString("username").equals(username)){
                result = true;
                break;
            }
            else{
                result = false;
            }
        }
        return result;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        init();
            /* TODO output your page here. You may use following sample code. */
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String password_encrypted = encrypt(password);
        String error = "";
        boolean usernameExists = checkUsername(username);
        
        if(usernameExists){
            error = "Username exists! Please choose another username";
        }
        if(!password.equals(confirm_password)){
            error = "Password and Confirm Password are not the same!";
        }
        if (conn != null && error.equals("")) {
            String queryStr = "INSERT INTO UserDB (username,password,roles) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryStr);
            ps.setString(1, username);
            ps.setString(2, password_encrypted);
            ps.setString(3, role);
            ps.executeUpdate();	
            request.setAttribute("username",username);
            request.setAttribute("role",role);
            System.out.println(password_encrypted);
            System.out.println(decrypt(password_encrypted));
            RequestDispatcher view = request.getRequestDispatcher("success.jsp");
            view.forward(request, response);
        }
        else{
            if (error.equals("")){
                System.out.println("No DB Connection");
            }
            else{
                System.out.println(error);
            }
        }
        
        request.setAttribute("error",error);
        RequestDispatcher view = request.getRequestDispatcher("error.jsp");
        view.include(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             try {
                 processRequest(request, response);
             } catch (SQLException ex) {
                 Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             try {
                 processRequest(request, response);
             } catch (SQLException ex) {
                 Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
