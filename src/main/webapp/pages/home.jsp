<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ProductModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/home.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/footer.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<link rel="icon" href="/path/to/favicon.ico" type="image/x-icon">
<title>Eclipse Vision</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<main>
		<section class="store-title">


			<div id="carousel" class="carousel"></div>

			<script src="${pageContext.request.contextPath}/stylesheet/script.js"></script>

		</section>

		<section class="features" id="features">
			<h1 class="heading">
				Our <span>Products</span>
			</h1>
			<!-- Fixed typo in 'Product's' -->
			<div class="box-container">
				<%
				List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
				if (products != null && !products.isEmpty()) {
					for (ProductModel product : products) {
				%>
				<div class="box">
					<a
						href="${pageContext.request.contextPath}/ProductDescription?productId=<%= product.getProductId() %>"
						class="btnn"> <img
						src="${pageContext.request.contextPath}/resources/images/<%= product.getImage() %>"
						alt="<%= product.getName() %>" />
					</a>
					<h3>
	
						<%=product.getName()%></h3>

					<p>Only <%=product.getStock()%> iteams left</p>
					<p>
						Rs
						<%=product.getPrice()%></p>

					<a
						href="${pageContext.request.contextPath}/AddtoCartServlet?productId=<%= product.getProductId() %>"
						class="btn">Add to Cart</a>
				</div>
				<%
				}
				} else {
				// Optionally display a message if no products are found
				out.print("<p>No products available.</p>");
				}
				%>
			</div>
		</section>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
</body>


</html>
