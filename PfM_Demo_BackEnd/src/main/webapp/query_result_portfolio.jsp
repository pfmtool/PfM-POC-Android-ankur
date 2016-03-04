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
<H1><center> <b> <u> Add new portfolio  </u> </b> </center></H1>
</div>
<b>



    	<br>
	<br>
	<div style="color:#151B54">
	<center>
    	<form name="input1" action="/addPortfolio.do" method="post">

        <table border="1" width="30%" cellpadding="3">
			<tbody>
				<tr>
					<td>Portfolio Name: </td>
					<td><input type="text" name="portfolioName"> <br>
				</tr>
				<tr>
					<td> Code:</td>
					<td> <input type="text" name="code"> </td>
				</tr>
				<tr>
					<td> Cusip: </td>
					<td> <input type="text" name="cusip"> </td>
				</tr>
				<tr>
					<td> Open date: </td>
					<td>
					 	<select name="opendate">
                                                     <option value="T">T</option>
                                                     <option value="T-1">T-1</option>
                                                     <option value="T-2">T-2</option>
                                                     <option value="T-3">T-2</option>
                                                     <option value="T-4">T-4</option>
                                                     <option value="T-5">T-5</option>
                                                     <option value="T-6">T-6</option>
                                                     <option value="T-7">T-7</option>
                                                     <option value="T-8">T-8</option>
                                                     <option value="T-9">T-9</option>
                        </select>
					 </td>
				</tr>
				<tr>
					<td> Expiry date: </td>
					<td>
						 <select name="expdate">
                             <option value="T">T</option>
                             <option value="T+1">T+1</option>
                             <option value="T+2">T+2</option>
                             <option value="T+3">T+3</option>
                             <option value="T+4">T+4</option>
                             <option value="T+5">T+5</option>
                             <option value="T+6">T+6</option>
                             <option value="T+7">T+7</option>
                             <option value="T+8">T+8</option>
                             <option value="T+9">T+9</option>
                           </select>
					 </td>
				</tr>
				<tr>
					<td> Portfolio Desc: </td>
					<td> <input type="text" name="desc"> </td>
				</tr>

   			</tbody>
   		</table>
   			<input type="submit" value="Add" style="font-face: 'Comic Sans MS'; font-size: larger; color: teal; background-color: #A9A9A9; border: 3pt ridge lightgrey" >

    	</form>
    	</center>
    </div>

<br>
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
			ArrayList<Portfolio> resultListPort = (ArrayList<Portfolio>) request.getAttribute("result");
			if (resultListPort != null) {
				for (Portfolio portfolio : resultListPort) {
		%>
		Portfolio Name: <%=portfolio.portfolioName%>&nbsp,
		Cusip: <%=portfolio.cusip%>&nbsp,
		Code: <%=portfolio.code%>&nbsp,
		Open Date: <%=portfolio.opendate%>&nbsp,
		Exp Date: <%=portfolio.expdate%>&nbsp,
		Desc: <%=portfolio.cusip%>&nbsp;
		&nbsp;&nbsp; <a
		href="/deletePortfolio.do?portfolioName=<%=portfolio.portfolioName%>">delete</a> <br> <%
 	}
 	}
 %>
 ---------------------------------------------------------------------<br>
 </div>
 		<form name="input1" action="/updatePortfolio.do" method="post">

                 		<input type="submit" value="Show All Portfolios" style="font-face: 'Comic Sans MS'; font-size: larger; color: teal; background-color: #A9A9A9; border: 3pt ridge lightgrey" >
        </form>


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

 </div>
 </center>
</body>
</html>