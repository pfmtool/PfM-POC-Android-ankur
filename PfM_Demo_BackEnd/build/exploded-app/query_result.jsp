<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.PfM_Demo_Backend.data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query Result</title>
</head>
<body>
<center>
<body bgcolor="#F5F5F5">
<div align="center" style="max-width:880px; border:2px solid #ccc;">
<div style="color:#2B3856">
<H1>
<center> <b> <u> Add new user  </u> </b> </center></H1>
</div>
	<b>


	<br>
	<center>
	<form  name="input" action="/addUser.do" method="post">

	<table border="1" width="30%" cellpadding="3">
    			<tbody>
    				<tr>
    					<td>User Name: </td>
    					<td><input type="text" name="uName"> <br>
    				</tr>
    				<tr>
    					<td> Password:</td>
    					<td> <input type="text" name="pass"> </td>
    				</tr>
		 </tbody>
		 </table>

		 <input type="submit" value="Add" style="font-face: 'Comic Sans MS'; font-size: larger; color: teal; background-color: #A9A9A9; border: 3pt ridge lightgrey">



	</form>

	<br>

	</center>



<br>
<br>
<br>
<br>
<br>
<br>
<br>

<div style="color:#98AFC7">
---------------------------------------------------------------------<br>
		<%
			ArrayList<User> resultList = (ArrayList<User>) request.getAttribute("result");
			if (resultList != null) {
				for (User user : resultList) {
		%>
		User Name:<%=user.mName%>&nbsp;
		Password:<%=user.mPassword%>&nbsp;
		&nbsp;&nbsp;
		<a	href="/deleteUser.do?name=<%=user.mName%>">delete</a> <br> <%
 	}
 	}
 %>
 	---------------------------------------------------------------------<br>
 </div>


		<form name="input" action="/updateUser.do" method="post">

        		<input type="submit" value="Show All User" style="font-face: 'Comic Sans MS'; font-size: larger; color: teal; background-color: #A9A9A9; border: 3pt ridge lightgrey" >
        	</form>
<br>
<br>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<form name="PortfolioPage" action="PortfolioPage" method="get">
   <input type="button" value="Navigate to Portfolio Page" name="CreateCourse"
    onclick="openPage('query_result_portfolio.jsp')"/>
</form>
<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
</script
</div>
</center>
</body>
</html>