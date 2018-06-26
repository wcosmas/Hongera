import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyCommission extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			session.setMaxInactiveInterval(120);
			
			String username = (String)session.getAttribute("uname");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			
			try {
				Date date1=new Date();
				SimpleDateFormat sdk=new SimpleDateFormat("HH:mm:ss");
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Timestamp sq=new Timestamp(date1.getTime());
				Date parsed = sdk.parse("18:00:00");
				Timestamp sk=new Timestamp(parsed.getTime());
				
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con;
				
		    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hongera","root","");
		    	
		    	
		    	GenerateCommission setCommision = new GenerateCommission();
		    	
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
				pw.println("<body>\n" + 
						"  	<div class=\"header\">\n<header class=\"header dark-bg\">\n" + 
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
						"	                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"glyphicon glyphicon-user\"></i>"+session.getAttribute("uname")+" <b class=\"caret\"></b></a>\n" + 
						"	                        <ul class=\"dropdown-menu animated fadeInUp\">\n" + 
						"	                          <li><a href=\"profile.html\" data-toggle=\"modal\" data-target=\"#myModal1\">Profile</a></li>\n" + 
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
						"                    <li class=\"current\"><a href=\"sales_person.jsp\"><i class=\"glyphicon glyphicon-home\"></i>Home</a></li>\n" + 
						"                    <li><a href=\"MyCommission\"><i class=\"glyphicon glyphicon-euro\"></i>View Commission</a></li>\n" + 
						"                    <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#myModal\"><i class=\"glyphicon glyphicon-shopping-cart\"></i>Enter Sales</a></li>\n" + 
						"                    \n" + 
						"                </ul>"+ 
						"             </div>\n" + 
						"		  </div>");
				pw.println("<div class=\"col-md-9\"><h3>My Commission</h3>");
				pw.println("<table class=\"table table-striped\" style=\"margin-top:2%;\" border=\"1\">");
				pw.println("<tr>"
						+ "<th>Date</th>"
						+ "<th>Total Amount</th>"
						+ "<th>Commission</th>"
						+ "</tr>");
				double total = 0.0;
				if(sdk.format(sq).compareTo(sdk.format(sk))<0){
					pw.println("your commission is less by a day");
		            System.out.println("Your cant recieve your commision");
		            String sql1 = "select sDate, sum(amount) as total, Branch.bName from Sales, Branch where Sales.personId=(select personId from Sales_Persons where username='"+username+"') and sDate>(select date from Commission where Commission.personId=(select personId from Sales_Persons where username='"+username+"') and sDate<'"+sdf.format(sq)+"' order by date desc limit 1) and Sales.branchId=Branch.branchId group by sDate";
			    	Statement state2 = con.createStatement();
		            ResultSet result = state2.executeQuery(sql1);
		            while(result.next()) {
						pw.println("<tr>");
						pw.println("<td style=\"color:red;\">"+result.getDate("sDate")+"</td>");
						pw.println("<td style=\"color:red;\">"+result.getDouble("total")+"</td>");
						pw.println("<td style=\"color:red;\">"+setCommision.calculateCommission(result.getString("bName"), result.getDouble("total"))+"</td>");		
						pw.println("</tr>");
						total+= setCommision.calculateCommission(result.getString("bName"), result.getDouble("total"));
			    	}
		        }
		        else{
		            Statement st = con.createStatement();
			    	String sql = "select sDate, sum(amount) as total, Branch.bName from Sales, Branch where Sales.personId=(select personId from Sales_Persons where username='"+username+"') and sDate>(select date from Commission where Commission.personId=(select personId from Sales_Persons where username='"+username+"') order by date desc limit 1) and Sales.branchId=Branch.branchId group by sDate";
			    	ResultSet result1 = st.executeQuery(sql);
			    	while(result1.next()) {
						pw.println("<tr>");
						pw.println("<td style=\"color:red;\">"+result1.getDate("sDate")+"</td>");
						pw.println("<td style=\"color:red;\">"+result1.getDouble("total")+"</td>");
						pw.println("<td style=\"color:red;\">"+setCommision.calculateCommission(result1.getString("bName"), result1.getDouble("total"))+"</td>");		
						pw.println("</tr>");
						total+= setCommision.calculateCommission(result1.getString("bName"), result1.getDouble("total"));
			    	}
		        }
				
				pw.println("<tr>"
						+"<td colspan=\"2\">Total</td>"
						+"<td style=\"color:blue;\">"+total+"</td>"
						+ "</tr>");
				pw.println("</table>");
				pw.println("<div class=\"modal fade\" id=\"myModal1\" role=\"dialog\" style=\"margin-top: 8%;\">\n" + 
						"    			<div class=\"modal-dialog\">\n" + 
						"		  			<div class=\"modal-content\">\n" + 
						"				        <div class=\"modal-header\">\n" + 
						"				          <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n" + 
						"				          <h4 class=\"modal-title\" style=\"text-align: center;font-weight:bold;\">Change Password</h4>\n" + 
						"				        </div>\n" + 
						"					        <form action=\"Register\" method=\"post\">\n" + 
						"						        <div class=\"modal-body\">\n" + 
						"		\n" + 
						"						          		<table>\n" + 
						"						          			\n" + 
						"						          			<tr>\n" + 
						"						          				<td>Old Password:</td>\n" + 
						"						          				<td colspan=\"3\"> <input type=\"password\" name=\"oldPass\" class=\"form-control\" style=\"width:150px;\"></td>\n" + 
						"						          			</tr>\n" + 
						"						          			<tr>\n" + 
						"						          				<td>New Password: </td>\n" + 
						"						          				<td><input type=\"password\" name=\"password\" class=\"form-control\" style=\"width:150px;\"></td>\n" + 
						"						          				<td>Confirm password:</td>\n" + 
						"						          				<td><input type=\"password\" name=\"cpassword\" class=\"form-control\" style=\"width:150px;\"></td>\n" + 
						"						          			</tr>\n" + 
						"						          			\n" + 
						"						          		</table>\n" + 
						"						         \n" + 
						"						        </div>\n" + 
						"						        <div class=\"modal-footer\">\n" + 
						"						          <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n" + 
						"						          <input type=\"submit\" class=\"btn btn-primary\" value=\"Change\">\n" + 
						"						        </div>\n" + 
						"					        </form>\n" + 
						"				      	</div>\n" + 
						"					</div>\n" + 
						"				</div>\n" + 
						"    	</div>\n" + 
						"    </div>");
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
		}
		
	}

}
