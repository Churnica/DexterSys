<%@page import="ltms.entity.UsersEntity"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% UsersEntity loggedUser =(UsersEntity)session.getAttribute("loggedUser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Home</title>
</head>
<body>
		<jsp:include page="menu.jsp" />
		
		<center>
		<h2>
		<% if(loggedUser != null && loggedUser.getRole() == null) { %>
			Welcome <%= loggedUser.getUserId() %>[<%= loggedUser.getRole() %>]
<% } %>
			<% if("LANDLORD".equalsIgnoreCase(loggedUser.getRole())) { %>
				<a href="listing">Add Listing</a>
				<br />
				<a href="viewMyListing">View My Listing</a>
				<br />
				<a href="viewMyLease">View My Lease</a>
				<br />
				<a href="viewMaintenanceRequest">View Maintenance Request</a>
				<br />
				<a href="viewListing">View Listing</a>
				<br />
				<a href="logoff">Sign out</a>
			<% } else if("TENANT".equalsIgnoreCase(loggedUser.getRole())) { %>
				<a href="viewListing">View Listing</a>
				<br />
				<a href="maintenance">Send Maintenance Request</a>
				<br />
				<a href="viewMyLease">View My Lease</a>
				<br />
				<a href="rating">Rate Listing</a>
				<br />
				<a href="logoff">Sign out</a>
			<% } else if("ADMIN".equalsIgnoreCase(loggedUser.getRole())) { %>
				<a href="verifyUser">Verify User</a>
				<br />
				<a href="verifyListing">Verify Listing</a>
				<br />
				<a href="logoff">Sign out</a>
			<% } %>
		</h2>
	</center>
</body>
</html>