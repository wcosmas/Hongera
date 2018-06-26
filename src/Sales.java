import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sales extends HttpServlet {
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
				Statement st2=con.createStatement();
			    ResultSet result1 = st.executeQuery("select sum(amount) as total, sDate, Sales_Persons.fName, Sales_Persons.lName, Branch.bName from Sales, Sales_Persons, Branch where Sales.personId = Sales_Persons.personId and Sales.branchId = Branch.branchId group by Sales.branchId, Sales.sDate order by sDate desc ");
			    ResultSet result2 = st2.executeQuery("select sum(amount),monthname(sDate),year(sDate) as year, Branch.bName, Sales_Persons.lName, Sales_Persons.fName from Branch, Sales, Sales_Persons where Branch.branchId = Sales.branchId and Sales_Persons.personId=Sales.personId group by month(sDate), Sales.branchId, year(sDate) order by sDate desc");
			    pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<head>\n" + 
						"    <title>Bootstrap Admin Theme v3</title>\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
						"    <!-- Bootstrap -->\n" + 
						"    <link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n" + 
						"    <!-- styles -->\n" + 
						"    <link href=\"css/styles.css\" rel=\"stylesheet\">\n"
						+ "<link href=\"css/stats.css\" rel=\"stylesheet\">" + 
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
						"	                          <li><a href=\"profile.html\">Profile</a></li>\n" + 
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
						"		  <div class=\"col-md-9\" >");
		
			    pw.println("<h3>Total Sales Per day</h3>");
			    pw.println("<table class=\"table table-striped\" style=\"margin-top:2%; margin-bottom: 10%;\" border=\"1\">");
				pw.println("<tr>"
						+ "<th>Person Name</th>"
						+ "<th>Branch</th>"
						+ "<th>Total Amount</th>"
						+ "<th>Date</th>"
						+ "</tr>");
			    
			    while(result1.next()) {
			    	pw.println("<tr>"
							+ "<td>"+result1.getString("lName")+" "+result1.getString("fName")+"</td>"
									+ "<td>"+result1.getString("bName")+"</td>"
									+ "<td>"+result1.getDouble("total")+"</td>"
									+ "<td>"+result1.getString("sDate")+"</td>"
									+ "</tr>");
			    }
			    pw.println("</table>");
			    
			    pw.println("<h3>Monthly Sales</h3>");
			    pw.println("<table class=\"table table-striped\" style=\"margin-top:2%; margin-bottom: 10%;\" border=\"1\" id=\"monthly\">");
				pw.println("<tr>"
						+ "<th>Person Name</th>"
						+ "<th>Branch</th>"
						+ "<th>Total Amount</th>"
						+ "<th>Date</th>"
						+ "</tr>");
			    
			    while(result2.next()) {
			    	pw.println("<tr>"
							+ "<td>"+result2.getString("lName")+" "+result2.getString("fName")+"</td>"
									+ "<td>"+result2.getString("bName")+"</td>"
									+ "<td>"+result2.getDouble("sum(amount)")+"</td>"
									+ "<td>"+result2.getString("monthname(sDate)")+", "+result2.getString("year")+"</td>"
									+ "</tr>");
			    }
			    pw.println("</table><a href=\"#home\"> Back</a>");
			    
			    pw.println(" 	</div>\n" + 
						"	</div>\n" + 
						"</div>\n" + 
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
						"    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n" + 
						"    <script src=\"https://code.jquery.com/jquery.js\"></script>\n" + 
						"    <!-- jQuery UI -->\n" + 
						"    <script src=\"https://code.jquery.com/ui/1.10.3/jquery-ui.js\"></script>\n" + 
						"    <!-- Include all compiled plugins (below), or include individual files as needed -->\n" + 
						"    <script src=\"bootstrap/js/bootstrap.min.js\"></script>\n" + 
						"\n" + 
						 
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
