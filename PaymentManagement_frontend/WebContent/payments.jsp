<%@page import = "com.Payment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> <br><br>
<br>
<h1>Payment Management</h1>
	<form id="formPayment" name="formPayment" method="post" action="payments.jsp" >
	   Account Holder Name: 
	 <input id="name" name="name" type="text" 
	 class="form-control form-control-sm">
	 <br>Account Number: 
	 <input id="accNumber" name="accNumber" type="text" 
	 class="form-control form-control-sm">
	 <br> CVV: 
	 <input id="cvv" name="cvv" type="text" 
	 class="form-control form-control-sm">
	 <br> Expire Date : 
	 <input id="expDate" name="expDate" type="text" 
	 class="form-control form-control-sm">
	 <br> Phone: 
	 <input id="phone" name="phone" type="text" 
	 class="form-control form-control-sm">
	 <br> Email: 
	 <input id="email" name="email" type="text" 
	 class="form-control form-control-sm">
	 <br>
	 <input id="btnSave" name="btnSave" type="button" value="Save" 
	 class="btn btn-primary">
	 <input type="hidden" id="hidPaymentIDSave" 
	 name="hidPaymentIDSave" value="">
	</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPaymentGrid">
 <%
 Payment paymentObj = new Payment(); 
 out.print(paymentObj.readPayments()); 
 %>
</div>
</div> </div></div>
</body>
</html>