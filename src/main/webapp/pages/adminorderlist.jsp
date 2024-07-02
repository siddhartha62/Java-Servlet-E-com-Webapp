<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order List</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/adminorderlist.css">
</head>
<body>

	<%@page import="java.util.List"%>
	<%@page import="model.OrderModel"%>
	<%@page import="java.util.Iterator"%>

	<%
	List<OrderModel> orders = (List<OrderModel>) request.getAttribute("orders");
	%>

	<%-- Header and styling --%>
	<%@ include file="AdminHeader.jsp"%>

	<div class="order-list-container">
		<h2>Order List</h2>
		<table class="order-table">
			<thead>
				<tr>
					<th>Customer</th>
					<th>Product Name</th>
					<th>Order Date</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Total Price</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (orders != null && !orders.isEmpty()) {
				%>
				<%
				Iterator<OrderModel> iterator = orders.iterator();
				%>
				<%
				while (iterator.hasNext()) {
				%>
				<%
				OrderModel order = iterator.next();
				%>
				<tr>
					<td><%=order.getCustomer()%></td>
					<td><%=order.getProductName()%></td>
					<td><%=order.getOrderDate()%></td>
					<td><%=order.getQuantity()%></td>
					<td><%=order.getPrice()%></td>
					<td><%=order.getTotalPrice()%></td>
					<td><select class="status-select"
						id="status<%=order.getOrderId()%>">
							<option value="waiting"
								<%=order.getStatus().equals("waiting") ? "selected" : ""%>>Waiting</option>
							<option value="Delivered"
								<%=order.getStatus().equals("Delivered") ? "selected" : ""%>>Delivered</option>
					</select></td>
					<td>
						<button class="btn"
							onclick="window.location.href='${pageContext.request.contextPath}/UpdateStatusServlet?orderId=<%=order.getOrderId()%>&orderStatus=' + getSelectedStatus('<%=order.getOrderId()%>')">Update</button>
					</td>
				</tr>
				<%
				}
				%>
				<%
				} else {
				%>
				<tr>
					<td colspan="7">No orders available.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<script>
		function getSelectedStatus(orderId) {
			var selectElement = document.getElementById('status' + orderId);
			return selectElement.value;
		}
	</script>
</body>
</html>
