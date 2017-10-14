<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Register</title>
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
		$("#frmRegister").validate({
			rules: {
				userId: {
					required: true,
				},
				password: {
					required: true,
					minlength: 6
				},
				firstname: {
					required: true,
				},
			},
			messages: {
				userId: {
					required: "Please enter a User Id"
				},
				password: {
					required: "Please enter a Password",
					minlength: "Your Password must consist of at least 6 characters"
				},
				firstname: {
					required: "Please enter First Name"
				},
			}
		});
	});
$(document).ready(function(){
    $("#userId").change(function(){
        var URL = 'loginExists/' + $("#userId").val();
        $.ajax({
        	   url: URL,
        	   success: function(data, status) {
        		   $("label[for='userIdError']").text(data);
        	   },
        	   type: 'GET'
        	});
    });
});
</script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<ul class="breadcrumb">
  		<li><a href="login">Home >> </a></li>
  		<li>Register</li>
	</ul>
	</div>
	<form id="frmRegister" action="registerSubmit" method="post">
	<div class="inner-regform">
		<label font-color="red" class="all-lable frmregister-lbl" for="userIdError"></label>
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="userId">User Id : </label>
		<input class="all-textbox frmregister-txt" type="text" id="userId" name="userId" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="password">Password : </label>
		<input class="all-textbox frmregister-txt" type="password" id="password" name="password" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="firstname">First Name : </label>
		<input class="all-textbox frmregister-txt" type="text" id="firstname" name="firstname" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="lastname">Last Name : </label>
		<input class="all-textbox frmregister-txt" type="text" id="lastname" name="lastname" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="phone">Phone number : </label>
		<input class="all-textbox frmregister-txt" type="text" id="phone" name="phone" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="email">Email : </label>
		<input class="all-textbox frmregister-txt" type="text" id="email" name="email" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="address">Address : </label>
		<input class="all-textbox frmregister-txt" type="text" id="address" name="address" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="role">Role : </label>
		<select class="all-combobox frmregister-cmb" name="role" id="role">
  			<option value="LANDLORD">Landlord</option>
  			<option value="TENANT">Tenant</option>
		</select>
		<br />
		<br />
		<input class="all-submit button-form frmregister-submit" type="submit" id="btnSubmit" name="btnSubmit" value="Register">
		<a class="all-submit viewlist-button" href="landingPage">Back</a>
		<br />
		<br />
	</div>
	</form>
</body>
</html>