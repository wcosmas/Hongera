import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EnterSale extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.setMaxInactiveInterval(120);
			String username = (String)session.getAttribute("uname");
			try{
				String productName=request.getParameter("productName");
				int quantity=Integer.parseInt(request.getParameter("quantity"));
				int amount=Integer.parseInt(request.getParameter("amount"));
				
				
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hongera","root","");
				
				Statement stat=con.createStatement();
				
				String ad = "select adminId, branchId, personId from Sales_Persons where username='"+username+"'";
				ResultSet rst=stat.executeQuery(ad);
				int admin_id = 0, branch_id=0, person_id = 0;
				
				while(rst.next()){
					admin_id=rst.getInt("adminId");
					branch_id=rst.getInt("branchId");
					person_id = rst.getInt("personId");
				}
				
				String sql="insert into Sales(adminId,personId,branchId,productName,quantity,amount,sDate) values("+admin_id+","+person_id+","+branch_id+",'"+productName+"',"+quantity+","+amount+",'2018-04-02')";
				stat.executeUpdate(sql);
				
				out.println("<h2>Inserted successfully</h2>");
				request.getRequestDispatcher("sales_person.jsp").include(request, response);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
			out.println("<p>First Login</p>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
	}


	

}
