<%@page import="ltms.entity.UsersEntity"%>
<%@page import="ltms.entity.ListingsEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Verify Users</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("a").click(function(){
        $("#hdnUserIdnStatus").val($(this).attr('id'));
        $( "#frmVerifyUser" ).submit();
    });
});
</script>
</head>
<body>
<jsp:include page="menu.jsp" />
<ul class="breadcrumb">
  <li><a href="landingPage">Home >> </a></li>
  <li>Verify User</li>
</ul>
<h2>Here are your users to verify!</h2>
<br />
<h4><%= request.getAttribute("successMessage") %></h4>
<br />
<form id="frmVerifyUser" action="verifyUserSubmit" method="post">
<table width="100%">
<tr>
<th width="15%" align="left">User Id / Role</th>
<th width="15%" align="left">Name</th>
<th width="15%" align="left">Address</th>
<th width="15%" align="left">Email</th>
<th width="10%" align="left">Phone</th>
<th width="15%" align="left">Reason</th>
<th width="15%" align="left">Select</th>
</tr>
<%
List<UsersEntity> users = (List<UsersEntity>)(request.getAttribute("usersForVerification"));
for(UsersEntity user: users) { %>
<tr>
<td><%= user.getUserId() %> / <%= user.getRole() %><br /></td>
<td><%= user.getFirstName() + " " + user.getLastName() %><br /></td>
<td><%= user.getAddress() %><br /></td>
<td><%= user.getEmail() %><br /></td>
<td><%= user.getPhone() %><br /></td>
<td><textarea rows='3' cols='25' id="reason" name="reason"></textarea></td>
<td><a id="ACTIVE:<%= user.getUserId() %>" href="#">Approve</a> / 
<a id="REJECTED:<%= user.getUserId() %>" href="#">Reject</a>
<br /></td>
</tr>
<% } %>
</table>
<input type="hidden" id="hdnUserIdnStatus" name="hdnUserIdnStatus" value="">
<a href="landingPage">Back</a>
</form>
</body>
</html>