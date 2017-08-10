package com.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddSubject
 */
@WebServlet("/AddSubject")
public class AddSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		PrintWriter pw = response.getWriter();	
		String subjectname = request.getParameter("subname");
		String subjectDescription = request.getParameter("subdes");
		pw.println("Subject name is   "+subjectname);
		pw.println("Subject Description is    "+subjectDescription);	
		
		
		
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schema","root","root");
			PreparedStatement pstmt = con.prepareStatement("insert into subject1 values(?,?)");			
			
			pstmt.setString(1,subjectname);		
			pstmt.setString(2,subjectDescription);		
			int c = pstmt.executeUpdate();	
			
			if(c>0){
				
				System.out.println("Record Inserted");
				
				RequestDispatcher rd = request.getRequestDispatcher("addquestion.html");				
				rd.forward(request, response);				
			}
			else{
				pw.println("Record not Inserted");
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
