<%@page import="ltms.entity.ListingsEntity"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Enter Lease Details</title>
<link rel="stylesheet" href="<c:url value="/resources/style/style.css" />" >
</head>
<body>
<jsp:include page="menu.jsp" />
<ul class="breadcrumb">
  <li><a href="landingPage">Home >> </a></li>
  <li><a href="viewMyListing">Add Listing >> </li>
  <li>Lease</li>
</ul>
<form class="form-listing" id="frmLease" action="leaseSubmit" method="post">
<%
ListingsEntity listing = (ListingsEntity)(request.getAttribute("selectedListing"));
%>
	<div class="inner-list">
	<!-- photo goes here -->
		<label class="all-lable frmregister-lbl" for="addressline1">Address Line 1 :&nbsp;</label>
		<input type="hidden" id="listingId" name="listingId" value="<%= listing.getListingId() %>">
		<label class="all-lable"><%= listing.getAddressLine1() %></label>
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="addressline2">Address Line 2 :&nbsp;</label>
		<label class="all-lable" > <%= listing.getAddressLine2() %></label>
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="city">City :&nbsp;</label>
		<label class="all-lable"><%= listing.getCity() %></label>
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="state">State :&nbsp;</label>
		<label class="all-lable"><%= listing.getState() %></label>
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="zipCode">Zip Code :&nbsp;</label>
		<label class="all-lable"><%= listing.getZipCode() %></label>
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="zoning">Zoning :&nbsp;</label>
		<label class="all-lable"> <%= listing.getZoning() %></label>
		<br />
		<br />
		<label  class="all-lable frmregister-lbl" class="all-lable frmregister-lbl" for="rent">Rent : $</label>
		<label class="all-lable"><%= listing.getRent() + " per month" %></label>
		<br />
		<br />
		<label class="all-lable frmregister-lbl"  for="description">Description :&nbsp;</label>
		<label class="all-lable"><%= listing.getDescription() %></label>
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="startDate">Start Date (mm/dd/yyyy) :</label>
		<input class="all-textbox frmregister-txt" type="text" id="startDate" name="startDate" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="endDate">End Date (mm/dd/yyyy) :</label>
		<input class="all-textbox frmregister-txt" type="text" id="endDate" name="endDate" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl"  for="deposit">Security Deposit :</label>
		<input class="all-textbox frmregister-txt" type="text" id="deposit" name="deposit" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="tenantId">Tenant Id :</label>
		<input class="all-textbox frmregister-txt" type="text" id="tenantId" name="tenantId" value="">
		<br />
		<br />
		<input class="all-submit button-form frmregister-submit" type="submit" id="btnSubmit" name="btnSubmit" value="Update">
		<br />
		<br />
		<a class="all-submit viewlist-button" href="viewMyListing">Back</a>
	</div>
	</form>

</body>
</html>