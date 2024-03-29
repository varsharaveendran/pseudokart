<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<link type="text/css" rel="stylesheet"
	href="./ViewResources/css/bootstrap.css">
<script type="text/javascript" src="./ViewResources/js/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="./ViewResources/js/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="./ViewResources/js/bootstrap-tab.js"></script>
<script>window.history.forward(1);</script>
<title>Place Order</title>
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
.tickSign{
    width: 30px;
    height: 20px;
    float: left;
    position: relative; right: 1px;
    background-image: url('./images/tickk.png');
    display : url('./images/tickk.png');
    background-repeat: no-repeat;
}
.tickSign:hover{
    background-image: url('./images/tickk.png');
}

</style>

<sb:head />
</head>

<%@ page import="com.opensymphony.xwork2.ActionContext,java.util.*,com.flipkart.action.placeorderAction,com.flipkart.model.*"%>

<body onload="javascript:history.go(1)">

<script>
$(window).load(function(){
   var tabno = <%=ActionContext.getContext().getSession().get("tabno") %>
  
  if(tabno == 0)
	  $('#myTab li:eq(0) a').tab('show');
 if(tabno == 1)
	  $('#myTab li:eq(1) a').tab('show');
   if(tabno == 2)
	  $('#myTab li:eq(2) a').tab('show');
   if(tabno == 3)
   {	
	   $('#myTab li:eq(3) a').tab('show');
        var sub = <%=ActionContext.getContext().getSession().get("paytabno") %>
        if(sub == 0)
        	 $('#newTab li:eq(0) a').tab('show');
        if(sub == 1)
       	 $('#newTab li:eq(1) a').tab('show');
        if(sub == 2)
       	 $('#newTab li:eq(2) a').tab('show');
        if(sub == 3)
       	 $('#newTab li:eq(3) a').tab('show');
   }
   
});
    </script>

	<div class="tabbable">
		<div class="container-fluid">
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a data-toggle="tab" href="#Emaillogin">Email
						Login</a></li>
				<li><a href="#shippingaddress" >Shipping Address</a></li>
				<li><a href="#ordersummary" >Order Summary</a></li>
				<li><a href="#paymentoptions" >Payment
						Options</a></li>
			</ul>



			<div class="tab-content">

				<div id="Emaillogin" class="tab-pane active in">
				<s:form action="nextTab" theme="bootstrap" cssClass="form-horizontal"
						label="">
				<table>	
				<tr>
				<td><%=ActionContext.getContext().getSession().get("email") %></td>
				<td></td><td></td>
				<td>Not You?</td>
				<td></td>
				<td><a href="logout">Logout</a></td>
				<td></td>
				</tr></table>
				<br/><br/>
				<pre>Your Order Details will be sent to the email address</pre>
				
				<s:submit cssClass="btn btn-warning" value="Continue"
								align="right" ></s:submit>
				</s:form>
						
				</div>

				<div id="shippingaddress" class="tab-pane" >
		
		    
					<div style="width: 40%; float: left" >
						<center>
							<h5>Select Previous Address</h5><br/>
						</center>
						 <s:if test="addr_list != null">
						<s:iterator value="addr_list">
						<a style="color: #004b91; text-decoration: none;" href="multiAddr?address_id=<s:property value="address_id"/>" >
							<div class="addressBox" onmouseover="this.style.border = '2px solid green'" onmouseout="this.style.border = '2px solid #a5a296'">
								<b><s:property value="name" /></b><br />
								<s:property value="streetAddress" /><br/>
								<s:property value="city" /> , &nbsp;<s:property value="state" /><br />
								<s:property value="pin" /><br />
								<s:property value="phone" /><br/>
								<b style=" border: 1px solid #ccc;padding: 1px 18px 1px 30px; box-shadow: 1px 1px .3px #9d9d9d; background-color: #cecdcd;">Click to select</b>
								<!-- <div class="tickSign" id=""></div>-->
								</div>
								
					</a><br/>
						</s:iterator>
				      	
				</s:if>	
		      </div>
		      <div style="width: 1px; height: 450px; background-color: black; float: left;"></div>	
		      
		      
      
					<div style="width: 58%; float: right">
											<center>
							<h5>Enter New Shipping Address</h5>
						</center>
						<br /> <br />
						<s:actionerror theme="bootstrap"/>
						<s:fielderror/>
						<s:actionmessage/>
						<s:form action="newShipAddr" theme="bootstrap" cssClass="form-horizontal"  method="post">
						 
							<s:textfield label="Name*" name="name" size="15" />

							<s:textarea label="Address*" name="address" cols="20" rows="3" />

							<s:textfield label="Landmark" name="landmark" />

							<s:textfield label="City*" name="city" />


							<s:select label="State*"
								list="{'Andaman and Nicobar Islands','Andhra Pradesh','Arunachal Pradesh',
			'Assam','Bihar','Chandigarh','Chhattisgarh','Dadra and Nagar Haveli',
			'Daman and Diu','Delhi','Goa','Gujarat','Haryana','Himachal Pradesh',
			'Jammu and Kashmir','Jharkhand','Karnataka','Kerala','Lakshadweep',
			'Madhya Pradesh','Maharashtra','Manipur','Meghalaya','Mizoram',
			'Nagaland','Orissa','Pondicherry','Punjab','Rajasthan','Sikkim',
			'Tamil Nadu','Tripura','Uttar Pradesh','Uttrakhand','West Bengal'}"
								name="state" emptyOption="false" headerKey="-1"
								headerValue="-----Select State---------" />

							<s:textfield label="Country" name="country" value="India"
								disabled="true" />

							<s:textfield label="Pincode*" name="pincode" id="pin"/>

							<s:textfield label="Phone*" name="phone" id="contactnumber"/>

							<s:submit cssClass="btn btn-warning" value="Save and Continue"
								align="right"></s:submit>
								</s:form>	
					</div>
				</div>



				<div id="ordersummary" class="tab-pane">
					<s:form action="orderReview" theme="bootstrap" cssClass="form-horizontal"
						label="Review Your Order">
						<table class="table table-striped">
							<tr>
								
								<th>Item Description</th>
								<th>Delivery Time</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Subtotal</th>
							</tr>
							<s:iterator value="cartItems">
							<tr>
							<td><s:property value="ItemDesc"/></td>
							<td><s:property value="DeliveryTime"/></td>
							<td><s:property value="subtotal"/></td>
							<td><s:property value="quantity"/></td>
							<td><s:property value="subtotal"/></td>
							</tr>
							</s:iterator>
							
						</table>
						<table>
							<tr>

								<th>Amount Payable</th>
								<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<th><%=(Double)ActionContext.getContext().getSession().get("totalCartAmt") %></th>
							</tr>
						</table>
						<br />
						<br />
						<s:submit cssClass="btn btn-warning" value="Continue" />
					</s:form>
				</div>

				<div id="paymentoptions" class="tab-pane">

					


					<div class="tabbable tabs-left">
						<ul class="nav nav-tabs" id="newTab">
							<li class="active"><a data-toggle="tab" href="#netbank">Net
									Banking</a></li>
							<li><a href="#card" data-toggle="tab">Credit/Debit Card</a></li>
							<li><a href="#gift" data-toggle="tab">e-Gift Voucher</a></li>
							<li><a href="#wallet" data-toggle="tab">e-Wallet</a></li>
						</ul>
                         
                         
				
						<div class="tab-content">
							<div id="netbank" class="tab-pane active in">
								<s:form theme="bootstrap" cssClass="form-horizontal"
									label="Pay With Net Banking" action="paymentGateway">
									<br />
									<pre>Note : After Clicking "pay",you will be redirected to a secure payment Gateway.</pre>
									<br />
									<center>
									<s:submit cssClass="btn btn-warning" value="Pay" />
									</center>
								</s:form>
							</div>

							<div id="card" class="tab-pane">
								<s:actionerror theme="bootstrap" />
								<s:actionmessage theme="bootstrap" />
								<s:fielderror theme="bootstrap" />
								<s:form action="creditdebitPay" theme="bootstrap" cssClass="form-horizontal"
									label="Pay Using Credit/Debit Cards ">
									<pre>Enter your Card Details</pre>
									<br />
									<br />
									<s:textfield label="Card Number" name="cardno" />
									<s:textfield label="Name on Card" name="name_card" />
									<s:select label="Expiry Date"
										list="{'1','2','3','4','5','6','7','8','9','10','11','12'}"
										name="month" emptyOption="false" headerKey="-1"
										headerValue="Month" />

									<s:select
										list="{'13','14','15','16','17','18','19','20',
													'21','22','23','24','25','26','27','28','29','30',
													'31','32','33','34','35','36','37','38','39','40',
													'41','42','43','44','45','46','47','48','49','50',
													'51','52'}"
										name="year" emptyOption="false" headerKey="-1"
										headerValue="Year" />

									<s:textfield label="CVV No" name="cvv"
										tooltip="Enter three digits found at the back of card" />

									<s:submit cssClass="btn btn-warning" value="Pay" />
								</s:form>


							</div>
							<%
							Cart ob=(Cart)ActionContext.getContext().getSession().get("vouchercart");
							if(ob==null)
							{
							%>
						
							<div id="gift" class="tab-pane">
								<s:actionerror theme="bootstrap" />
								<s:actionmessage theme="bootstrap" />
								<s:fielderror theme="bootstrap" />
								<s:form action="voucherpay" theme="bootstrap" cssClass="form-horizontal"
									label="Pay Using e-Gift Voucher ">
									<pre>Enter the Gift Voucher Details</pre>
									<br />
									<br />
									<s:textfield label="Card Number" name="vcardnum" />
									<s:password label="PIN Number" name="vpinno" />

									<s:submit cssClass="btn btn-warning" value="Pay" />
								</s:form>
							</div>
							<%} %>
							<%
							   double bal=Wallet.findBalance();
							   double cart_amt=(Double)ActionContext.getContext().getSession().get("totalCartAmt");
							   String topupamt=(String)ActionContext.getContext().getSession().get("topupamt");
							   Cart obj=(Cart)ActionContext.getContext().getSession().get("vouchercart");
							   if((bal > cart_amt) && (topupamt==null) && (obj==null))
							   {
							   
							%>
							<div id="wallet" class="tab-pane">
							
							<s:form action="walletpay" theme="bootstrap" cssClass="form-horizontal"
									label="Pay Using e-Wallet ">
									<table class="table table-hover">
									<tr class="success">
									<td><strong>Current Wallet Balance :</strong> </td>
									<td><%=bal%></td>
									</tr>
									<tr class="info">
									<td><strong>Amount payable :</strong> </td>
									<td><%=cart_amt %></td>
									</tr>
									<tr class="warning">
									<td><strong>Remaining Wallet Balance Will Be  :</strong> </td>
									<td><%=(bal-cart_amt) %></td>
									</tr>
									</table>
									<s:submit cssClass="btn btn-warning" value="Pay" onclick="func()" />
								</s:form>
								<script type="text/javascript">
								 function func()
								 {
									 alert("Payment Succesful");
								 }
								
								</script>
							
							</div>
							<%} %>
						</div>
					</div>
					<!-- End here -->
				</div>

			</div>
		</div>
		<!-- /.tab-content -->
	</div>
	<!-- /.tabbable -->
</body>
</html>


<!-- To do :  failure case for wallet ; failure case for voucher ; order summry -->