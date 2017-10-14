<%@page import="ltms.entity.LeaseEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landlord Tenant System - View My Leases</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<ul class="breadcrumb">
  <li><a href="landingPage">Home >> </a></li>
  <li>View My Lease</li>
</ul>
<h2>Here are your leases to view!</h2>
<br />
<table  class="listing-tablecell" width="100%">
<tr>
<th width="16%" align="left">Address</th>
<th width="16%" align="left">Start Date</th>
<th width="16%" align="left">End Date</th>
<th width="16%" align="left">Security Deposit</th>
<th width="16%" align="left">Landlord Name</th>
<th width="16%" align="left">Tenant Name</th>
</tr>
<%
List<LeaseEntity> leases = (List<LeaseEntity>)(request.getAttribute("myLeases"));
for(LeaseEntity lease: leases) {%>
<tr>
<td><%=lease.getListingAddressline1() + lease.getListingAddressline2()%></td>
<td><%=lease.getStartDate()%></td>
<td><%=lease.getEndDate()%></td>
<td>$ <%=lease.getSecurityDeposit()%></td>
<td><%=lease.getLandlordId()%></td>
<td><%=lease.getTenantId()%></td>

</tr>
<% } %>
</table>
<a class="all-submit viewlist-button" style="float:none; overflow:auto; display:block; margin:5px auto;" href="landingPage">Back</a>
</body>
</html>