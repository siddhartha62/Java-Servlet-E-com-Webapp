<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/adminHeader.css">
<header class="navbar">
	<h1>Eclipse Vision</h1>
	<div class="logo">
		<a href="${pageContext.request.contextPath}/pages/adminpanel.jsp">
			<img
			src="${pageContext.request.contextPath}/photos/Eclipse Vision_transparent.png"
			alt="Eclipse Vision Logo" />
		</a>
	</div>
	<nav>
		<ul>
			<li><a
				href="${pageContext.request.contextPath}/pages/adminpanel.jsp">
					<i class="fas fa-box-open"></i> Dash Board
			</a></li>
			<li><a href="${pageContext.request.contextPath}/LogoutServlet"><i
					class="fas fa-user"></i> Log Out</a></li>
		</ul>
	</nav>
</header>
