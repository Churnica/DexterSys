<%@ page import="ltms.entity.UsersEntity"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% UsersEntity loggedUser =(UsersEntity)session.getAttribute("loggedUser"); %>
<link rel="stylesheet" href="<c:url value="/resources/style/style.css" />">
<% if(loggedUser != null && loggedUser.getRole() == null) { %>
		<center>
			<h3>Welcome <%= loggedUser.getUserId() %>[<%= loggedUser.getRole() %>]</h3>
		</center>
<% } %>
		<header class="header-wraper">
		  <div class="header-main">
		  	   <div class="logo-main">
					<img alt="header logo" src="<c:url value="/resources/images/header-logo.png" />">
					<p>Conveyance.com</p>				
				</div>
				<div class="menu-main">
					<ul class="menuright" >
<% if(loggedUser == null || loggedUser.getRole() == null) { %>
						
						<li><a href="login">Home</a></li>
						<li><a href="register">Register</a></li>						
						<li><a href="login">Login</a></li>
						<li class="menuright" ><a  href="contact">Contact Us</a></li>
						<li class="menuright" ><a href="about">About Us</a></li>						
						
						
<% } else if("LANDLORD".equalsIgnoreCase(loggedUser.getRole())) { %>
						<li><a href="landingPage"><%=loggedUser.getUserId()%>'s Dashboard</a></li>
						<li><a href="listing">Add Listing</a></li>
						<li><a href="viewMyListing">View My Listing</a></li>
						<li><a href="viewMyLease">View My Lease</a></li>
						<li><a href="viewMaintenanceRequest">View Maintenance Request</a></li>
						<li><a href="viewListing">View Listing</a></li>
						<li><a href="logoff">Sign out</a></li>
<% } else if("TENANT".equalsIgnoreCase(loggedUser.getRole())) { %>
						<li><a href="landingPage"><%=loggedUser.getUserId()%>'s Dashboard</a></li>
						<li><a href="viewListing">View Listing</a>
						<li><a href="viewMyLease">View My Lease</a></li>
						<li><a href="maintenance">Send Maintenance Request</a>
						<li><a href="rating">Rate Listing</a></li>
						<li><a href="logoff">Sign out</a>
<% } else if("ADMIN".equalsIgnoreCase(loggedUser.getRole())) { %>
						<li><a href="landingPage"><%=loggedUser.getUserId()%>'s Dashboard</a></li>
						<li><a href="verifyUser">Verify User</a></li>
						<li><a href="verifyListing">Verify Listing</a></li>
						<li><a href="logoff">Sign out</a></li>
<% }%>
					</ul>
				</div>
			</div>
		</header>