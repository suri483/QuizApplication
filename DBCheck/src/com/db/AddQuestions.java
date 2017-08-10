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
 * Servlet implementation class AddQuestions
 */
@WebServlet("/AddQuestions")
public class AddQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestions() {
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
		
		String question = req.getParameter("question");
		String answer1 = req.getParameter("ans1");
		String answer2 = req.getParameter("ans2");
		String sname = req.getParameter("s1");
	
		int rn = (int )(Math.random() * 50 + 1);
		pw.println("Question is :  "+question);
		pw.println("Answer1 is  :  "+answer1);
		pw.println("Answer2 is  :  "+answer2);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schema","root","root");
			PreparedStatement pstmt1 = con.prepareStatement("insert into question1 values(?,?,?)");
			pstmt1.setInt(1,rn);
			pstmt1.setString(2,question);
			pstmt1.setString(3,sname);
			int c=pstmt1.executeUpdate();
			if(c>0)
			{
				System.out.print("QUESTION IS ADDED");
						
			PreparedStatement pstmt2 = con.prepareStatement("insert into answer1 values(?,?,?)");
			pstmt2.setInt(1,1);
			pstmt2.setString(2,answer1);
			pstmt2.setInt(3,rn);
			int k=pstmt2.executeUpdate();
			PreparedStatement pstmt3 = con.prepareStatement("insert into answer1 values(?,?,?)");
			pstmt3.setInt(1,2);
			pstmt3.setString(2,answer2);
			pstmt3.setInt(3,rn);
			int l=pstmt3.executeUpdate();
			if(k>0&&l>0)
			{
				System.out.print("ANSWER IS ADDED");
			}
			}		
			} 
			catch (ClassNotFoundException e) {
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
