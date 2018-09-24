<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="ErrorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="Project.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		session.setAttribute("tweetid", request.getParameter("id"));
	%>
	<h3>Write the new Tweet</h3>
	<form action="EditTweetServlet">
		<div class="edittweetbox">
			<textarea name="EditTweetArea" rows="6" cols="80" id="textarea_tweet"></textarea>
		</div>
		<div class="button">
			<input type="submit" value="Edit">
		</div>
	</form>
</body>
</html>