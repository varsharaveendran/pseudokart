<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>addr_summary</title>
</head>


 <!-- ADD AFTER GOIN FROM SHIPPING ADDRESS TAB TO ORDER SUMMARY TAB -->
<body>
	<s:form action="index" theme="bootstrap" cssClass="form-vertical"
		label="Order Summary">

	<s:textarea name="summary" cols="5" rows="10" />


	</s:form>
	
	<s:form action="index" theme="bootstrap" cssClass="form-vertical"
		label="Shipping Address">

	<s:textarea name="ship_address" cols="5" rows="10" />


	</s:form>
</body>
</html>