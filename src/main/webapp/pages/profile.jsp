<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.OrderModel"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/profile.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap"
	rel="stylesheet">
<title>Profile Card</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="profile-card">
		<div class="profile-section">
			<div class="profile-image">
				<img
					src="${pageContext.request.contextPath}/resources/images/${profile.imageUrlFromPart}" />
			</div>
			<div class="profile-info">
				<div class="info-row">
					<label>Full Name:</label> <span>${profile.firstName}
						${profile.lastName}</span>
				</div>
				<div class="info-row">
					<label>User Name:</label> <span>${profile.userName}</span>
				</div>
				<div class="info-row">
					<label>Address:</label> <span>${profile.address}</span>
				</div>
				<div class="info-row">
					<label>Phone Number:</label> <span>${profile.phoneNumber}</span>
				</div>
				<div class="info-row">
					<label>Email Address:</label> <span>${profile.email}</span>
				</div>
			</div>
			<div class="profile-edit">
				<button onclick="toggleEditForm()">Edit</button>
			</div>
		</div>
	</div>

	<div id="editForm" style="display: none;">
		<h2>Edit Profile</h2>
		<form action="${pageContext.request.contextPath}/ProfileUpdateServlet"
			method="post">
			<label for="firstName">First Name:</label> <input type="text"
				id="firstName" name="firstName" value="${profile.firstName}">
			<br> <label for="lastName">Last Name:</label> <input type="text"
				id="lastName" name="lastName" value="${profile.lastName}"> <br>
			<label for="lastName">User Name:</label> <input type="text"
				id="lastName" name="username" value="${profile.userName}"> <br>
			<label for="lastName">Address:</label> <input type="text"
				id="lastName" name="Address" value="${profile.address}"> <br>
			<label for="lastName">Phone Number:</label> <input type="text"
				id="lastName" name="phonenumber" value="${profile.phoneNumber}">
			<br> <br>
			<button type="submit">Save</button>
		</form>
	</div>

	<div class="order-history">
		<h2>Your Orders History</h2>
		<table>
			<thead>
				<tr>
					<th>Product Name</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Total Price</th>
					<th>Order Date</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				// Iterate through the list of orders and populate the table rows
				List<OrderModel> orders = (List<OrderModel>) request.getAttribute("orders");
				if (orders != null && !orders.isEmpty()) {
					for (OrderModel order : orders) {
				%>
				<tr>
					<td><%=order.getProductName()%></td>
					<td><%=order.getQuantity()%></td>
					<td><%=order.getPrice()%></td>
					<td><%=order.getTotalPrice()%></td>
					<td><%=order.getOrderDate()%></td>
					<td><%=order.getStatus()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<!-- If no orders available, display a message -->
				<tr>
					<td colspan="6">No orders available.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		function toggleEditForm() {
			var editForm = document.getElementById("editForm");
			if (editForm.style.display === "none") {
				editForm.style.display = "block";
			} else {
				editForm.style.display = "none";
			}
		}
	</script>
</body>
</html>
