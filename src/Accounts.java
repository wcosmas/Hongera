import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Accounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		

  		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  		response.setHeader("Pragma", "no-cache");//HTTP 1.0
  		response.setHeader("Expires", "0");//Proxies
  		
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			
			try {
				
				session.setMaxInactiveInterval(120);
				
				Class.forName("com.mysql.jdbc.Driver");				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hongera","root","");
				Statement st=con.createStatement();
			    ResultSet result = st.executeQuery("select * from Sales_Persons");
			    
			    pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<head>\n" + 
						"    <title>Bootstrap Admin Theme v3</title>\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
						"    <!-- Bootstrap -->\n" + 
						"    <link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n" + 
						"    <!-- styles -->\n" + 
						"    <link href=\"css/styles.css\" rel=\"stylesheet\">\n" + 
						"\n" + 
						"   \n" + 
						"  </head>");
				pw.println("<body>"
						+ "<div class=\"header\" >\n" + 
						"	     <div class=\"container\">\n" + 
						"	     	<header class=\"header dark-bg\">\n" + 
						"		      <div class=\"toggle-nav\">\n" + 
						"		        <div class=\"icon-reorder tooltips\" data-original-title=\"Toggle Navigation\" data-placement=\"bottom\"><i class=\"icon_menu\"></i></div>\n" + 
						"		      </div>\n" + 
						"		\n" + 
						"		      <div class=\"top-nav notification-row\">\n" + 
						"		\n" + 
						"		        <ul class=\"nav pull-right top-menu\">\n" + 
						"		          <!-- user login dropdown start-->\n" + 
						"		          <li class=\"dropdown\">\n" + 
						"		            <ul class=\"dropdown-menu extended logout\">\n" + 
						"		              <div class=\"log-arrow-up\"></div>\n" + 
						"		              <li class=\"eborder-top\">\n" + 
						"		                <a href=\"#\"><i class=\"icon_profile\"></i> My Profile</a>\n" + 
						"		              </li>\n" + 
						"		              <li>\n" + 
						"		                <a href=\"login.html\"><i class=\"icon_key_alt\"></i> Log Out</a>\n" + 
						"		              </li>\n" + 
						"		            </ul>\n" + 
						"		          </li>\n" + 
						"		          <!-- user login dropdown end -->\n" + 
						"		        </ul>\n" + 
						"		        <!-- notificatoin dropdown end-->\n" + 
						"		      </div>\n" + 
						"		      <div class=\"row\" >\n" + 
						"	           <div class=\"col-md-5\">\n" + 
						"	              \n" + 
						"	              <div class=\"logo\">\n" + 
						"	                 <h1><a href=\"index.html\">Hongera Kids Clothing</a></h1>\n" + 
						"	              </div>\n" + 
						"	           </div>\n" + 
						"	          \n" + 
						"	           <div class=\"col-md-7\">\n" + 
						"	              <div class=\"navbar navbar-inverse\" role=\"banner\">\n" + 
						"	                  <nav class=\"collapse navbar-collapse bs-navbar-collapse navbar-right\" role=\"navigation\">\n" + 
						"	                    <ul class=\"nav navbar-nav\">\n" + 
						"	                      <li class=\"dropdown\">\n" + 
						"	                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"glyphicon glyphicon-user\"></i>"+session.getAttribute("uname")+"<b class=\"caret\"></b></a>\n" + 
						"	                        <ul class=\"dropdown-menu animated fadeInUp\">\n" + 
						"	                          <li><a href=\"Logout\">Logout</a></li>\n" + 
						"	                        </ul>\n" + 
						"	                      </li>\n" + 
						"	                    </ul>\n" + 
						"	                  </nav>\n" + 
						"	              </div>\n" + 
						"	           </div>\n" + 
						"	        </div>\n" + 
						"		    </header>\n" + 
						"	        \n" + 
						"	</div>\n" + 
						"\n" + 
						"    <div class=\"page-content\">\n" + 
						"    	<div class=\"row\">\n" + 
						"		  <div class=\"col-md-3\">\n" + 
						"		  	<div class=\"sidebar content-box\" style=\"display: block;width:;\">\n" + 
						"                <ul class=\"nav\">\n" + 
						"                    <!-- Main menu -->\n" + 
						"                    <li class=\"current\"><a href=\"admin.jsp\"><i class=\"glyphicon glyphicon-home\"></i>Home</a></li>\n" + 
						"                    <li><a href=\"Commission\">Generate Commission</a></li>\n" + 
						"                    <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#myModal\">Add Sales Person</a></li>\n" + 
						"                    <li><a href=\"Sales\">View Sales</a></li>\n" + 
						"                    <li><a href=\"Accounts\">Manage accounts</a></li>\n" + 
						"                    \n" + 
						"                </ul>\n" + 
						"             </div>\n" + 
						"		  </div>\n" + 
						"		  <div class=\"col-md-9\"> <h3>Accounts</h3>");
				
			    pw.println("<table class=\"table table-striped\" style=\"margin-top:2%;\" border=\"1\">");
				pw.println("<tr>"
						+ "<th>First Name</th>"
						+ "<th>Last Name</th>"
						+ "<th>Username</th>"
						+ "<th>Contact</th>"
						+ "<th>Reference Person</th>"
						+ "<th>Physical Address</th>"
						+ "<th>Delete Account</th>"
						+ "</tr>");
			    
			    while(result.next()) {
			    	pw.println("<form method='get' action='DeleteAccount'>");
			    	pw.println("<tr>"
							+ "<td>"+result.getString("fName")+"</td>"
									+ "<td>"+result.getString("lName")+"</td>"
									+ "<td>"+result.getString("username")+"</td>"
									+ "<td>"+result.getString("phone")+"</td>"
									+ "<td>"+result.getString("ref")+"</td>"
									+ "<td>"+result.getString("address")+"</td>"
									+"<input type='hidden' name='firstName' value='"+result.getString("fName")+"'>"
									+"<input type='hidden' name='lastName' value='"+result.getString("lName")+"'>"
									+"<input type='hidden' name='username1' value='"+result.getString("username")+"'>"
									+ "<td>"+"<input type=\"submit\" value=\"Delete\" name=\"delete\" class=\"btn btn-primary signup\" style=\"color:red; margin-left:10px;\">"+"</td>"
									+ "</tr>");
			    	pw.println("</form>");
			    }
			    
			    /*while(result.next()) {
					pw.println("<form method='get' action='DeleteAccount'>");
					pw.println("<tr><td>");
					pw.println(result.getString("fName")+"</td>");
					pw.println("<td>"+result.getString("lName")+"</td>");
					pw.println("<td>"+result.getString("address")+"</td>");
					pw.println("<td>"+result.getString("ref")+"</td>");
					pw.println("<td>"+result.getString("phone")+"</td>");
					pw.println("<input type='hidden' name='firstName' value='"+result.getString("fName")+"'>");
					pw.println("<input type='hidden' name='lastName' value='"+result.getString("lName")+"'>");
					pw.println("<input type='hidden' name='username1' value='"+result.getString("username")+"'>");
					pw.println("<td>"+result.getString("branch.bName")+"</td>"+"<td><input type='button' name='button' value='Edit'>"+"<input type='submit' name='button' value='Delete' ></td>");
					pw.println("</tr>");
					pw.println("</form>");
				}*/
			    pw.println("</table>");
			    pw.println(" </div>\n" + 
						"		</div>\n" + 
						"    </div>\n" + 
						"\n" + 
						"    <footer style=\"position: fixed;bottom: 0;right: 0;left: 0;\">\n" + 
						"         <div class=\"container\">\n" + 
						"         \n" + 
						"            <div class=\"copy text-center\">\n" + 
						"               Incubee 2018 <a href='#'>Hongera Kids Clothing</a>\n" + 
						"            </div>\n" + 
						"            \n" + 
						"         </div>\n" + 
						"    </footer>\n" + 
						"\n" + 
						"\n" + 
						"    <script src=\"https://code.jquery.com/jquery.js\"></script>\n" + 
						"    <!-- Include all compiled plugins (below), or include individual files as needed -->\n" + 
						"    <script src=\"bootstrap/js/bootstrap.min.js\"></script>\n" + 
						"    <script src=\"js/custom.js\"></script>\n" + 
						"  </body>\n" + 
						"</html>"); 
			    
			   
			    
			}catch(Exception e) {
				e.printStackTrace();
				
				
			}
		}
		else {
			
			request.getRequestDispatcher("login.html").include(request, response);
			pw.println("<h4>Login first</h4><br>");
		}
	}

}
