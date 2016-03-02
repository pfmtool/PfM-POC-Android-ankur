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

<h1>Portfolio Detail:</h1>

<b>
		---------------------------------------------------------------------<br>

		<%
			ArrayList<Portfolio> resultListPort = (ArrayList<Portfolio>) request
					.getAttribute("result");
			if (resultListPort != null) {
				for (Portfolio portfolio : resultListPort) {
		%> Portfolio Name:<%=portfolio.portfolioName%>&nbsp; Cusip:<%=portfolio.cusip%>&nbsp;
		&nbsp;&nbsp; <a
		href="/deletePortfolio.do?portfolioName=<%=portfolio.portfolioName%>">delete</a> <br> <%
 	}
 	}
 %>
		---------------------------------------------------------------------<br>

		<form name="input1" action="/updatePortfolio.do" method="post">
                		<input type="submit" value="Show All Portfolios">
                	</form>


	<center> <b> <u> Add new portfolio  </u> </b> </center>
    	<br>
	<br>
	<br>
	<br>
	<center>
    	<form name="input1" action="/addPortfolio.do" method="post">
    		Portfolio Name: <input type="text" name="portfolioName">
    		Cusip: <input type="text" name="cusip">
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

<form name="User Page" action="UserPage" method="get">


                       <input type="button" value="Navigate to User page" name="CreateCourse"
                        onclick="openPage('query_result.jsp')"/>
                    </form>
                    <script type="text/javascript">
                     function openPage(pageURL)
                     {
                     window.location.href = pageURL;
                     }
                    </script
</body>
</html>