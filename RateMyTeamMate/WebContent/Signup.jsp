<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SignUp</title>
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
.rcorners13 {	border-radius:5px;
	padding: 5px;
	size:30;
}
.style5 {
	color: #3b3a36;
	font-style: italic;
	font: bold;
	font-size: 20px;
}
.style11 {
	color: #3b3a36;
}
.style16 {
	border-radius:5px;
	padding: 5px;
	background-color:#FFF056;
	color:#3b3a36;
	size:20;
	border-bottom-width: medium;
	border-left-style: solid;
}
.style12 {border-radius:5px;
	padding: 5px;
background-color:#FFF056;
	color:#3b3a36;
	size:20;
}
.style13 {border-radius:5px;
	padding: 5px;
background-color:#FFF056;
	color:#3b3a36;
	size:20;
}
.style14 {border-radius:5px;
	padding: 5px;
	border-color:#FFF056;
	border-width:thin;

}
.style22 {border-radius:5px;
	padding: 5px;
background-color:#FFF056;
	color:#3b3a36;
	size:20;
}
.bcolour {	border-radius:10px;
	padding: 5px;
	background-color:#FFF056;
	font-style: italic;
	font: Bold;
	width: 10em;  
	height: 3em;
}
-->
</style>
</head>

<body>
<form id="form1" name="form1" method="post" action="/RateMyTeamMate/SignUp" >
<table width="100%" height="918" border="0" >
<tr><td><%@ include file="header.jsp" %></td></tr>
<tr> 
<c:if test="${!empty message}">
				<div style="padding-bottom:15px; padding-top:15px; padding-left:45px; padding-right:15px; float:none;display: block;" class="error">${message}</div>
			</c:if>
<c:if test="${!empty error}">
				<div style="padding-bottom:15px; padding-top:15px; padding-left:45px; padding-right:15px; float:none;display: block;" class="error">${error}</div>
			</c:if>
  <td height="870" align="center" valign="middle" class="Search" bgcolor="#DFE2DB"><table width="367" height="800" border="0" align="center" cellpadding="0" cellspacing="0" style="	style=border-collapse:collapse;" class="style14">
  
  <tr><td><table width="367" border="0" align="center" 
  class="jumbotron" >
    
    <tr>
      <td width="357" height="23" align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center"><label class="style5">Sign Up For Account</label></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="style11">First Name</td>
    </tr>
    <tr>
      <td align="center"><input type="text" name="FirstName" id="FirstName" class="rcorners13" autofocus required pattern="[A-Za-z ]{2,20}" title="Must contain alphabets only. Min length is 2 and max length is 20." maxlength="20"/></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="style11">Last Name</td>
    </tr>
    <tr>
      <td align="center"><input type="text" name="LastName" id="LastName" class="rcorners13" autofocus required pattern="[A-Za-z ]{2,20}" title="Must contain alphabets only. Min length is 2 and max length is 20." maxlength="20" /></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="style11">Your Email Address</td>
    </tr>
    <tr>
      <td align="center"><input type="email" name="Email" id="Email" class="rcorners13" /></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="style11">Name Of University/College</td>
    </tr>
    <tr>
      <td align="center"><input type="text" name="UniName" id="UniName" class="rcorners13" autofocus required pattern="[A-Za-z .]{2,20}" title="Must contain alphabets only. Min length is 2 and max length is 20." maxlength="20" /></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="style11">Department</td>
    </tr>
    <tr>
      <td align="center"><input type="text" name="Department" id="Department" class="rcorners13" autofocus required pattern="[A-Za-z .]{2,20}" title="Must contain alphabets only. Min length is 2 and max length is 20." maxlength="20" /></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="style11">Phone Number</td>
    </tr>
    <tr>
      <td align="center"><input type="text" name="PhoneNumber" id="PhoneNumber" class="rcorners13" maxlenght="12" autofocus required pattern="[0-9]{10}" title="Must contain integers up to 10 digit." maxlength="20" /></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="style11">User Name</td>
    </tr>
    <tr>
      <td align="center"><label>
        <input type="text" name="UserName" id="UserName" class="rcorners13" required pattern="[A-Za-z0-9]{5,20}" title="Must contain alphabets & numbers only. Min length is 5 and max length is 20." maxlength="20" />
      </label></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="style11">Password</td>
    </tr>
    <tr>
      <td align="center"><input type="password" name="Password" id="Password" class="rcorners13" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters." maxlength="20" /></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center"><input name="SignUp" type="submit" class="bcolour" id="SignUp" value="SignUp" size="20" onmouseover="this.style.color = 'white'" onmouseout="this.style.color = '#3b3a36'" /></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
  </table></td></tr></table></td>
</tr>
<tr><td><%@ include file="footer.jsp" %></td></tr>
</table>
</form>
<div id="google_translate_element"></div><script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', includedLanguages: 'fr', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
}
</script><script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
</body>
</html>
