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
<body bgcolor="#F5F5F5">
<h1>User Detail:</h1>
	<b>
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

		<form name="input" action="/updateUser.do" method="post">
        		<input type="submit" value="Show All User">
        	</form>
	</b>
	<center> <b> <u> Add new user  </u> </b> </center>
	<br>
	<br>
	<br>
	<br>
	<center>
	<form  name="input" action="/addUser.do" method="post">
		User Name: <input type="text" name="uName">
		Password: <input type="text" name="pass">
		<input type="submit" value="Add">

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

</body>
</html>