package com.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletFirst
 */
@WebServlet("/ServletFirst")
public class ServletFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFirst() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
		
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		System.out.println("Hai");
		
		String uname = req.getParameter("name");
		String pass = req.getParameter("password");
		pw.println("User name is : "+uname);
		pw.println("Password is :"+pass);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schema","root","root");
			PreparedStatement pstmt = con.prepareStatement("select username,pwd from students");
			
			 int flag=1;
			if(flag==1){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){				
				if(uname.equals(rs.getString(1)) && pass.equals(rs.getString(2))){
					
					RequestDispatcher rd = req.getRequestDispatcher("QuizMainPage.html");
					rd.forward(req, res);
					break;
					
				}
				else{
					RequestDispatcher rd = req.getRequestDispatcher("error.html");
					rd.include(req, res);
					
				}
				
			}
			}if(flag==2){
		
			
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
