<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Employee</title>
<link rel="stylesheet" type="text/css" href="horizontalmenu.css">
<link rel="stylesheet" type="text/css" href="TextBoxCSSCode.css">
<link rel="stylesheet" type="text/css" href="ButtonCSSCode.css">
</head>
<body>

<div id='cssmenu'>
<ul>
	<li ><a href="service?action=create"><span>CreateUser</span></a></li>
	<li><a href="service?action=display"><span>DisplayUser</span></a></li>
	<li class='active'><a href=""><span>ModifyUser</span></a></li>
	<li><a href="service?action=search"><span>SearchUser</span></a></li>
	<li><a href=""><span>DeleteUser</span></a></li>
	<li class='last'><a href="service?action=searchany"><span>SearchByAnyValue</span></a></li>
</ul>
</div>

<div align="center">
<form id="form1" name="form1" method="post" action="service?action=AfterModify">
  <p>&nbsp;</p>
  <table width="500">
    <tr>
      <th align="left" scope="row">KIN ID</th>
      <td align="left"><label for="kin2"></label>
      <input type="text" name="kin" id="kin2" class="TextBox" /></td>
    </tr>
    <tr>
      <th align="left" scope="row">Name</th>
      <td align="left"><label for="name"></label>
      <input type="text" name="name" id="name" class="TextBox" /></td>
    </tr>
    <tr>
      <th align="left" scope="row">Contact</th>
      <td><label for="contact"></label>
      <input type="text" name="contact" id="contact" class="TextBox" /></td>
    </tr>
    <tr>
      <th align="left" scope="row">Email ID</th>
      <td><label for="email"></label>
      <input type="text" name="email" id="email" class="TextBox" /></td>
    </tr>
    <tr>
      <th align="left" scope="row">City</th>
      <td><label for="city"></label>
      <input type="text" name="city" id="city" class="TextBox"/></td>
    </tr>
    <tr>
      <th align="left" scope="row">Date Of Joining</th>
      <td><label for="doj"></label>
      <input type="text" name="doj" id="doj" class="TextBox"/></td>
    </tr>
    <tr>
      <th align="left" scope="row">Role</th>
      <td><label for="role"></label>
        <select name="role" id="role">
        <c:forEach var="role" items="${roles}">
        <option><c:out value="${role.r_name}"></c:out></option>
        </c:forEach>
      </select></td>
    </tr>
    <tr>
      <th align="left" scope="row">Department</th>
      <td><label for="dept"></label>
        <select name="dept" id="dept">
        <c:forEach var="department" items="${departments}">
        <option><c:out value="${department.dept_name}"/></option>
        </c:forEach>
      </select></td>
    </tr>
    <tr>
      <th align="left" scope="row">Sub-Department</th>
      <td><label for="subdept"></label>
        <select name="subdept" id="subdept">
        <c:forEach var="subdepartment" items="${subdepartments}">
        <option><c:out value="${subdepartment.subDept.subdept_name}"/></option>
        </c:forEach>
      </select></td>
    </tr>
  </table>
  <p>
    <input type="submit" name="update" id="update" value="Update" class="myButton" />
  </p>
</form>
</div>

</body>
</html>