<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="horizontalmenu.css">
<link rel="stylesheet" type="text/css" href="TextBoxCSSCode.css">
<link rel="stylesheet" type="text/css" href="ButtonCSSCode.css">
<title>DeleteEmployee</title>
</head>
<body>
<div id='cssmenu'>
<ul>
	<li ><a href="service?action=create"><span>CreateUser</span></a></li>
	<li><a href="service?action=display"><span>DisplayUser</span></a></li>
	<li><a href="service?action=modify"><span>ModifyUser</span></a></li>
	<li><a href="service?action=search"><span>SearchUser</span></a></li>
	<li class='active'><a href=""><span>DeleteUser</span></a></li>
	<li class='last'><a href="service?action=searchany"><span>SearchByAnyValue</span></a></li>
</ul>
</div>
<br>
<div align="center">
<form id="form1" name="form1" method="post" action="service?action=AfterDelete">
<table width="500">
	<tr>
		<td align="right">
		<label for="kin">Enter KIN ID</label>
		</td>
		<td>
    	<input type="text" name="kin" id="kin" class="TextBox" />
		</td>
	</tr>
</table>
<input type="submit" name="delete2" id="delete" value="Delete" class="myButton" />
</form>
</div>
</body>
</html>