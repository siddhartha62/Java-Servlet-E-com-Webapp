<%@page import="java.util.List"%>
<%@page import="model.ProductModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Product Details</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/addproduct.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
</head>
<body>

	<jsp:include page="AdminHeader.jsp"></jsp:include>

	<div class="add-product-container">
		<h2>Update Products</h2>
		<form
			action="${pageContext.request.contextPath}/AdminEditProductServlet?hi=1"
			method="post">
			<input type="hidden" name="productId" value="${products.getProductId()}"
				required>
			<div class="form-row">
				<div class="form-group">
					<label for="productName">Name</label> <input type="text"
						id="productName" name="name" value="${products.getName()}"
						required>
				</div>
				<div class="form-group">
					<label for="productPrice">Price</label> <input type="number"
						name="price" value="${products.getPrice()}" required>
				</div>
			</div>
			<div class="form-group">
				<label for="stockQuantity">Stock Quantity</label> <input
					type="number" id="stockQuantity" name="stock"
					value="${products.getStock()}" required>
			</div>
			<div class="form-group">
				<label for="productDescription">Description</label>
				<textarea id="productDescription" name="description" required>${products.getDescription()}</textarea>
			</div>
			<div class="form-group">
				<button type="submit">Update</button>
			</div>
		</form>
	</div>

</body>
<footer>
	<p>©2024 Eclipse Vision. All rights reserved.</p>
</footer>
</html>
