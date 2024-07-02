<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link rel="stylesheet" href="../stylesheet/register.css">
<link rel="stylesheet" href="../stylesheet/header.css">
<link rel="stylesheet" href="../stylesheet/footer.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<link rel="icon" href="/path/to/favicon.ico" type="image/x-icon">
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<!-- Information Section -->
		<div class="info-section">
			<h1>Welcome to Eclipse Vision</h1>
			<p>Welcome to Eclipse Vision, where clarity meets excellence! As
				a dedicated purveyor of high-definition experiences, we bring you
				the finest selection of televisions to elevate your viewing
				pleasure. Our commitment is to quality, affordability, and
				innovation, ensuring every moment in front of the screen is
				immersive and spectacular.</p>
			<p>
				Why Register? <br>- Access to exclusive deals and the latest
				promotions. <br> - Personalized recommendations based on your
				preferences. <br> - Faster checkout process to make your
				purchases swift and hassle-free. <br>- Track and manage your
				orders with ease.
			</p>
			<p>If you already have an account click below and Login</p>
			<a href="login.jsp">
				<button class="account-btn">Have An Account</button>
			</a>
		</div>

		<!-- Registration Form Section -->
		<div class="form-section">
			<h1>REGISTER FORM</h1>
			<form action="${pageContext.request.contextPath}/RegisterServlet" method="post"
				enctype="multipart/form-data">
				<input type="text" name="firstName" placeholder="First Name">
				<input type="text" name="lastName" placeholder="Last Name">
				<input type="text" name="userName" placeholder="User Name">
				<input type="email" name="email" placeholder="Your Email"> <input
					type="text" name="address" placeholder="Address"> <input
					type="text" name="phoneNumber" placeholder="Phone Number">
				<input type="password" name="password" placeholder="Password">
				<input type="password" name="confirmPassword"
					placeholder="Confirm Password"> 
					<input type="file" class="form-control-file" id="image" name="image" required />
				<button type="submit" class="register-btn">Register</button>
			</form>
		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
