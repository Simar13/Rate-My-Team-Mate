<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" import="java.sql.*" import="bean.Rating" 
 import="util.DateTimeParser" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Rating</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style type="text/css">
div.stars {
  width: 270px;
  display: inline-block;
}
input.star { display: none; }
label.star {
  float: right;
padding: 10px;
font-size: 24px;
color: #444;
transition: all .2s;
}

input.star:checked ~ label.star:before {
  content: '\f005';
  color: #FD4;
  transition: all .25s;
}
input.star-5:checked ~ label.star:before {
  color: #FE7;
  text-shadow: 0 0 20px #952;
}
input.star-1:checked ~ label.star:before { color: #F62; }
label.star:hover { transform: rotate(-15deg) scale(1.3); }
label.star:before {
  content: '\f006';

  font-family: FontAwesome;
}

div.stars1 {
  width: 270px;
  display: inline-block;
}
input.star1 { display: none; }
label.star1 {
  float: right;

  padding: 10px;

  font-size: 24px;

  color: #444;
  transition: all .2s;
}

input.star1:checked ~ label.star1:before {
  content: '\f005';
  color: #FD4;
  transition: all .25s;
}
input.star1-5:checked ~ label.star1:before {
  color: #FE7;
  text-shadow: 0 0 20px #952;
}
input.star1-1:checked ~ label.star1:before { color: #F62; }
label.star1:hover { transform: rotate(-15deg) scale(1.3); }
label.star1:before {
  content: '\f006';

  font-family: FontAwesome;
}


div.starsL {
  width: 270px;
  display: inline-block;
}
input.starL { display: none; }
label.starL {
  float: right;
padding: 10px;
font-size: 24px;
color: #444;
transition: all .2s;
}

input.starL:checked ~ label.starL:before {
  content: '\f005';
  color: #FD4;
  transition: all .25s;
}
input.starL-5:checked ~ label.starL:before {
  color: #FE7;
  text-shadow: 0 0 20px #952;
}
input.starL-1:checked ~ label.starL:before { color: #F62; }
label.starL:hover { transform: rotate(-15deg) scale(1.3); }
label.starL:before {
  content: '\f006';

  font-family: FontAwesome;
}


div.starsT {
  width: 270px;
  display: inline-block;
}
input.starT { display: none; }
label.starT {
  float: right;
padding: 10px;
font-size: 24px;
color: #444;
transition: all .2s;
}

input.starT:checked ~ label.starT:before {
  content: '\f005';
  color: #FD4;
  transition: all .25s;
}
input.starT-5:checked ~ label.starT:before {
  color: #FE7;
  text-shadow: 0 0 20px #952;
}
input.starT-1:checked ~ label.starT:before { color: #F62; }
label.starT:hover { transform: rotate(-15deg) scale(1.3); }
label.starT:before {
  content: '\f006';

  font-family: FontAwesome;
}
.rcorners13 {	border-radius:5px;
	padding: 5px;
	size:30;
}
.style5 {color: #3b3a36}

.style7 {font-size: 12px}

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
.txt {
	background-color: #DFE2DB;
}
.posit {
   position:fixed; top:150px; 
}
</style>
</head>

<body>
<form id="form1" name="form1" method="get" action="/RateMyTeamMate/AddRating" >
<table width="100%" height="672" border="0" >
<tr><td><%@ include file="header.jsp" %></td></tr>
 <tr><td>
    <c:if test="${!empty message}">
				<div style="padding-bottom:15px; padding-top:15px; padding-left:45px; padding-right:15px; float:none;display: block;" class="error">${message}</div>
			</c:if>
<c:if test="${!empty error}">
				<div style="padding-bottom:15px; padding-top:15px; padding-left:45px; padding-right:15px; float:none;display: block;" class="error">${error}</div>
			</c:if><td></tr>
<tr> 
  <td height="605" align="center" valign="middle" bgcolor="#DFE2DB" class="Search"><table width="1038" height="588" border="0">
      <td width="438"><table width="416" height="571" border="0" class="posit">
        <tr>
          <td width="405" height="95"><table width="308" height="85" border="0">
         
            <tr>
              <td width="10" rowspan="3" background="images/client1.png">&nbsp;</td>
              <td width="288"><label>Name :<br />
                <input type="text" name="name" id="name" readonly="readonly" value="<%=request.getAttribute("name")%>" class="txt"  disabled="disabled" style="border:none"/>
                </label></td>
            </tr>
            <tr>
              <td><label>University :<br />
                <input type="text" name="university" id="university" readonly="readonly" value="<%=request.getAttribute("university")%>" class="txt"  disabled="disabled" style="border:none"/>
                </label></td>
            </tr>
            <tr><td><label>Course :<br />
              <input type="text" name="course" id="course" readonly="readonly" value="<%=request.getAttribute("course")%>" class="txt"  disabled="disabled" style="border:none"/></label></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td height="56" align="left"><input name="rate" type="submit" class="bcolour1" id="rate" value="Rate This Person" size="20" onmouseover="this.style.color = 'white'" onmouseout="this.style.color = '#3b3a36'" /></td>
        </tr>
        <tr>
         <td height="410"><table width="361" height="402" border="0">
            <tr>
            
              <td width="362" height="55" align="left"><div>OVERALL RATING</div></p>
                <div><progress value="<%=request.getAttribute("avg")%>" max="5" class="progress-bar-success" title="<%=request.getAttribute("avg")%>"></progress></div> </td>
              </tr>
            <tr>
              <td height="55" align="left"> <div>&nbsp;&nbsp; HELPFULLNESS</div>
              <div><progress value="<%=request.getAttribute("helpfulness")%>" max="5" class="progress-bar-success" title="<%=request.getAttribute("helpfulness")%>" ></progress></div>   </td>
              </tr>
            <tr>
              <td height="55" align="left"><div>&nbsp;<span class="style5">KNOWLEDGEABLE</span></div>
              <div><progress value="<%=request.getAttribute("knowledgeable")%>" max="5" class="progress-bar-success" title="<%=request.getAttribute("knowledgeable")%>"></progress></div>   </td>
              </tr>
            <tr>
              <td height="55" align="left"><div>&nbsp;&nbsp;<span class="style5">LEADERSHIP SKILLS</span></div>
            <div><progress value="<%=request.getAttribute("leadership")%>" max="5" class="progress-bar-success" title="<%=request.getAttribute("leadership")%>"></progress></div>     </td>
              </tr>
            <tr>
              <td height="55" align="left"><div>&nbsp;&nbsp;<span class="style5"> TEAM PARTICIPATION</span></div>
              <div><progress value="<%=request.getAttribute("teamParticipation")%>" max="5" class="progress-bar-success" title="<%=request.getAttribute("teamParticipation")%>"></progress></div>   </td>
            </tr>
          </table></td>
        </tr>
      </table></td>
      <td width="590" valign="top">
      <%ArrayList<Rating> list = (ArrayList<Rating>) request.getAttribute("list");
          	for(Rating obj : list){
          		
          		 ArrayList<String> listTag = obj.getTag();
                 StringBuffer sb = new StringBuffer();
                 Iterator<String> itr = listTag.iterator();
         		int i=0;
         		while (itr.hasNext()) {
         			if(i==0){
         			sb.append(itr.next());
         			i++;
         			}
         			else{
         				sb.append(" , ");
         				sb.append(itr.next());
         			}
         			
         		}
              
          %>
      
      <table width="590" height="230" border="0" class="jumbotron">
        <tr>
          <td width="224" height="40" align="center">
            <%=DateTimeParser.currentTimeStamp()%></td><td width="356">Tags          
                   
            <p><%=sb%></p></td>
        </tr>
        <tr>
          <td height="42" align="center"><table width="174" height="34" border="0">
              <tr>
                <td width="50">&nbsp;
                    <progress value="<%=obj.getHelpfulness() %>" max="5" class="progress-bar-success" title="<%=obj.getHelpfulness() %>"></progress></td>
                <td width="108" align="center"><span class="style7">HELPFULLNESS</span></td>
              </tr>
          </table></td>
          <td rowspan="4" align="center" valign="top">COMMENTS
              <p> <%=obj.getComment() %> </p></td>
        </tr>
        <tr>
          <td height="45" align="center"><table width="174" height="34" border="0">
              <tr>
                <td width="50">&nbsp;
                    <progress value="<%=obj.getKnowledgeable() %>" max="5" class="progress-bar-success" title="<%=obj.getKnowledgeable()%>"></progress></td>
                <td width="108" align="center" class="style7">KNOWLEGEABLE</td>
              </tr>
          </table></td>
        </tr>
        <tr>
          <td height="41" align="center"><table width="174" height="34" border="0">
              <tr>
                <td width="50">&nbsp;
                    <progress value="<%=obj.getLeadership() %>" max="5" class="progress-bar-success" title="<%=obj.getLeadership()%>"></progress></td>
                <td width="108" align="center" class="style7">LEADERSHIP SKILLS</td>
              </tr>
          </table></td>
        </tr>
        <tr>
          <td height="45" align="center"><table width="174" height="34" border="0">
              <tr>
                <td width="50">&nbsp;
                    <progress value="<%=obj.getTeamParticipation() %>" max="5" class="progress-bar-success" title="<%=obj.getTeamParticipation()%>"></progress></td>
                <td width="108" align="center" class="style7">TEAM PARTICIPATION</td>
              </tr>
          </table></td>
        </tr>
      </table>
      <%}%></td>
    </tr>
  </table></td>
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
