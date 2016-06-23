<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<style type="text/css">
<!--
#Search {
width:350px;
padding: 5px;
padding-right: 40px;
outline: none;
border: 2px solid #999999;
border-radius: 10px;
background-color: #FBFBFB;
font-family: Cambria, Cochin, Georgia, serif;
font-size: 16px;
background-position: 350px -10px;
background-image: url('//www.kirupa.com/images/search.png');
background-repeat: no-repeat;
}
.bcolour{	border-radius:10px;
	padding: 5px;
	background-color:#FFF056;
	font-style: italic;
	font: Bold;
	width: 10em;  
	height: 3em;
}
.style2 {
	color: #CCCCCC;
	font-style: italic;
}
.style5 {
	color: #3b3a36;
	font-style: italic;
	font: bold;
	font-size: 24px;
}
.style7 {
	color: #FFFFFF;
	font-style: italic;
	font: bold;
}
.style8 {color: #333333}
.style10 {
	font-size: 24px;
	font-weight: bold;
	font-style: italic;
	color: #333333;
}
.rcorners13 {	border-radius:10px;
	padding: 5px;
	font-style: italic;
	font: Bold;
}
.SearchAzax {	
padding-right: 40px;
height:30px;
outline: none;
border: 2px solid #999999;
border-radius: 10px;
background-color: #FBFBFB;
font-family: Cambria, Cochin, Georgia, serif;
font-size: 16px;
background-position: 650px -10px;
background-image: url('//www.kirupa.com/images/search.png');
background-repeat: no-repeat;
}
-->
</style>



<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">


</head>

<body>
<form id="form1" name="form1" method="get" action="/RateMyTeamMate/AddRating" >
      <div class="search-container">
		<div class="ui-widget">
<table width="100%" height="891" border="0" >
<tr><td><%@ include file="header.jsp" %></td></tr>
<c:if test="${!empty message}"><div style="padding-bottom:15px; padding-top:15px; padding-left:45px; padding-right:15px; float:none;display: block;" class="error">${message}</div></c:if>
<c:if test="${!empty error}">
				<div style="padding-bottom:15px; padding-top:15px; padding-left:45px; padding-right:15px; float:none;display: block;" class="error">${error}</div>
			</c:if>
<tr> <td height="500" align="center" valign="middle" bgcolor="#DFE2DB" class="Search"><table width="607" height="148" border="0">
      <tr>
        <td width="601" align="center" class="style5">Whom you want to rate ?</td>
      </tr>
      <tr>
        <td align="center"><input  placeholder="Enter name to rate..." type="text" name="search" id="search" class="search SearchAzax" align="middle" size="70" class="SearchAzax" /></td>
      </tr>
      <tr>
       
      <td align="center"><label>
        <input name="ratebutton" type="submit" class="bcolour" id="ratebutton"  value="Rate" onmouseover="this.style.color = 'white'" onmouseout="this.style.color = '#3b3a36'"/>
      </label>     </td>
    </tr>
  
    </table> </td>
  </tr>
  
  <tr>
    <td height="500" align="center" valign="middle" bgcolor="#FFF056" class="Search"><table width="422" border="0"  >
      <tr>
        <td width="547" height="54" align="center"><h2 class="style5">Find what you're looking for</h2></td>
      </tr>
      <tr>
        <td height="101" align="center"><table width="407" height="83" border="0">
            <tr>
              <td align="center"><div class="col-md-4 col-sm-8">
                  <div class="service-item"> <span class="fa-stack fa-4x"> <i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-users fa-stack-1x text-primary"></i> </span>
                      <button type="button" class="btn btn-success" onclick="window.location = 'SignUp.jsp'" >ADD A<br />
                        GROUP MATE</button>
                  </div>
              </div></td>
              <td align="center"><div class="col-md-4 col-sm-8">
                  <div class="service-item"> <span class="fa-stack fa-4x"> <i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-search fa-stack-1x text-primary"></i> </span>
                      <button type="button" class="btn btn-success" onclick="window.location = 'SignUp.jsp'" >LOOKINNG FOR A <br />
                        GROUP MATE</button>
                  </div>
              </div></td>
            </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
   <tr><td><%@ include file="footer.jsp" %></td></tr>
</table>
 </form> 
<script>

jQuery("#search").autocomplete({
    source: function (request, response) {
      
    	$.ajax({			
			url : '/RateMyTeamMate/SearchStudent',
			type : 'GET',
			data : {
				query : request.term
			},
			dataType : 'json',
			success : function(data) {
			
			response(data);
			//	alert(data);
				
			}
		});
    },
    
});


function change() {
	var y = document.getElementById("div1");
		y.style.bgColor = '#DFE2DB';
		
        }
		function back() {
	var x = document.getElementById("div1");
		x.bgColor="#191919";
        }
		function Act() {
	var x = document.getElementById("div1");
		window.location ="Signup.jsp"
        }
		
</script>
  <div id="google_translate_element"></div><script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', includedLanguages: 'fr', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
}
</script><script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>  
    
</body>
</html>
