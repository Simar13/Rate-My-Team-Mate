<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
</head>
<body>


<nav><div><%@ include file="header.jsp" %>
</div> </nav>

<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-3 sidenav">
    
     <table width="185" height="85"  border="0" class="fix" >
            <tr>
              <td width="294" align="left"><label> Name :
                <input type="text" name="name" id="name" value="<%=request.getAttribute("name")%>"/>
              </label></td>
            </tr>
            <tr>
              <td align="left"><label> Program :
                <input type="text" name="course" id="course" value="<%=request.getAttribute("course")%>"/>
              </label></td>
            </tr>
            <tr>
              <td align="left"><label> University :
                <input type="text" name="university" id="university" value="<%=request.getAttribute("university")%>"/>
              </label></td>
            </tr>
            <tr>
              <td align="left"><label> Department :
                <input type="text" name="department" id="department" value="<%=request.getAttribute("department")%>"/>
              </label></td>
            </tr>
          </table>
    </div>
    <div class="col-sm-8 text-left"> 
      <h1>Welcome</h1>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
      <hr>
      <h3>Test</h3>
      <p>Lorem ipsum...</p>
    </div>
    </div>
  </div>


<div>
  <%@ include file="footer.jsp" %>
</div>

</body>
</html>
