<%@ page import="com.flp4.ems.domain.Employee,com.flp4.ems.service.*,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="horizontalmenu.css">
<link rel="stylesheet" type="text/css" href="TableCSSCode.css">
<title>EmployeeDetails</title>
</head>
<body>
<%-- <%! List<Employee> emp_list; %>
<%! EmployeeServiceImpl emp_service=new EmployeeServiceImpl(); %> --%>

<%-- <%
	emp_list=(List<Employee>)emp_service.getAllEmployee();
	request.setAttribute("list",emp_list);
	System.out.println("Employee List :"+emp_list);
%> --%>

<div id='cssmenu'>
<ul>
	<li><a href="service?action=create"><span>CreateUser</span></a></li>
	<li class='active'><a href=""><span>DisplayUser</span></a></li>
	<li><a href="service?action=modify"><span>ModifyUser</span></a></li>
	<li><a href="service?action=search"><span>SearchUser</span></a></li>
	<li><a href="service?action=delete"><span>DeleteUser</span></a></li>
	<li class='last'><a href="service?action=searchany"><span>SearchByAnyValue</span></a></li>
</ul>
</div>
<br>
<table class="CSSTableGenerator">
<tr>
	<th>KIN ID</th>
	<th>Name</th>
	<th>Email Id</th>
	<th>Contact</th>
	<th>City</th>
	<th>Date Of Joining</th>
	<th>Role</th>
	<th>Department</th>
	<th>Sub-Department</th>
</tr>
<c:forEach var="employees" items="${empl_list}">

<tr>
	<td><c:out value="${employees.kin}"/></td>
	<td><c:out value="${employees.name}"/></td>
	<td><c:out value="${employees.email}"/></td>
	<td><c:out value="${employees.contact}"/></td>
	<td><c:out value="${employees.city}"/></td>
	<td><c:out value="${employees.doj}"/></td>
	
	
 	<td><c:out value="${employees.role.r_name}"/></td>
 	
	<td><c:out value="${employees.dept.dept_name}"/></td>
	<td><c:out value="${employees.sub_dept.subDept.subdept_name}"/></td>
</tr>
</c:forEach>

</table>
</body>
</html>