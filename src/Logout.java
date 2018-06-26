import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  		response.setHeader("Pragma", "no-cache");//HTTP 1.0
  		response.setHeader("Expires", "0");//Proxies
		request.getSession().invalidate();
		
		request.getRequestDispatcher("login.html").include(request, response);
		
		out.println("<p style=\"text-align:right;\">You are successfuly logged out</p>");
		out.close();
		
	}

}
