<%@page import="java.util.List"%>
<%@page import="model.ProductModel"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product List</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/adminproductlist.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<link rel="icon" href="/path/to/favicon.ico" type="image/x-icon">
</head>
<body>
	<jsp:include page="AdminHeader.jsp"></jsp:include>

	<div class="product-list-container">
		<h2>Product List</h2>
		<table class="product-table">
			<thead>
				<tr>
					<th>Product ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Images</th>
					<th>Stock Quantity</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
				if (products != null && !products.isEmpty()) {
					for (ProductModel product : products) {
				%>
				<tr>
					<td><%=product.getProductId()%></td>
					<td><%=product.getName()%></td>
					<td><%=product.getDescription()%></td>
					<td>RS <%=product.getPrice()%></td>
					<td><img
						src="${pageContext.request.contextPath}/resources/images/<%= product.getImage() %>"
						alt="<%= product.getName() %>" class="small-image" /></td>
					<td><%=product.getStock()%></td>
					<td><button
							onclick="window.location='${pageContext.request.contextPath}/AdminEditProductServlet?productId=<%= product.getProductId() %>'"
							class="btn">Edit</button> <a href="${pageContext.request.contextPath}/DeleteProductServlet?productId=<%= product.getProductId() %>&isAdminDelete=yes"
						class="btn">Delete</a></td>
				</tr>
				<%
				}
				} else {
				// Optionally display a message if no products are found
				out.print("<tr><td colspan='6'>No products available.</td></tr>");
				}
				%>
			</tbody>
		</table>
	</div>
	<footer>
		<p>©2024 Eclipse Vision. All rights reserved.</p>
	</footer>
</body>
</html>
