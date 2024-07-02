<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.AddToCartModel"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Shopping Cart</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/cart.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<link rel="icon" href="/path/to/favicon.ico" type="image/x-icon">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="shopping-cart">
		<h1>Your Shopping Cart</h1>
		<%
		List<AddToCartModel> carts = (List<AddToCartModel>) request.getAttribute("carts");
		if (carts != null && !carts.isEmpty()) {
			for (AddToCartModel cart : carts) {
		%>
		<div class="cart-item">
			<div class="item-image">
				<img
					src="${pageContext.request.contextPath}/resources/images/<%= cart.getImage() %>"
					alt="" width="100%" height="100%">
			</div>
			<div class="item-info">
				<p class="item-name"><%=cart.getProductName()%></p>
				<p class="item-price">
					$<%=cart.getProductPrice()%>.00 per item
				</p>
				<div class="item-quantity">
					<label for="item-<%=cart.getCartId()%>-quantity">Total Item</label>
					<input type="number" id="item-<%=cart.getCartId()%>-quantity"
						name="item-<%=cart.getCartId()%>-quantity"
						value="<%=cart.getQuantity()%>">
				</div>
				<div class="item-actions">
					<a class="remove_btn"
						href="${pageContext.request.contextPath}/PlaceOrderServlet?cartId=<%=cart.getCartId()%>&quantity="
						onclick="var quantity = quantityReturn(this); this.href += quantity; return true;">
						Place Order </a> <a class="remove-item"
						href="${pageContext.request.contextPath}/DeleteProductServlet?cartId=<%= cart.getCartId() %>&isCartDelete=yes"
						class="btn">Remove</a>
				</div>
			</div>
		</div>
		<%
		}
		} else {
		// Optionally display a message if no products are found
		out.print("</h1>No products available.</h1>");
		}
		%>
	</div>

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
	<script>
		function quantityReturn(button) {
			// Find the parent cart-item element
			var cartItem = button.closest(".cart-item");

			// Retrieve the quantity from the input field inside the cart-item
			var quantity = cartItem.querySelector(".item-quantity input").value;

			return quantity;
		}
	</script>

</body>
</html>
