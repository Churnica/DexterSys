<%@page import="ltms.entity.MaintenanceEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - View Maintenance Requests</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<ul class="breadcrumb">
  <li><a href="landingPage">Home >> </a></li>
  <li>View Maintenance Request</li>
</ul>
<h2>Here are the maintenance requests you need to act upon!</h2>
<br />
<table class="listing-tablecell" width="100%">
<tr>
<th width="15%" align="left">Maintenance Id</th>
<th width="25%" align="left">Property</th>
<th width="20%" align="left">Date Raised</th>
<th width="20%" align="left">Issue</th>
<th width="20%" align="left">Tenant Id</th>
</tr>
<%
List<MaintenanceEntity> maintenanceList = (List<MaintenanceEntity>)(request.getAttribute("maintenanceRequests"));
for(MaintenanceEntity maintenance: maintenanceList) { %>
<tr>
<td><%= maintenance.getMaintanceId() %><br /></td>
<td><%= maintenance.getListingAddressline1() %><br /><%= maintenance.getListingAddressline2() %><br /></td>
<td><%= maintenance.getTokenDate() %><br /></td>
<td><%= maintenance.getIssue() %><br /></td>
<td><%= maintenance.getTenantId() %><br /></td>
</tr>
<% } %>
</table>
<a class="all-submit viewlist-button" style="float:none; overflow:auto; display:block; margin:5px auto;" href="landingPage">Back</a>
</body>
</html>