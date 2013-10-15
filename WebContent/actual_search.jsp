<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="horizontalmenu.css">
<link rel="stylesheet" type="text/css" href="TableCSSCode.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SearchResult</title>
</head>
<body>
<div id='cssmenu'>
<ul>
	<li ><a href="service?action=create"><span>CreateUser</span></a></li>
	<li><a href="service?action=display"><span>DisplayUser</span></a></li>
	<li><a href="service?action=modify"><span>ModifyUser</span></a></li>
	<li class='active'><a href="service?action=search"><span>SearchUser</span></a></li>
	<li><a href="service?action=delete"><span>DeleteUser</span></a></li>
	<li class='last'><a href="service?action=searchany"><span>SearchByAnyValue</span></a></li>
</ul>
</div>

<br>

<table class="CSSTableGenerator">
	<tr>
		<th>KIN Id</th>
		<th>Name</th>
		<th>Email ID</th>
		<th>Contact</th>
		<th>Date Of Joining</th>
		<th>City</th>
		<th>Role</th>
		<th>Department</th>
		<th>SubDepartment</th>
	</tr>
	
	<tr>
		<td><c:out value="${employee.kin}"/></td>
		<td><c:out value="${employee.name}"/></td>
		<td><c:out value="${employee.email}"/></td>
		<td><c:out value="${employee.contact}"/></td>
		<td><c:out value="${employee.doj}"/></td>
		<td><c:out value="${employee.city}"/></td>
		<td><c:out value="${employee.role.r_name}"/></td>
		<td><c:out value="${employee.dept.dept_name}"/></td>
		<td><c:out value="${employee.sub_dept.subDept.subdept_name}"/></td>
	</tr>
</table>
</body>
</html>