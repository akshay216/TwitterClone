<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="ErrorPage.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>TableLess Form</TITLE>
<META name="Author" Content="CSC Induction Batch">
<META name="Keywords" Content="form, divs">
<META name="Description" Content="A CSS Tableless Form Design">
<link rel="stylesheet" type="text/css" href="SignUp.css" />
<script type="text/javascript">
	function validate(form) {
		var errors = [];
		var email = form.email.value;
		var password = form.password.value;
		var confirm_password = form.confirm_password.value;
		var fullname = form.fname.value;
		if (fullname.length === 0) {
			errors[errors.length] = "You must enter your name";
		}
		if (email.length === 0) {
			errors[errors.length] = "You must enter a email";
		} else {
			var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
			if (!(email.match(emailExp))) {
				errors[errors.length] = "invalid email type";
			}
		}
		if (password.length === 0) {
			errors[errors.length] = "You must enter a password";
		}
		if (confirm_password.length === 0) {
			errors[errors.length] = "You must confirm your password";
		} else if (!(confirm_password == password)) {
			errors[errors.length] = "password not matched";
		}
		if (!checkCheckBox(form.i_agree)) {
			errors[errors.length] = "You must agree to the terms.";
		}
		function checkCheckBox(cb) {
			return cb.checked;
		}

		if (errors.length > 0) {
			reportErrors(errors);
			return false;
		}

		function reportErrors(errors) {
			var msg = "There were some problems...\n";
			var numError;
			for (var i = 0; i < errors.length; i++) {
				numError = i + 1;
				msg += "\n" + numError + ". " + errors[i];
			}
			alert(msg);
		}

	}
</script>
</HEAD>
<BODY>
	<center>
		<h1>
			<b>WELCOME TO TWITTER CLONE</b>
		</h1>
	</center>
	<center>
		<div class="m">
			<form name="register" method="post" action="EditProfileServlet"
				onsubmit="return validate(this);">
				<div class="edit">
					<b>Edit Profile</b>
				</div>
				<div class="editfname">Enter your new Fullname :</div>
				<div class="editfnamebox">
					<INPUT type="text" name="fname">
				</div>
				<div class="editemail">New E-Mail :</div>
				<div class="editemailbox">
					<INPUT type="text" name="email">
				</div>
				<div class="pasw">New Password :</div>
				<div class="paswbox">
					<INPUT type="password" name="password">
				</div>
				<div class="cpass">Confirm password :</div>
				<div class="cpassbox">
					<INPUT type="password" name="confirm_password">
				</div>
				<div class="ctext">Comments (optional) :</div>
				<div class="ctextbox">
					<TEXTAREA NAME="message" ROWS="5" COLS="25"></TEXTAREA>
				</div>
				<div class="agree">
					&nbsp;<INPUT type="checkbox" name="i_agree" value="1"> I
					agree to the terms & conditions
				</div>
				<div class="sub">
					&nbsp;<INPUT class="button" type="submit" name="submit"
						value="SUBMIT">
				</div>
			</form>
		</div>
	</center>
</BODY>
</HTML>