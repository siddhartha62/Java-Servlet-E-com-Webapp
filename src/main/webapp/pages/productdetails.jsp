<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Details</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/productdetails.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<link rel="icon" href="/path/to/favicon.ico" type="image/x-icon">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="product-container">
		<div class="product-image">
			<img
				src="${pageContext.request.contextPath}/resources/images/${products.getImage()}"
				alt="Product Image">
		</div>
		<div class="product-details">
			<h1 class="product-name">${products.getName()}</h1>
			<p class="product-description">${products.getDescription()}</p>
			<p class="product-price">Price: ${products.getPrice()}</p>
			<p class="product-stock">Stock Quantity: ${products.getStock()}</p>
			<a href="${pageContext.request.contextPath}/AddtoCartServlet?productId= ${products.getProductId()}"
						class="btn">Add to Cart</a>
		</div>
	</div>
</body>
<footer class="site-footer">
	<div class="container">
		<div class="about">
			<h3>About Eclipse Vision</h3>
			<p>We provide the latest in electronics at the best prices. Your
				satisfaction is our mission.</p>
		</div>
		<div class="footer-links">
			<h3>Quick Links</h3>
			<ul>
				<li><a href="#products">Products</a></li>
				<li><a href="#services">Services</a></li>
				<li><a href="#support">Support</a></li>
				<li><a href="#contact">Contact Us</a></li>
			</ul>
		</div>
		<div class="footer-contact">
			<h3>Contact Us</h3>
			<p>
				<i class="fas fa-envelope"></i> support@eclipsevision.com
			</p>
			<p>
				<i class="fas fa-phone"></i> +977 9849061788
			</p>
			<p>
				<i class="fas fa-map-marker-alt"></i> C4s,Purva, Nepal
			</p>
		</div>
		<div class="footer-social">
			<h3>Follow Us</h3>
			<a href="https://www.facebook.com" target="_blank"><i
				class="fab fa-facebook-f"></i></a> <a href="https://www.twitter.com"
				target="_blank"><i class="fab fa-twitter"></i></a> <a
				href="https://www.instagram.com" target="_blank"><i
				class="fab fa-instagram"></i></a>
		</div>
	</div>
</footer>
</html>
