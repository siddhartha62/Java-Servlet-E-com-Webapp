<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/footer.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<link rel="icon" href="/path/to/favicon.ico" type="image/x-icon">
<title>Login Page</title>

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="split-screen">
		<div class="left">
			<section class="welcome">
				<h1>Welcome back</h1>
				<p>Login and continue shopping.</p>
				<p>The best quality television available in the market</p>
			</section>
		</div>
		<div class="right">
			<form action="${pageContext.request.contextPath}/LoginServlet" method="post" class="login-form">
				<h2>Log in</h2>
				<div class="input-group">
					<label for="email">EMAIL ID</label> <input type="email"
						name="email" id="email" required>
				</div>
				<div class="input-group">
					<label for="password">PASSWORD</label> <input type="password"
						name="password" id="password" required>
				</div>
				<c:if test="${not empty errorMessage}">
					<div class="error-message">${errorMessage}</div>
				</c:if>
				<a href="#">Forgot Password?</a>
				<button type="submit">LOGIN</button>
			</form>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
