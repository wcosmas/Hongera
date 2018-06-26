

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;


public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.setMaxInactiveInterval(120);
			
			String lname=request.getParameter("lName");
			String fname=request.getParameter("fName");
			String username=request.getParameter("username");
			String branch=request.getParameter("branch");
			String password=request.getParameter("password");
			String phone=request.getParameter("phone");
			String address=request.getParameter("address");
			String ref=request.getParameter("ref");
			String ref_contact=request.getParameter("ref_contact");
			
			Connection conn;
			String url="jdbc:mysql://localhost:3306/";
			String dbName="Hongera";
			String driver="com.mysql.jdbc.Driver";
			try {
				
				Date date1=new Date();
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Timestamp sq=new Timestamp(date1.getTime());
			
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url+dbName,"root","");
				Statement sts = conn.createStatement();
				ResultSet rest = sts.executeQuery("select username from Sales_Persons, Branch where Branch.branchId=Sales_Persons.branchId and Branch.bName='"+branch+"'");
				
				if(!rest.next()){
					Statement state = conn.createStatement();
					ResultSet rt = state.executeQuery("select Admin.adminId, Branch.branchId from Branch, Admin where Admin.username='"+session.getAttribute("uname")+"' and Branch.bName='"+branch+"'");
					
					int adminId = 0, branchId = 0;
					
					while(rt.next()) {
						adminId = rt.getInt("adminId");
						branchId = rt.getInt("branchId");
					}
					
					Statement state2 = conn.createStatement();
					state2.executeUpdate("insert into Sales_Persons (adminId, branchId, lname,fname,username,password,phone,address,ref,additionDate,refContact) values("+adminId+", "+branchId+", '"+lname+"', '"+fname+"', '"+username+"', '"+password+"', '"+phone+"', '"+address+"', '"+ref+"', '"+sdf.format(sq)+"', '"+ref_contact+"')");
					
					request.getRequestDispatcher("admin.jsp").include(request, response);
					out.println("<h3>Added successfuly</h3>");
				}
				else {
					
					request.getRequestDispatcher("admin.jsp").include(request, response);
					out.println("<h3>The branch is already occupied</h3>");
				}
				
				
				out.close();
			}
			catch(Exception exp) {
				System.out.println(exp);
			}
		}
		
		else {
			
			request.getRequestDispatcher("login.html").include(request, response);
			out.println("<h3>Login first</h3><br>");
		}
		
		
		

	}
	
}

