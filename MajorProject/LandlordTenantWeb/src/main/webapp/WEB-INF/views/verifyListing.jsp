<%@page import="ltms.entity.ListingsEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Verify Listings</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("a").click(function(){
        $("#hdnListingIdnStatus").val($(this).attr('id'));
        $( "#frmVerifyListing" ).submit();
    });
});
</script>
</head>
<body>
<jsp:include page="menu.jsp" />
<h2>Here are your listings to verify!</h2>
<br />
<h4><%= request.getAttribute("successMessage") %></h4>
<br />
<form id="frmVerifyListing" action="verifyListingSubmit" method="post">
<table width="100%">
<tr>
<th width="20%" align="left">Property</th>
<th width="20%" align="left">Location</th>
<th width="25%" align="left">Details</th>
<th width="20%" align="left">Reason</th>
<th width="15%" align="left">Action</th>
</tr>
<%
List<ListingsEntity> listings = (List<ListingsEntity>)(request.getAttribute("listingsForVerification"));
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
<td><textarea rows='3' cols='25' id="reason" name="reason"></textarea></td>
<td><a id="ACTIVE:<%= listing.getListingId() %>" href="#">Approve</a> / 
<a id="REJECT:<%= listing.getListingId() %>" href="#">Reject</a>
<br /></td>
</tr>
<% } %>
</table>
<input type="hidden" id="hdnListingIdnStatus" name="hdnListingIdnStatus" value="">
<a href="landingPage">Back</a>
</form>
</body>
</html>