<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="finalproject.UserBean"%>
<%@page import="account.DeleteAccount"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*;" errorPage="ErrorPage.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Project.css" />
<TITLE>Profile</TITLE>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
	function clickUpdate(button) {
		if ($("#textarea_tweet").val() != '') {
			$.ajax({
				url : "TweetServ",
				type : 'POST',
				data : "message=" + $("#textarea_tweet").val(),
				success : function(msg) {

					$(".loadtweet").html(msg);
				}

			});
		} else {
			alert("Please write something");
		}

	}
</script>
<script type="text/javascript">
function LimtCharacters(txtMsg, CharLength, indicator) {
	chars = txtMsg.value.length;
	document.getElementById(indicator).innerHTML = CharLength - chars;
	if (chars > CharLength) {
	txtMsg.value = txtMsg.value.substring(0, CharLength);
	}
	}
</script>
<script type="text/javascript">

	function lookup(inputString) {
		if (inputString.length == 0) {
			$('#suggestions').hide();
		} else {
			$.post("AutoSearchServlet", {
				queryString : "" + inputString + ""
			}, function(data) {
				if (data.length > 0) {
					$('#suggestions').show();
					$('#autoSuggestionsList').html(data);
				}
				$("#autoSuggestionsList ul li").click(function() {

					var value = $(this).html();
					$("#inputString").val(value);
					$('#suggestions').hide();
					$("#inputString").remove();
				});

			});
		}
	}

	function fill(thisValue) {
		$('#inputString').val(thisValue);
		setTimeout("$('#suggestions').hide();", 200);
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tweetButton').click(function() {
			$.ajax({
				type : "post",
				url : "test", //this is my servlet
				data : {
					input : $('#tweetbox').val(),
				//  output: $('#op').val()
				},
				success : function(msg) {
					$('#textarea').append(msg); //textarea
				}
			});
		});

	});
</script>

<style type="text/css">
.suggestionsbox {
	position: relative;
	width: 10%;
	border-radius: 4px;
	left: 5%;
	top: 10%;
	float: left;
}

ul {
	position: absolute;
	background-color: white;
	list-style-type: none;
	margin: 0;
	padding: 1%;
	border-color: black;
	text-align: left;
	top: 45%;
	left: 1%;
}

ul li {
	
}

ul li a {
	display: block;
}
</style>

</HEAD>
<BODY>

	<%
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
		String userId = user.getUserid();
	%>
	<center>
		<div class="heading">My Twitter Clone</div>
		<div class="w">
			Welcome
			<%=user.getFullName()%>
			&nbsp;!&nbsp;&nbsp;<a href="LoginFetchTweetServlet">Home|</a>&nbsp;&nbsp;<a
				href="MyProfileServlet?id=<%=userId%>">Profile|</a>&nbsp;&nbsp;<a
				href="EditProfile.jsp?id=<%=userId%>">Edit Profile|</a>&nbsp;&nbsp;<a
				href="SignOutServlet">SignOut</a>
		</div>
		<hr>
		<div class="h">What are you doing?</div>

		<div class="followtext">
			<b>Follow</b>
		</div>
<div class="numberleft">
			     <div class="numofchar">Number of Characters Left: 
                <label id="lblcount" style="background-color:#E2EEF1;color:Red;font-weight:bold;">140</label><br/></div></div>
		<div class="text">
			<textarea name="TweetArea" rows="6" cols="60" id="textarea_tweet" placeholder="Compose a new tweet...." onkeyup="LimtCharacters(this,140,'lblcount');"></textarea>
		</div>

		<div class="u">
			<input type="text" size="30" value="" id="inputString"
				onkeyup="lookup(this.value);" onblur="fill();" />
		</div>
		<div class="suggestionsBox" id="suggestions" style="display: none;">
			<div class="suggestionList" id="autoSuggestionsList"></div>
		</div>

		<div class="update">
			<input type="button" id="updateButton" onclick="clickUpdate(this)"
				onload="Validate(this)" value="Update">
		</div>
		<hr>
		<center>
			<b>TIMELINE</b>
		</center>
		<div class="loadtweet">
			<table border="0" width="80%">
				<tr>
					<th width="25%">FROM</th>
					<th width=50%>TWEET</th>
					<th width="25%">TIME</th>
				</tr>
				<%
					Iterator itr1;
					List dataprofile = user.getDataList();
					for (itr1 = dataprofile.iterator(); itr1.hasNext();) {
				%>
				<tr>
					<%
						for (int i = 0; i < 3; i++) {
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

		</div>
	</center>
</BODY>

</HTML>