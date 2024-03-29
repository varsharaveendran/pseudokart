<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>addr_summary</title>
<style type="text/css">

.addressBox{
	border: 2px solid #a5a296; 
	width: 150px; 
	height: 120px; 
	margin: 5px; 
	padding: 10px; 
	color: #333; 
	border-radius: 3px; 
	background-position: bottom right;
	 background-repeat: no-repeat; 
	 word-wrap: break-word;
	 }
	 </style>
</head>

<%@ page
	import="com.opensymphony.xwork2.ActionContext,com.flipkart.action.placeorderAction,com.flipkart.model.Address"%>
<!-- ADD AFTER GOIN FROM SHIPPING ADDRESS TAB TO ORDER SUMMARY TAB -->
<body>
	<s:form >
		<div class="addressBox">
			<font><strong>Order Summary</strong></font><br/>
			<s:if test="%{ #session['orderID'] != null }">
			Order Id : &nbsp;&nbsp;<%=(String)ActionContext.getContext().getSession().get("orderID")%><br/>
			</s:if>
			Total Purchase : &nbsp;&nbsp; <%=(Double)ActionContext.getContext().getSession().get("totalCartAmt") %><br/>
		    
		
		</div>
	</s:form>
<br/><br/><br/>
		<s:if test="%{ #session['shipaddr'] != null }">
			<div class="addressBox">
						<font><strong>Shipping Address</strong></font>
						<s:property value="#session.shipaddr.name" /><br/>
						<s:property value="#session.shipaddr.streetAddress" /><br/>
						<s:property value="#session.shipaddr.city" />&nbsp;&nbsp;<s:property value="#session.shipaddr.pin" /><br/>
						<s:property value="#session.shipaddr.state" /><br/>
						<s:property value="#session.shipaddr.phone" /><br/>
					</div>

		</s:if>
	
</body>
</html>