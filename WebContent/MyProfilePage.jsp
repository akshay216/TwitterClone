<%@page import="finalproject.UserBean"%>
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
	<!-- Table displaying the profile details of the logged in user
                              fetching the details from MyProfileServlet -->
	<%
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
	%>
	<div class="pro">Profile Details</div>
	<table class="details">
		<tr>
			<th>UserId :</th>
		</tr>
		<tr>
			<th>Name :</th>
		</tr>
		<tr>
			<th>Email :</th>
		</tr>
	</table>
	<table class="mydetails" border="0">
		<tr>
			<%
				Iterator itr1;
				List dataprofile = user.getDataList1();
				for (itr1 = dataprofile.iterator(); itr1.hasNext();) {
			%>

			<td><%=itr1.next()%></td>
		</tr>
		<%
			}
		%>
	</table>
	</div>
	<!-- Table displaying the TWEETS of the logged in user
                              fetching the details from MyProfileServlet -->
	<center>
		<b>YOUR TWEETS</b>
	</center>
	<table class="mytweets" border="3">
		<tr>
			<%
				Iterator itr;
			%>
			<%
				List datatweet = user.getDataList();

				for (itr = datatweet.iterator(); itr.hasNext();) {
			%>
			<td><%=itr.next()%></td>
			<td><%=itr.next()%></td>
			<%
				String s = (String) itr.next();
			%>
			<td><a href="EditTweet.jsp?id=<%=s%>">Edit</a></td>
			<td><a href="DeleteTweet.jsp?id=<%=s%>">Delete</a></td>
		</tr>
		<%
			}
		%>
	</table>
	</div>
	<!-- Division displaying the no. of followers and no. of tweets of the logged in user
	fetching it from the MyProfileServlet -->


	<div class="right">
		<a href="LoginFetchTweetServlet">Home</a> <br> No. of Tweets:
		<%=user.getTweetcount()%>
		<br> No. of Followers:
		<%=user.getFollowcount()%>
	</div>
	<div class="delete">
		<a href="DeleteAccount.jsp?id=<%=user.getUserid()%>">Click here to
			delete your account</a>
	</div>
</body>
</html>