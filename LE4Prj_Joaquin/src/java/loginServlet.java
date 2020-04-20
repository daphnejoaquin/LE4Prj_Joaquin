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
import java.sql.Statement;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author justi
 */
public class loginServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        init();
        String displayName = "";
        String role = ""; 
        String error = "";
        try {	

                if (conn != null) {
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM UserDB");
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        System.out.println(username);
                        System.out.println(password);
                        while(rs.next()){
                            String username_real = rs.getString("username");
                            String password_real = decrypt(rs.getString("password"));
                            
                            if(username_real.equals(username) && password_real.equals(password)){
                                //success
                                request.setAttribute("username",username_real);
                                request.setAttribute("role",rs.getString("roles"));
                                RequestDispatcher view = request.getRequestDispatcher("success.jsp");
                                view.forward(request, response);
                                
                            }
                            else if(username_real.equals(username) && !password_real.equals(password)){
                                //incorrect password!
                                if(password.equals("")){
                                    error = "Password is Blank!";
                                }
                                else{
                                   error = "Incorrect Password!";
                                }
                                break;
                            }
                        }
                        if(error.equals("")){
                            if(username.equals("") && password.equals("")){
                                //username and password is blank
                                error = "Username and Password is blank!";
                            }
                            else if(username.equals("")){
                                //username is blank
                                error = "Username is blank!";
                            }
                            else if(password.equals("")){
                                error = "Password is blank!";
                            }
                            else{
                                error = "Incorrect Credentials!";   
                            }
                        
                        
                        }
                        else{
                            //do nothing
                        }
                        System.out.println(error);
                        request.setAttribute("error",error);
                        RequestDispatcher view = request.getRequestDispatcher("error.jsp");
                        view.include(request,response);
                        

                } else {
                        System.out.println("Error");
                }
        } catch (SQLException sqle){
           System.out.println("SQL Exeception");    
        } 
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
        processRequest(request, response);
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
        processRequest(request, response);
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
