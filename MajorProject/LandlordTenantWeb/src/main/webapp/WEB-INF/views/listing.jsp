<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - Add Listing</title>
<link rel="stylesheet" href="<c:url value="/resources/style/style.css" />" >
</head>
<body>
<jsp:include page="menu.jsp" />
<ul class="breadcrumb">
  <li><a href="landingPage">Home >> </a></li>
  <li>Add Listing</li>
</ul>

<form class="form-listing" id="frmListing" action="listingSubmit" method="post" enctype="multipart/form-data">
	<div class="inner-regform">
	<!-- <h3>Add Listing</h3>  -->
		<label class="all-lable frmregister-lbl" for="listingImage">Image : </label>
		<input class="all-textbox frmregister-txt" type="file" id="listingImage" name="listingImage">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="addressline1">Address Line 1 : </label>
		<input class="all-textbox frmregister-txt" type="text" id="addressline1" name="addressline1" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="addressline2">Address Line 2 : </label>
		<input class="all-textbox frmregister-txt" type="text" id="addressline2" name="addressline2" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="city">City : </label>
		<input class="all-textbox frmregister-txt" type="text" id="city" name="city" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="state">State : </label>
		<input class="all-textbox frmregister-txt" type="text" id="state" name="state" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="zipCode">Zip Code : </label>
		<input class="all-textbox frmregister-txt" type="text" id="zipCode" name="zipCode" value="">
		<br />
		<br />
		<label  class="all-lable frmregister-lbl" for="zoning">Zoning : </label>
		<select class="all-combobox frmregister-cmb" name="zoning" id="zoning">
  		<option value="Residential">Residential</option>
  		<option value="Commercial">Commercial</option>
		</select>
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="rent">Rent : $</label>
		<input class="all-textbox frmregister-txt" type="text" id="rent" name="rent" value="">
		<br />
		<br />
		<label class="all-lable frmregister-lbl" for="description">Description : </label>
		<textarea class="all-textbox frmregister-txt" rows='4' cols='50' id="decription" name="description"></textarea>
		<br />
		<br />
		<input class="all-submit button-form frmregister-submit" type="submit" id="btnSubmit" name="btnSubmit" value="Add">
		<a class="all-submit viewlist-button" href="landingPage">Back</a>
	</div>
	</form>

</body>
</html>