<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Untitled Document</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="MainPage.jsp"><h2></>RateMyTeamMate</h2></a>
    </div>
        <ul class="dropdown-menu">
          <li><a href="#"></a></li>
        </ul>
      </li>
      <li><a href="#"></a></li>
      <li><a href="#"></a></li>
      <li><a href="#"></a></li>
    </ul>
    <% String bang =(String) session.getAttribute("json");
         System.out.println("IN header burrr"+ bang );
         if(bang==null){  System.out.println("IN header burrrrrr 2"); %>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="Signup.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="SignIn.jsp"><span class="fa fa-sign-in"></span> Login</a></li>
           
       <% } 
             else {%> 
              <li><a href="/RateMyTeamMate/Login?logout=true"><span class="fa fa-sign-out"></span> SignOut</a></li>
           <%} 
             %> 
    </ul>
  </div>
</nav>
</body>
</html>
