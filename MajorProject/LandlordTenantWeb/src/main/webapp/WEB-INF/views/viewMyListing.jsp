<%@page import="ltms.entity.ListingsEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - View My Listings</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<ul class="breadcrumb">
  <li><a href="landingPage">Home >> </a></li>
  <li>View My Listing</li>
</ul>
<h2>Here are your listings to view/manage!</h2>
<br />
<table class="listing-tablecell" width="100%">
<tr>
<th width="25%" align="left">Property</th>
<th width="20%" align="left">Location</th>
<th width="30%" align="left">Details</th>
<th width="25%" align="left">Action</th>
</tr>
<%
List<ListingsEntity> listings = (List<ListingsEntity>)(request.getAttribute("myListings"));
for(ListingsEntity listing: listings) {
	String imageURL = "/resources/images" + listing.getImagePath();
	pageContext.setAttribute("imageURL", imageURL); %>
<tr>
<td><img height="100" width="100" alt="listing<%= listing.getListingId() %>" src="<c:url value="${imageURL}" />" onclick="javascript:window.open('<c:url value="${imageURL}" />');"></td>
<td>
<%= listing.getAddressLine1() + " " + listing.getAddressLine2() %>
<br />
<%= listing.getCity() + " " + listing.getState() + " " + listing.getZipCode() %>
<br />
</td>
<td>
<%= listing.getZoning() + " / $" + listing.getRent() + " per month" %>
<br />
<%= listing.getDescription() %>
<br />
</td>
<td>
<a href="lease?listingId=<%= listing.getListingId() %>">Enter Lease Details</a>
<br />
<a href="deleteListing?listingId=<%= listing.getListingId() %>">Delete Listing</a>
<br />
</td>
</tr>
<% } %>
</table>
<br />
<br />
<a class="all-submit viewlist-button" style="float:none; overflow:auto; display:block; margin:5px auto; "href="landingPage">Back</a>
</body>
</html>