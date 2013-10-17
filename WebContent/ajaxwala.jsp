<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AJAX Example</title>
<script>
		
	var xmlHttp;
	xmlhttp=new XMLHttpRequest();
	
	function loadSubDept(){

	dpt=document.getElementById("dept").value;
	url="ajaxserv?dept"+dpt;
	xmlhttp.onreadystatechange=function()
  {
  	if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("subdept1").innerHTML=xmlhttp.responseText;
    }
  }
	xmlHtttp.open("GET",url,true);
	xmlHttp.send();		
	}
	
</script>
</head>

<body>
<p>
  <label for="dept"></label>
  <label for="dept2">Department</label>
  <select name="dept" id="dept" onchange="loadSubDept()">
  <option>Development</option>
  <option>Human Resources</option>
  <option>Accounts</option>
  <option>Sales</option>
  </select>
</p>
<div id="subdept1">
<p>
  <label for="subdept"></label>
  <label for="subdept">SubDepartment</label>
  <select name="subdept" id="subdept">
  <option value='-1'></option></select>
</p>
</div>

</body>
</html>