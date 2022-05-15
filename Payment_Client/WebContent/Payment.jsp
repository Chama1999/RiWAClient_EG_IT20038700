<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/payment.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Payment.js"></script>
<script src="Components/auth.js"></script>
</head>
<body>
 <section class="sub-header">
  <nav>
      <a href="index.html"><img src="Media/logopanademic.png"></a>
      <div class="title">
      <h1>ELECTROGRID</h1>
      </div>
      <div class="nav-links" id="navLinks">
         <i class="fa fa-times" onclick="hideMenu()"></i>
          <ul>
              <li><a href="">OVERVIEW</a></li>
              <li><a href="">SERVICES</a></li>
              <li><a href="">EMERGENCIES</a></li>
              <li><a href="">CONTACT</a></li>
              <li><input id="btnLogout" name="btnLogout" type="button" value="logout" class="btn btn-primary"></li>
             
          </ul>

      </div>
        <i class="fa fa-bars" onclick="showMenu()"></i>
  </nav>
     <h1>Payment Management</h1>



</section>
<br>
	<div class="container">
	
		<div class="row">
			<div class="col-6">
				<!--  <form id="formItem" name="formItem">
					Item code: <input id="itemCode" name="itemCode" type="text"
						class="form-control form-control-sm"> <br> Item name:
					<input id="itemName" name="itemName" type="text"
						class="form-control form-control-sm"> <br> Item
					price: <input id="itemPrice" name="itemPrice" type="text"
						class="form-control form-control-sm"> <br> Item
					description: <input id="itemDesc" name="itemDesc" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>-->
				<form id="formItem" name="formItem">
					CardType: <input id="CardType" name="CardType" type="text"
						class="form-control form-control-sm"> <br> CardNumber:
					<input id="CardNumber" name="CardNumber" type="text"
						class="form-control form-control-sm"> <br> CardHolderName: <input id="CardHolderName" name="CardHolderName" type="text"
						class="form-control form-control-sm"> <br> CVC: <input id="CVC" name="CVC" type="text"
						class="form-control form-control-sm"> <br> CardExpireDate: <input id="CardExpireDate" name="CardExpireDate" type="text"
						class="form-control form-control-sm"> <br> PaymentDate: <input id="PaymentDate" name="PaymentDate" type="text"
						class="form-control form-control-sm"> <br> BillID: <input id="BillID" name="BillID" type="text"
						class="form-control form-control-sm"> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
					<%
						Payment paymentObj = new Payment();
					out.print(paymentObj.getAllPayment());
					%>

				</div>
			</div>
		</div>
	</div>



</body>
</html>