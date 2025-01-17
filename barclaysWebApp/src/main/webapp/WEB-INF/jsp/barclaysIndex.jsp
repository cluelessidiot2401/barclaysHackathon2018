
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="favicon.ico" rel="icon" type="image/png">
<title>Barclays bank</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="nh10.css" rel="stylesheet">
<link href="cards.css" rel="stylesheet">
<link href="footer.css" rel="stylesheet">
<link href="login.css" rel="stylesheet">



<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<style>
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 70%;
	margin: auto;
}
</style>


</head>

<body>



	<!--Navigation bar code-->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-er">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Barclays Bank</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">


				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a><span
							class="glyphicon glyphicon-home"></span>
							<div
								onclick="document.getElementById('id01').style.display='block'"
								style="width: auto;">Home</div></a></li>
					<li><a href="#"> <span class="glyphicon glyphicon-user "></span>
							<div
								onclick="document.getElementById('id02').style.display='block'"
								style="width: auto;">Login</div></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--Navigation bar code-->

	<!--Modal part -->
	<div id="id01" class="modal">
		<span onclick="document.getElementById('id01').style.display='none'"
			class="close" title="Close Modal">&times;</span>
		<form class="modal-content" action="/action_page.php">
			<div class="container">
				<h3>Sign Up</h3>


				<label for="name"><b>Name</b></label> <input type="text"
					placeholder="Enter name" name="name" required><br /> <label
					for="email"><b>Email</b></label> <input type="text"
					placeholder="Enter Email" name="email" required><br /> <label
					for="phonenum"><b>Phone Number (format: xxxx-xxx-xxxx):</b></label><br />
				<input id="phonenum" placeholder="Enter Mobile no" type="tel"
					pattern="^\d{4}-\d{3}-\d{4}$" name="phoneno" required><br />

				<label for="panno"><b>Pan card Number (format:
						xxxxx9999x):</b></label><br /> <input id="panno" type="text"
					placeholder="Enter Pan-Card no"
					pattern="[A-za-z]{5}\d{4}[A-Za-z]{1}" name="panno" required>
				<br /> <label for="gender"><b>Choose Gender</b></label> <input
					type="radio" name="gender" value="male" checked> Male <input
					type="radio" name="gender" value="female"> Female <input
					type="radio" name="gender" value="other"> Other <br /> <label
					for="dob"><b>Choose Date of Birth</b></label> <input type="date"
					name="dob"  required><br /> <br /> <label for="psw"><b>Password</b></label><br />
				<input type="password" placeholder="Enter Password" name="psw"
					required>
				<!--
      <label for="psw-repeat"><b>Repeat Password</b></label><br/>
      <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
-->

				<div class="clearfix">
					<button type="button"
						onclick="document.getElementById('id01').style.display='none'"
						class="cancelbtn">Cancel</button>
					<button type="submit" class="signupbtn">Sign Up</button>
				</div>
			</div>
		</form>
	</div>

	<div id="id02" class="modal">
		<span onclick="document.getElementById('id02').style.display='none'"
			class="close" title="Close Modal">&times;</span>
		<form class="modal-content" action="/action_page.php">
			<div class="container">
				<h1>Log in</h1>
				<p>Please fill in this form to log in to your account.</p>


				<label for="email"><b>Email</b></label><br /> <input type="text"
					placeholder="Enter Email" name="email" required><br /> <label
					for="psw"><b>Password</b></label><br /> <input type="password"
					placeholder="Enter Password" name="psw" required><br />


				<div class="clearfix">
					<button type="button"
						onclick="document.getElementById('id02').style.display='none'"
						class="cancelbtn">Cancel</button>
					<button type="submit" class="signupbtn">Sign Up</button>
				</div>
				<br /> <br />
			</div>
		</form>
	</div>

	<script>
		// Get the modal
		var modal = document.getElementById('id01');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
		var modal = document.getElementById('id02');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
	<!--Modal part end here-->

	<br>
	<br>


	<div class="container-fluid">


		<div class="container">
			<br>
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>

				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item ">
						<img src="img1.jpg" alt="Chania" width="460" height="345">

					</div>

					<div class="item">
						<img src="img2.jpg" alt="Chania" width="460" height="345">
					</div>

					<div class="item active">
						<img src="img5.jpg" alt="Flower" width="460" height="345">
					</div>

				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>
	<br>
	<br>


	<div class="container">
		<div class="transbox">
			<!--
  <form class="form-inline">
  <div class="form-group">
      <label for="gender">Looking for</label><br>
      <select class="selectpicker">
  <option>Male</option>
  <option>Female</option>
 </select>
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label><br>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password">
    </div>
    <div class="checkbox">
      <label><input type="checkbox"> Remember me</label>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>-->


			<!--Footer-->


			<footer>
				<div class="container">
					<div class="row">
						<div class="col-md-6 col-sm-12 right-border">
							<div class="footer-about">
								<h2 class="footer-title">About Us</h2>
								<p>
									<B>Our common purpose is to help people achieve their
										ambitions – in the right way. We’ll measure and reward our
										people, not just on commercial results, but on how they live
										our Values and bring them to life every day. And we’ll judge
										our success on a balanced scorecard of impact.</B>
								</p>
							</div>


						</div>
						<div class="col-md-6 col-sm-12">
							<div class="col-md-6 col-sm-6">
								<div class="contact-info">
									<h2 class="footer-title">Contact Info</h2>
									<ul>

										<li><B> barclays_hr@gmail.com</B></li>
								</div>
							</div>
							<div class="col-md-6 col-sm-6 left-border">

								<h2 class="footer-title">Developers</h2>


								<div class="footer-title">
									<h5>
										<B>Piyush Doke</B>
									</h5>
									<h5>
										<B>Shashank Wadje</B>
									</h5>
									<h5>
										<B>Vikram Ambre</B>
									</h5>
								</div>
							</div>


						</div>
					</div>
				</div>
			</footer>
</body>
</html>
