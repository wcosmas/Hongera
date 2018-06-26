import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	
		PrintWriter out = response.getWriter();
			
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
			
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hongera","root","");
	    	
	    	Statement state2;
	    	//for the attendant
	    	String sql = "SELECT username, password FROM Sales_Persons WHERE username='"+request.getParameter("username")+"'&&password='"+request.getParameter("password")+"'";
	    	
	    	state2 = con.createStatement();
	    	ResultSet rp = state2.executeQuery(sql);
	  		
	    	HttpSession session;
	    	if(request.getParameter("username").equals(getServletConfig().getInitParameter("username"))&&request.getParameter("password").equals(getServletConfig().getInitParameter("password"))) {
	    		session = request.getSession();
	    		session.setAttribute("uname", request.getParameter("username"));
	    		request.getRequestDispatcher("admin.jsp").forward(request, response);
	    		
	    	}
	    	
	    	else if(rp.next()){
	    		session = request.getSession();
	    		session.setAttribute("uname", request.getParameter("username"));
		    	request.getRequestDispatcher("sales_person.jsp").forward(request, response);
		    	
		    }
	    	
	    	else {
	    		request.getRequestDispatcher("login.html").include(request, response);
	    		out.println("<h3>Wrong Username or Password</h3>");
	    		
	    	}
	    	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}