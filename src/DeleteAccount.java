import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="jdbc:mysql://localhost:3306/";
		String dbName="Hongera";
		String driver="com.mysql.jdbc.Driver";
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession(false);
		if(session!=null) {
		try {
		
			Class.forName(driver).newInstance();
			
			Connection conn = (Connection) DriverManager.getConnection(url+dbName,"root","");
			
			
			Statement state = (Statement) conn.createStatement();
			
			state.executeUpdate("DELETE FROM Sales_Persons WHERE Sales_Persons.username = '"+request.getParameter("username1")+"'");
			request.getRequestDispatcher("/Accounts").include(request, response);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		else {
			out.println("<h4>Login first</h4><br>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	}
}
