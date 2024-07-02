<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../stylesheet/adminpanel.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<title>Eclipse Vision</title>
</head>
<body>

	<jsp:include page="AdminHeader.jsp"></jsp:include>

	<div class="container">
		<a href="${pageContext.request.contextPath}/pages/adminaddproduct.jsp" class="box" id="addProduct">
    <i class="fas fa-plus"></i> <span>Add Product</span>
</a>
<a href="${pageContext.request.contextPath}/AllOrders" class="box" id="orderList">
    <i class="fas fa-list-alt"></i> <span>Order List</span>
</a>
<a href="${pageContext.request.contextPath}/AdminProductList" class="box" id="productList">
    <i class="fas fa-th-list"></i> <span>Product List</span>
</a>
		
	</div>
</body>

</html>
