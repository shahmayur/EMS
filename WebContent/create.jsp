
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="horizontalmenu.css">
<link rel="stylesheet" type="text/css" href="TextBoxCSSCode.css">
<link rel="stylesheet" type="text/css" href="ButtonCSSCode.css">
<title>CreateEmployee</title>
</head>

<body>
<sql:setDataSource var="ems" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/emsphase2"
     user="root"  password="password"/>

<div id='cssmenu'>
<ul>
	<li class='active'><a href=""><span>CreateUser</span></a></li>
	<li><a href="service?action=display"><span>DisplayUser</span></a></li>
	<li><a href="service?action=modify"><span>ModifyUser</span></a></li>
	<li><a href="service?action=search"><span>SearchUser</span></a></li>
	<li><a href="service?action=delete"><span>DeleteUser</span></a></li>
	<li class='last'><a href="service?action=searchany"><span>SearchByAnyValue</span></a></li>
</ul>
</div>

<div align="center">
<form name="form1" method="post" action="service?action=AfterCreate">
  <table width="500">
    <tr>
      <th align="left" scope="row">KIN Id</th>
      <td><label for="kinid"></label>
      <input type="text" name="kinid" id="kinid" class="TextBox"></td>
    </tr>
    <tr>
      <th align="left" scope="row">Name</th>
      <td><label for="name"></label>
      <input type="text" name="name" id="name" class="TextBox"></td>
    </tr>
    <tr>
      <th align="left" scope="row">Email ID</th>
      <td><label for="emailid"></label>
      <input type="text" name="emailid" id="emailid" class="TextBox"></td>
    </tr>
    <tr>
      <th align="left" scope="row">Contact</th>
      <td><label for="contact"></label>
      <input type="text" name="contact" id="contact" class="TextBox"></td>
    </tr>
    <tr>
      <th align="left" scope="row">City</th>
      <td><label for="city"></label>
      <input type="text" name="city" id="city" class="TextBox"></td>
    </tr>
    <tr>
      <th align="left" scope="row">Date Of Joining</th>
      <td><label for="doj"></label>
      <input type="text" name="doj" id="doj" class="TextBox"></td>
    </tr>
    <tr>
      <th align="left" scope="row">Role</th>
      <td><label for="role"></label>
        <select name="role" id="role">
        <sql:query dataSource="${ems}" var="result">
			SELECT * from role;
		</sql:query>
        
        <c:forEach var="row" items="${result.rows}">
          <option><c:out value="${row.Role_name}"/></option>
         </c:forEach> 
      </select></td>
    </tr>
    <tr>
      <th align="left" scope="row">Department</th>
      <td><label for="department"></label>
        <select name="department" id="department">
        <sql:query dataSource="${ems}" var="result">
			SELECT * from department;
		</sql:query>
		        <c:forEach var="row" items="${result.rows}">
          <option><c:out value="${row.Department_name}"/></option>
         </c:forEach> 
      </select></td>
    </tr>
    <tr>
      <th align="left" scope="row">Sub-Department</th>
      <td><label for="subdept"></label>
        <select name="subdept" id="subdept">
            <sql:query dataSource="${ems}" var="result">
				SELECT * from subdepartment;
			</sql:query>
			<c:forEach var="row" items="${result.rows}">
          <option><c:out value="${row.SubDepartment_name}"/></option>
          	</c:forEach>
      </select></td>
    </tr>
  </table>
  <p>
    <input type="submit" name="create" id="create" value="Create" class="myButton">
  </p>
</form>

</div>
</body>
</html>
