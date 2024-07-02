<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>About Us</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<link rel="icon" href="/path/to/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/about.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

<main>


	<div class="hero-section">
		<img src="${pageContext.request.contextPath}/photos/aboutback.jpg" alt="Nature background"
			class="hero-image">
		<h1 class="hero-title">
			Beyond Imagery, <br> Eclipse Vision.
		</h1>

	</div>

	<div class="content-section">
		<p>
			Our breakthrough Ultra-Definition screens illuminate every pixel with
			precision, <br> creating a spectacle of color and contrast that
			captivates the senses. <br> Coupled with our seamless smart
			interface, VisionPro TVs don’t just <br> broadcast content—they
			deliver an unparalleled journey into storytelling. <br>
		</p>
		<div class="underline"></div>
	</div>

	<div class="features-container">
		<div class="feature">
			<img src="/photos/innovation.jpeg" alt="" class="product-image">
			<h2>INNOVATION IN VIEWING</h2>
			<p>Discover the unseen with Eclipse Vision's pioneering display
				technology. Every television is a portal to breathtaking clarity and
				color, engineered for an immersive experience that brings content to
				life. Simplicity meets sophistication in every design, delivering
				elegance that elevates.</p>
		</div>

		<div class="feature">
			<img src="/photos/qualiy check bruh.jpg" alt="" class="product-image">
			<h2>CONFIDENCE IN QUALITY</h2>
			<p>Eclipse Vision stands behind the exceptional quality of each
				television. Our confidence is your peace of mind—enjoy a 30-day
				trial period where the brilliance of our screens speaks for itself.
				Not just satisfied but captivated? It’s the Eclipse Vision
				guarantee.</p>
		</div>
		<div class="feature">
			<img src="/photos/crafted with care.jpg" alt="" class="product-image">
			<h2>CRAFTED WITH CARE</h2>
			<p>Eclipse Vision embraces the future, responsibly. We’re
				pioneering the integration of eco-friendly materials in our
				televisions, minimizing our footprint while maximizing performance.
				It's technology in harmony with the planet, for a clearer conscience
				and screen.</p>
		</div>
	</div>


	<div class="form-container">
		<h1>You have any feedback?</h1>
		<h4>Feel free to send us message.</h4>
		<form action="${pageContext.request.contextPath}/FeedbackForm" method="post">
			<div class="form-group">
				<label for="name">Name:</label> <input type="text" id="name"
					name="name" required>
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" id="email"
					name="email" required>
			</div>
			<div class="form-group">
				<label for="message">Message:</label>
				<textarea id="message" name="message" rows="4" required></textarea>
			</div>
			<button type="submit">Submit</button>
		</form>
	</div>
	
	</main>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>