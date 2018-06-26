import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Commission extends HttpServlet {
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
				
				Date date1=new Date();
				SimpleDateFormat sdk=new SimpleDateFormat("HH:mm:ss");
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Timestamp sq=new Timestamp(date1.getTime());
				Date parsed = sdk.parse("18:00:00");
				Timestamp sk=new Timestamp(parsed.getTime());
				
				Date date=new Date();
			    SimpleDateFormat new_date =new SimpleDateFormat ("yyyy-MM-dd");
				
			    
			    GenerateCommission setCommision = new GenerateCommission();
		    	
		    		
		    		String sql1 = "INSERT INTO Commission ( adminId, personId, cAmount, date, salesDate) VALUES (?,?,?,?,?)";
		    		//state2.executeUpdate(sql1);
		    	
		    	//out.println(row+" rows affected");
			    
			    ResultSet result1 = st.executeQuery("select adminId from Admin");
			    int admin_id = 0;
			    while(result1.next()) {
			    	admin_id = result1.getInt("adminId");
			    }
			    
			    
			    
			    int i=0;
			    PreparedStatement prepare = con.prepareStatement(sql1);
			    
			    
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
						"		  <div class=\"col-md-9\">");
				pw.println("<h3>Generate Commission by:</h3>"
						+ "<a href=\"MonthlyCommission\" class=\"nav-link\">View Monthly Commission</a>");
			    pw.println("<table class=\"table table-striped\" style=\"margin-top:2%;\" border=\"1\">");
				pw.println("<tr>"
						+ "<th>Branch</th>"
						+ "<th>Amount</th>"
						+ "<th>Sales Date</th>"
						+ "<th>Commission Amount</th>"
						+ "</tr>");
				if(sdk.format(sq).compareTo(sdk.format(sk))<0){
					ResultSet result = st.executeQuery("select sDate, sum(amount) as total, bName,Sales.personId, Sales_Persons.fName, Sales_Persons.lName from Sales, Branch, Sales_Persons where Sales.personId=Sales_Persons.personId && Sales.branchId=Branch.branchId and sDate>(select date from Commission order by date desc limit 1) and sDate<'"+sdf.format(sq)+"' group by Sales.personId,Sales.sDate order by sDate desc");
				    
				    ArrayList<String> branch_name = new ArrayList<>();
				    ArrayList<Double> amount = new ArrayList<>();
				    ArrayList<Integer> personId = new ArrayList<>();
				    ArrayList<String> sDate = new ArrayList<>();
				    while(result.next()) {
				    	branch_name.add(result.getString("bName"));
				    	amount.add(result.getDouble("total"));
				    	personId.add(result.getInt("personId"));
				    	sDate.add(result.getString("sDate"));
				    	
				    }
					while(i<branch_name.size()) {
						pw.println("<tr>"
										+ "<td>"+branch_name.get(i).toString()+"</td>"
										+ "<td>"+amount.get(i).toString()+"</td>"
										+ "<td>"+sDate.get(i).toString()+"</td>"
										+ "<td>"+setCommision.calculateCommission(branch_name.get(i).toString(), amount.get(i))+"</td>"
										+ "</tr>");
				    	prepare.setInt(1, admin_id);
				    	prepare.setInt(2, personId.get(i));
				    	prepare.setDouble(3, setCommision.calculateCommission(branch_name.get(i).toString(), amount.get(i)));
				    	prepare.setString(4, sdf.format(sq));
				    	prepare.setString(5, sDate.get(i));
				    	prepare.executeUpdate();
				    	
				    	i++;
				    }
				}
				else {
					ResultSet result = st.executeQuery("select sDate, sum(amount) as total, bName,Sales.personId, Sales_Persons.fName, Sales_Persons.lName from Sales, Branch, Sales_Persons where Sales.personId=Sales_Persons.personId && Sales.branchId=Branch.branchId and sDate>(select date from Commission order by date desc limit 1) group by Sales.personId,Sales.sDate order by sDate desc");
				    
				    ArrayList<String> branch_name = new ArrayList<>();
				    ArrayList<Double> amount = new ArrayList<>();
				    ArrayList<Integer> personId = new ArrayList<>();
				    ArrayList<String> sDate = new ArrayList<>();
				    while(result.next()) {
				    	branch_name.add(result.getString("bName"));
				    	amount.add(result.getDouble("total"));
				    	personId.add(result.getInt("personId"));
				    	sDate.add(result.getString("sDate"));
				    	
				    }
					while(i<branch_name.size()) {
						pw.println("<tr>"
								+ "<td></td>"
										+ "<td>"+branch_name.get(i).toString()+"</td>"
										+ "<td>"+amount.get(i).toString()+"</td>"
										+ "<td>"+sDate.get(i).toString()+"</td>"
										+ "<td>"+setCommision.calculateCommission(branch_name.get(i).toString(), amount.get(i))+"</td>"
										+ "</tr>");
				    	prepare.setInt(1, admin_id);
				    	prepare.setInt(2, personId.get(i));
				    	prepare.setDouble(3, setCommision.calculateCommission(branch_name.get(i).toString(), amount.get(i)));
				    	prepare.setString(4, sdf.format(sq));
				    	prepare.setString(5, sDate.get(i));
				    	prepare.executeUpdate();
				    	
				    	i++;
				    }
				}
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
