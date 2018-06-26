import java.io.*;import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set content type
				response.setContentType("text/html");
				//get printwriter
				PrintWriter out = response.getWriter();
				
				String password = request.getParameter("password");
				HttpSession session = request.getSession(false);
				if(session!=null) {
					try {
						
						session.setMaxInactiveInterval(120);
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hongera","root","");
				    	Statement state = con.createStatement();
				    	
							String sql = "update Sales_Persons set password='"+password+"' where username='"+(String)session.getAttribute("uname")+"'";
							state.executeUpdate(sql);
							request.getRequestDispatcher("sales_person.jsp").include(request, response);
							
					}catch(Exception e) {
						e.printStackTrace();
					}
				}	
				else {
					out.println("<p>Login first<p>");
					request.getRequestDispatcher("login.html").include(request, response);
				}
				
				
	}

}
