<%@page import="finalproject.UserBean"%>
<%@page import="follow.FollowCheck"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*;" errorPage="ErrorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Project.css" />
<title>Insert title here</title>
</head>
<body>

	<%
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
	%>
	<h1 align="center">
		Welcome to
		<%=user.getFollowId()%>
		profile
	</h1>
	<p align="right">
		<a class="hyper" href="LoginFetchTweetServlet">Home Page</a>
	</p>
	<table class="profiledetails" border="3">
		<tr>
			<%
				Iterator itr;
			%>
			<%
				List data = user.getDataList();
				for (itr = data.iterator(); itr.hasNext();) {
			%>

			<td><%=itr.next()%></td>
		</tr>
		<%
			}
		%>
	</table>


	<table border="3">
		<tr>
			<td bgcolor="yellow" class="font">TWEETS</td>
			<td bgcolor="yellow" class="font">TIMESTAMP</td>
		</tr>
		<tr>
			<%
				Iterator itr1;
			%>
			<%
				List data1 = user.getDataList1();
				for (itr1 = data1.iterator(); itr1.hasNext();) {
			%>
			<%
				for (int i = 0; i < 2; i++) {
			%>
			<td><%=itr1.next()%></td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>
	<%
		int a = user.getIsfollowing();
		if (a == 1) {
	%>
	<form action="ForUnFollow">
		<input class="profiledetails" type="submit" value="Unfollow">
	</form>
	<%
		}
	%>
	<%
		if (a == 0) {
	%>
	<form action="ForFollow">
		<input class="profiledetails" type="submit" value="Follow">
	</form>
	<%
		}
	%>
	<br>
	<div class="followcount">
		Followers :
		<%=user.getFollowcount()%>
	</div>

</body>
</html>