<%@page import="ltms.entity.LeaseEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Rating</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<form id="frmRating" action="ratingSubmit" method="post">
	<div style="margin-top:20px;" class="inner-regform">
		<label class="all-lable frmregister-lbl" for="leaseId">Property:</label>
		<select class="all-combobox frmregister-cmb" name="leaseId" id="leaseId">
<%
List<LeaseEntity> leases = (List<LeaseEntity>)(request.getAttribute("leasesForRating"));
for(LeaseEntity lease: leases) { %>
  		<option value="<%= lease.getLeaseId() + ":" + lease.getLandlordId() + ":" + lease.getTenantId() %>"><%= lease.getListingAddressline1() + " " + lease.getListingAddressline2() %></option>
<% } %>
		</select>
		<br />
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="rating">Rating:</label>
		<input class="all-textbox frmregister-txt" type="text" id="rating" name="rating" value="">
		<br />
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="review">Review:</label>
		<textarea class="all-textbox frmregister-txt" rows='5' cols='50' id="review" name="review"></textarea>
		<br />
		<br />
		<br />
		
		<input class="all-submit button-form frmregister-submit" type="submit" id="btnSubmit" name="btnSubmit" value="Rate">
		<a class="all-submit viewlist-button" href="landingPage">Back</a>
		<br />
	</div>
	</form>

</body>
</html>