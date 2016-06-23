<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<title>SignIn</title>
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
.bcolour {	border-radius:10px;
	padding: 5px;
	background-color:#FFF056;
	font-style: italic;
	font: Bold;
	width: 10em;  
	height: 3em;
}
.bcolour1 {border-radius:10px;
	padding: 5px;
	background-color:#FFF056;
	font-style: italic;
	font: Bold;
	width: 10em;  
	height: 3em;
}
.rcorners131 {border-radius:5px;
	padding: 5px;
	size:30;
}
-->
</style>
<meta name="google-signin-client_id" content="237793661090-42rgsi0an1fnaav4kveon2aoth7vsu1h.apps.googleusercontent.com">
</head>

<body>
<form id="form1" name="form1" method="post" action="/RateMyTeamMate/Login" >
<table width="100%" height="445" border="0" >
<tr><td><%@ include file="header.jsp" %></td></tr>
 <c:if test="${!empty message}">
          <div style="padding-bottom:15px; padding-top:15px; padding-left:45px; padding-right:15px; float:none;display: block;" class="error">${message}</div>
        </c:if>
        <c:if test="${!empty error}">
          <div style="padding-bottom:15px; padding-top:15px; padding-left:45px; padding-right:15px; float:none;display: block;" class="error">${error}</div>
        </c:if>
<tr> 
  <td height="700" align="center" valign="middle" class="Search" bgcolor="#DFE2DB"><table width="367" border="0" align="center" cellpadding="0" cellspacing="0" style="	style=border-collapse:collapse;" class="style14"><tr>
    <td><table width="367" border="0" align="center" class="jumbotron" >
      <tr>
        <td align="center"><label class="style5">LOGIN</label></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td align="center" class="style11"> Username</td>
      </tr>
      <tr>
        <td align="center"><input type="text" name="UserName" id="UserName" class="rcorners131" required pattern="[A-Za-z0-9]{5,20}" title="Must contain alphabets & numbers only. Min length is 5 and max length is 20." maxlength="20" /></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td align="center" class="style11">Password</td>
      </tr>
      <tr>
        <td align="center"><input type="password"  name="Password" id="Password" class="rcorners131" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters." maxlength="20" /></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td align="center"><label>
          <input name="SignIn" type="submit" class="bcolour1" id="SignIn" value="SignIn" size="20" onmouseover="this.style.color = 'white'" onmouseout="this.style.color = '#3b3a36'" />
        </label></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
      </tr>
      <tr><td align="center"><a href="#" class="sc-btn sc--flat sc--facebook fa fa-facebook" id="login"> Facebook</a></td>
      </tr>
      <tr><td align="center">&nbsp;&nbsp;</td>
      </tr>
      <tr>
       <td align="center"> <div class="g-signin2" data-onsuccess="onSignIn"></div></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td align="center"><a href="resetpassword">Forgot Your password?</a></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr></table></td>
</tr>
<tr><td><%@ include file="footer.jsp" %></td></tr>
</table>
</form>
<script>
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
	  $.ajax({
  	  type: 'POST',
  	  url: '/RateMyTeamMate/Login',
  	  data: JSON.stringify(profile)                     	  
  	 
  	});
	  location.replace('MainPage.jsp'); 
  alert(data);
	}
	
	
        (function(d, s, id){
           var js, fjs = d.getElementsByTagName(s)[0];
           if (d.getElementById(id)) {return;}
           js = d.createElement(s); js.id = id;
           js.src = "//connect.facebook.net/en_US/sdk.js";
           fjs.parentNode.insertBefore(js, fjs);
         }(document, 'script', 'facebook-jssdk'));
      </script>
		<script src="https://apis.google.com/js/platform.js" async defer></script>

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
      <script src="http://cdn.rawgit.com/ryandrewjohnson/jquery-fblogin/master/dist/jquery.fblogin.min.js"></script>
      <script>
        $(function () {

            var $successCallback = $('.success-callback');

            $('.permissions-container').on('click', 'input[type="checkbox"]', function () {
              $('.permissions-selected').text(getPermissions());
            });

            function getPermissions() {
              var permisssions = '';

              $('.permissions-container input[type="checkbox"]:checked').each(function () {
                permisssions += $(this).val() + ',';
              });

              permisssions = permisssions.substring(0, (permisssions.length - 1));

              return permisssions;
            }

            $('.reset-btn').click(function () {
              $('.permissions-container input[type="checkbox"]').attr('checked', false);
              $('.permissions-selected').text('');
              $successCallback.html($successCallback.data('default'));
              $('.toggle-check').text($('.toggle-check').data('check'));
            });

            $('.toggle-check').click(function () {
              var $checkboxes = $(this).parent('h3').siblings('.permissions-list').find('input[type="checkbox"]');
              if($(this).text() === $(this).data('check')) {
                $(this).text($(this).data('uncheck'));
                $checkboxes.prop('checked', true);
              }
              else {
                $(this).text($(this).data('check'));
                $checkboxes.prop('checked', false);
              }
            });

            $('#login').click(function () {

                var fbPermissions = getPermissions();
				console.log(fbPermissions);
                $.fblogin({
                    fbId: '547338912106905',
                    permissions: fbPermissions,
                    fields: 'first_name,last_name,locale,email,birthday',
                })
                .fail(function (error) {
                  console.log('error callback', error);
                })
                .progress(function (data) {
                    console.log('progress', data);
                })
                .done(function (data) {
                    console.log('done everything', data);
                    $('.success-callback').html(JSON.stringify(data, null, '\t'));
                    $.ajax({
                    	  type: 'POST',
                    	  url: '/RateMyTeamMate/Login',
                    	
                    	  data: JSON.stringify(data)                     	  
                    	 
                    	});
                    alert(data);
                   location.replace('MainPage.jsp'); 
                });
            });


        });
    </script>
    <div id="google_translate_element"></div><script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', includedLanguages: 'fr', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
}
</script><script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
</body>
</html>
