<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Login</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.js"></script>
<script>
	$.validator.setDefaults({
		submitHandler: function(form) {
			form.submit();
		}
	});
	
	$().ready(function() {
		// validate signup form on keyup and submit
		$("#frmLogin").validate({
			rules: {
				txtUsername: {
					required: true,
					minlength: 4
				},
				txtPassword: {
					required: true,
					minlength: 4
		}
			},
			messages: {
				txtUsername: {
					required: "Please enter a username",
					minlength: "Your username must consist of at least 4 characters"
				},
				txtPassword: {
					required: "Please enter a password",
					minlength: "Your password must consist of at least 4 characters"
				}
			}
		});
	});
	
	function submitForgotPassword() {
		var frmLogin = document.forms[0];
		frmLogin.action = "forgotpwd";
		frmLogin.method = "post";
		frmLogin.submit();
	}
	</script>
</head>
<body>
	<jsp:include page="menu.jsp" />
    <form class="login-form" id="frmLogin" action="login" method="post">
	   	<table class="login-table" border="0" cellpadding="0" cellspacing="0">
	<% 
		if(null!=request.getAttribute("errorMessage"))
		{
	%>
			<tr valign="top">
				<td><font color="red"><%=request.getAttribute("errorMessage")%></font><br /></td>
			</tr>
	<%
		}
	%>
			<tr valign="top">
				<td>
					<div class="inner-logintable">
						<label for="username">User Name:</label>
						<input class="text-form login-username" type="text" id="txtUsername" name="txtUsername" value="">
						<br />
						<label for="password">Password:</label>
						<input class="text-form login-password" type="password" id="txtPassword" name="txtPassword" value="">
						<br />
						<input class="button-form" type="submit" id="btnSubmit" name="btnSubmit" value="Login">
						<a href="register">New User &#63;</a>
						<a href="#" onclick="javascript:submitForgotPassword();">Forgot Password &#63;</a>
					</div>
				</td>
			</tr>
		</table>
    </form>
</body>
</html>
