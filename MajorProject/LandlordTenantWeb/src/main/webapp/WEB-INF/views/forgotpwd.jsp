<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Forgot Password</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.js"></script>
<script>
	$.validator.setDefaults({
		submitHandler: function(form) {
			form.submit();
		}
	});
	
	$().ready(function() {
		$("#frmForgotPwd").validate({
			rules: {
				field: {
					required: true,
				},
				inputChoice: {
					required: true,
				},
			},
			messages: {
				field: {
					required: "Please enter a User Name / Email"
				},
				inputChoice: {
					required: "Please enter a User Name / Email"
				},
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<ul class="breadcrumb">
  		<li><a href="login">Home >> </a></li>
  		<li>Forgot Password</li>
	</ul>
	</div>
	
	<form id="frmForgotPwd" action="forgotPwdSubmit" method="post">
	<div class="inner-regform">
		<p>Your new password will be sent to your registered Email Id!</p>
		<br />
		<br />
		<br />
		<label id="lblUsername" class="all-lable frmregister-lbl" for="username">User Name:</label>
		<label class="all-lable frmregister-lbl"><%= request.getAttribute("userId")%></label>
		<input type="hidden" name="username" id="username" value="<%= request.getAttribute("userId")%>">
		<br />
		<br />
		<br />
		<%-- 
		<label id="lblEmail" class="all-lable frmregister-lbl" for="email">Email:</label>
		<input class="all-textbox frmregister-txt" type="text" id="email" name="email" value="">
		<br />
		<br />
		<br />
		--%>
		<input class="all-submit button-form frmregister-submit" type="submit" id="btnSubmit" name="btnSubmit" value="Send new password">
		<a class="all-submit viewlist-button" href="login">Back</a>
		<br />
		<br />
		<br />
	</div>
	</form>
</body>
</html>