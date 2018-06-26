<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Hongera Center</title>
    <!-- for rfreshing the page every after 2 minutes -->
    <meta http-equiv="refresh" content="120; URL=http://localhost:8088/Hongera/sales_person.jsp">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="css/styles.css" rel="stylesheet">

   
  </head>
  <body>
  	<%
  		
  		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  		response.setHeader("Pragma", "no-cache");//HTTP 1.0
  		response.setHeader("Expires", "0");//Proxies
  		
  		if(request.getSession().getAttribute("uname")!=null){
  			session.setMaxInactiveInterval(120);
  		}
  		else{
  			response.sendRedirect("login.html");
  		}
  	
  	%>
  	<div class="header">
	     
		  <header class="header dark-bg">
		      <div class="toggle-nav">
		        <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
		      </div>
		
		      <div class="top-nav notification-row">
		
		        <ul class="nav pull-right top-menu">
		          <!-- user login dropdown start-->
		          <li class="dropdown">
		            <ul class="dropdown-menu extended logout">
		              <div class="log-arrow-up"></div>
		              <li class="eborder-top">
		                <a href="#"><i class="icon_profile"></i> My Profile</a>
		              </li>
		              <li>
		                <a href="login.html"><i class="icon_key_alt"></i> Log Out</a>
		              </li>
		            </ul>
		          </li>
		          <!-- user login dropdown end -->
		        </ul>
		        <!-- notificatoin dropdown end-->
		      </div>
		      <div class="row" >
	           <div class="col-md-5">
	              
	              <div class="logo">
	                 <h1><a href="index.html">Hongera Kids Clothing</a></h1>
	              </div>
	           </div>
	          
	           <div class="col-md-7">
	              <div class="navbar navbar-inverse" role="banner">
	                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
	                    <ul class="nav navbar-nav">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i>
	                        	<%= request.getSession().getAttribute("uname") %>
	                        <b class="caret"></b></a>
	                        <ul class="dropdown-menu animated fadeInUp">
	                          <li><a href="#" data-toggle="modal" data-target="#myModal1">Profile</a></li>
	                          <li><a href="Logout">Logout</a></li>
	                        </ul>
	                      </li>
	                    </ul>
	                  </nav>
	              </div>
	           </div>
	        </div>
		    </header>
	        
	</div>

    <div class="page-content">
    	<div class="row">
		  <div class="col-md-3">
		  	<div class="sidebar content-box" style="display: block;width:;">
                <ul class="nav">
                    <!-- Main menu -->
                    <li class="current"><a href="sales_person.jsp"><i class="glyphicon glyphicon-home"></i>Home</a></li>
                    <li><a href="MyCommission"><i class="glyphicon glyphicon-euro"></i>View Commission</a></li>
                    <li><a href="#" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-shopping-cart"></i>Enter Sales</a></li>
                    
                </ul>
             </div>
		  </div>
		  <div class="col-md-9">
		  			<div class="modal fade" id="myModal" role="dialog">
    				<div class="modal-dialog">
    
				      <!-- Modal content-->
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title" style="text-align: center;font-weight:bold;">Enter Sales Details</h4>
				        </div>
				        <form action="EnterSale" method="post">
				        <div class="modal-body">
				          
				          		Product Name: <input type="text" name="productName" class="form-control"><br><br>
				          		Quantity: <input type="text" name="quantity" class="form-control"><br><br>
				          		Amount: <input type="text" name="amount" class="form-control"><br><br>
				         
				        </div>
				        <div class="modal-footer">
				          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				          <input type="submit" class="btn btn-primary" value="submit">
				        </div>
				        </form>
				      </div>
				      
				    </div>
  				</div>
		  </div>
		  
		  <div class="modal fade" id="myModal1" role="dialog" style="margin-top: 8%;">
    			<div class="modal-dialog">
		  			<div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title" style="text-align: center;font-weight:bold;">Change Password</h4>
				        </div>
					        <form action="ChangePassword" method="post">
						        <div class="modal-body">
		
						          		<table>
						          			
						          			<tr>
						          				<td>Old Password:</td>
						          				<td colspan="3"> <input type="password" name="oldPass" class="form-control" style="width:150px;"></td>
						          			</tr>
						          			<tr>
						          				<td>New Password: </td>
						          				<td><input type="password" name="password" class="form-control" style="width:150px;"></td>
						          				<td>Confirm password:</td>
						          				<td><input type="password" name="cpassword" class="form-control" style="width:150px;"></td>
						          			</tr>
						          			
						          		</table>
						         
						        </div>
						        <div class="modal-footer">
						          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						          <input type="submit" class="btn btn-primary" value="Change">
						        </div>
					        </form>
				      	</div>
					</div>
				</div>
    	</div>
    </div>

    <footer style="position: fixed;bottom: 0;right: 0;left: 0;">
         <div class="container">
         
            <div class="copy text-center">
               Incubee 2018 <a href='#'>Hongera Kids Clothing</a>
            </div>
            
         </div>
    </footer>


    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>
  </body>
</html>