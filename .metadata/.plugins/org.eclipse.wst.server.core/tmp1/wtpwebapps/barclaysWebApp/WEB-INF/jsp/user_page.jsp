<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>




<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome to Barclays site</title>

<!-- Bootstrap -->


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-      BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
	

<script>
	var uName = "";

	$(document).ready(function() {
		var temp = '<spring:url value="/getuName.json" />';
		console.log(temp);
		//		alert(encodeURIComponent(temp));
		$.getJSON(temp, {
			ajax : 'true'
		}, function(data) {
			console.log(data[0]);
			$("#userNameAnchor").text("Hi " + data[0]);
			uName = data[0];
		});
	});

	$(document)
			.ready(
					function() {

						$('a[name="view_balance_anchor"]')
								.click(
										function(event) {
											var temp = '<spring:url value="/getBalance/'+uName+'.json" />';
											console.log(temp);
											//		alert(encodeURIComponent(temp));
											$("#View_Balance").text(
													"Fetching Balance...");

											$
													.getJSON(
															temp,
															{
																ajax : 'true'
															},
															function(data) {
																console
																		.log(data[0]);
																$(
																		"#View_Balance")
																		.text(
																				"Balance is: "
																						+ data[0]);
															})
													.fail(
															function() {
																$(
																		"#View_Balance")
																		.text(
																				"Failed to Fetch Balance");
															});

										});

						$('#transferFunds')
								.click(
										function(event) {
											var temp = '<spring:url value="/transfer/'
													+ $('#toAccount').val()
													+ '/'
													+ $('#amount').val()
													+ '/'
													+ $('#myPassword').val()
													+ '.json" />';
											console.log(temp);
											//		alert(encodeURIComponent(temp));
											$
													.getJSON(
															temp,
															{
																ajax : 'true'
															},
															function(data) {
																console
																		.log(data[0]);
																if (data[0] == "1") {
																	alert("Funds Successfully transferred");
																} else {
																	alert("Some Error Occured");
																}
															})
													.done(function() {

													})
													.fail(
															function() {
																alert("Some Error Occured");

															});

										});

						$('a[name="view_account_anchor"]')
								.click(
										function(event) {
											var temp = '<spring:url value="/getAccountNo.json" />';
											console.log(temp);
											//		alert(encodeURIComponent(temp));
											$("#View_Account").text(
													"Fetching AccountNo...");

											$
													.getJSON(
															temp,
															{
																ajax : 'true'
															},
															function(data) {
																console
																		.log(data[0]);
																$(
																		"#View_Account")
																		.text(
																				"Your Account Number is : "
																						+ data[0]);
															})
													.fail(
															function() {
																$(
																		"#View_Account")
																		.text(
																				"Failed to Fetch Account Number");
															});

										});

						$('a[name="history_anchor"]')
								.click(
										function(event) {
											var temp = '<spring:url value="/getTransactionHistory.json" />';
											console.log(temp);
											//		alert(encodeURIComponent(temp));
											$("#Transaction_History").text(
													"Fetching Transaction History...");
											
											var html ="<table style='width:100%; border: 1px solid black;''>";
											html+="<tr><th>Sender Account</th>";
												html+="<th>Receiver Account</th>";
													html+="<th>Date</th>";
													html+="<th>Time</th></tr>";

											$
													.getJSON(
															temp,
															{
																ajax : 'true'
															},
															function(data) {
																var i = 0;
																var len = data.length;
																for (i=0; i < len; i++) {
																	html+="<tr>";
																	html+="<td>";
																	html += data[i].accNo;
																	html+="</td>";
																	html+="<td>";
																	html += data[i].toAccNo;
																	html+="</td>";
																	html+="<td>";
																	html += data[i].date;
																	html+="</td>";
																	html+="<td>";
																	html += data[i].time;
																	html+="</td>";
																	html+="</tr>";
																}
																html+="</table>";
															})
													.done(
															function() {
																$(
																		"#Transaction_History")
																		.html(
																				html);
															})
													.fail(
															function() {
																$(
																		"#Transaction_History")
																		.text(
																				"Failed to Fetch Account Number");
															});

										});

					});

	function View_Balance() {

		var x = document.getElementById("View_Balance");
		//    b.addEventListener("click",View_Balance,false);;
		if (x.style.display == "none") {
			document.getElementById("Transaction_History").style.display = "none";
			document.getElementById("Transfer_Funds").style.display = "none";
			document.getElementById("View_Account").style.display = "none";
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}

	function Transaction_History() {
		var x = document.getElementById("Transaction_History");
		if (x.style.display == "none") {
			document.getElementById("View_Balance").style.display = "none";
			document.getElementById("Transfer_Funds").style.display = "none";
			document.getElementById("View_Account").style.display = "none";
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}

	function Transfer_Funds() {
		var x = document.getElementById("Transfer_Funds");
		if (x.style.display == "none") {
			document.getElementById("Transaction_History").style.display = "none";
			document.getElementById("View_Balance").style.display = "none";
			document.getElementById("View_Account").style.display = "none";
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}

	function View_Account() {
		var x = document.getElementById("View_Account");
		if (x.style.display == "none") {
			document.getElementById("Transaction_History").style.display = "none";
			document.getElementById("View_Balance").style.display = "none";
			document.getElementById("Transfer_Funds").style.display = "none";
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}
</script>

<style>
	
		table.inner tr:nth-child(odd) {background-color: #ffffff}
		table.outer tr:nth-child(odd) {background-color: #ffffff}
		table.inner
		{
   		 width: 100%;
		
		}
		table.inner td 	
		{
    		  padding: 15px;
		}

		table.outer td 	
		{
    		  padding: 15px;
		}

		
		table.outer 
		{
    		 width: 200%;
		
		}
		table.outer td
		{
		 text-align:center;
		}
		
	</style>

</head>



<body>


	<link
		href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
		rel="stylesheet">

	<!--Nav bar code-->
	<nav class="navbar navbar-inverse container" role="navigation">
		<div class="container-fluid">

			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Barclays.com</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-center">

				</ul>


				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" id="userNameAnchor"
						class="dropdown-toggle" data-toggle="dropdown">Hi <b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li align="center" class="well">
								<!-- class well adds a rounded border around an elemen--> <a
								href="#" class="btn btn-sm btn-default"><span
									class="glyphicon glyphicon-log-out"></span> Logout</a>
						</ul></li>
					<!--li dropdwon end-->
				</ul>
				<!--navbarend-->
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!--nav bar code end-->

	<div class="container">

		<div class="row well">
			<div class="col-md-2 ">
				<ul class="nav nav-pills nav-stacked">

					<li class="active"><a name="view_account_anchor"
						href="javascript:View_Account()"><span class="glyphicon "></span>
							View Account</a></li>
					<br>
					<li class="active"><a name="view_balance_anchor"
						href="javascript:View_Balance()"><span class="glyphicon "></span>
							View Balance</a></li>
					<br>
					<li class="active"><a name="transfer_funds_anchor"
						href="javascript:Transfer_Funds()"><span class="glyphicon "></span>
							Transfer Funds</a></li>
					<br>
					<li class="active"><a name="history_anchor"
						href="javascript:Transaction_History()"><span
							class="glyphicon "></span> Transaction History</a></li>
					<br>

				</ul>
			</div>
			<!--class col end-->
			<div class="col-md-10">
				<div id="View_Account" style="display: :none;">
					Account No is :: <br />
				</div>
				<div id="View_Balance" style="display: none;">
					<h5>Balance is ::</h5>
					<br />
					<br />
				</div>
				<div id="Transaction_History" style="display: none;">
					<h5>Transaction History is ::</h5>
					<br />
					<br />
				</div>
				<div id="Transfer_Funds" style="display: none;">
					<div>
						<form action="">
							<label for="toaccount"><b>Transfer to Account </b></label> <input
								type="text" id="toAccount" name="toaccount"
								placeholder="Enter account no to transfer" pattern="[0-9]{3}"><br />
							<br /> <label for="tamount"><b>Transfer amount </b></label> <input
								type="number" id="amount" name="tamount"
								placeholder="Enter amount to be transfered"><br />
							<br /> <label for="cpassword"><b>Enter Password </b></label> <input
								type="password" id="myPassword" name="cpassword"
								placeholder="Enter password"><br />
							<br /> <input id="transferFunds" type="button" value="Submit"></input>
					</div>
				</div>
			</div>
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />

		</div>
	</div>

</body>
</html>


