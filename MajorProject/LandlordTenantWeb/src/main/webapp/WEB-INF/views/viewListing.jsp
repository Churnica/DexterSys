<%@page import="ltms.entity.ListingsEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - View Listings</title>
<link rel="stylesheet" href="<c:url value="/resources/style/style.css" />" >
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(function(){
    $("a").click(function(){
        $( "#dialog-message" ).dialog({
            modal: true,
            buttons: {
              Ok: function() {
                $( this ).dialog( "close" );
              }
            }
        });
    	$( "#dialog-message" ).show();
    });
 });
</script>
</head>
<body>
<jsp:include page="menu.jsp" />
<ul class="breadcrumb">
  <li><a href="landingPage">Home >> </a></li>
  <li>Verify Listing</li>
</ul>
	<div class="inner-viewlisting">
	<center><h2>Here are the listing available for you to rent!</h2></center>
<br />
<table class="listing-tablecell" width="100%">
<tr>
<th width="15%" align="left">Property</th>
<th width="20%" align="left">Location</th>
<th width="25%" align="left">Details</th>
<th width="20%" align="left">Landlord</th>
<th width="10%" align="left">Available From</th>
<th width="10%" align="left">Action</th>
</tr>
<%
List<ListingsEntity> listings = (List<ListingsEntity>)(request.getAttribute("listings"));
for(ListingsEntity listing: listings) { 
	String imageURL = "/resources/images" + listing.getImagePath();
	pageContext.setAttribute("imageURL", imageURL);
%>
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
<%= listing.getLandlordFirstname() + " " + listing.getLandlordLastname() %>
<br />
<%= "Phone: " + listing.getLandlordPhone() %>
<br />
<%= "Email: " + listing.getLandlordEmail() %>
<br />
</td>
<td>
<%= listing.getLeaseEndDateDisplayString() %>
</td>
<td>
<a id=<%=listing.getListingId() %>" href="#">View Rating</a>
</td>
</tr>
<% } %>
</table>
<div class="go-backdiv">
<a class="all-submit viewlist-button" href="landingPage">Go Back</a>
</div>
</div>
<div class="hidden" title="Rating" id="dialog-message">
<p>
<span style="float:left; margin:0 7px 50px 0;"></span>
    Property Rating
</p>
<p>
<table class="listing-tablecell" width="50%">
<tr>
<th width="50%" align="left">Review</th>
<th width="50%" align="left">Rating</th>
</tr>
<tr>
<td>Good</td>
<td>5</td>
</tr>
</table>
</div>
</body>
</html>