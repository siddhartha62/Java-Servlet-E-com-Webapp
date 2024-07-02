
<%
String cookieUsername = null;
Cookie[] cookies = request.getCookies();

if (cookies != null) {
	for (Cookie cookie : cookies) {

		if (cookie.getName().equals("role")) {
	cookieUsername = cookie.getValue();
		}
	}
}
HttpSession userSession = request.getSession();
Integer notificationCount = (Integer) userSession.getAttribute("notificationCount"); // Retrieve notification count from session
if (notificationCount == null) {
	notificationCount = 0; // Initialize notification count if not found in session
}
%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheet/header.css">
<header class="navbar">
	<h1>Eclipse vision</h1>

	<div class="logo">
		<a href="${pageContext.request.contextPath}/HomeproductServlet"> <img
			src="${pageContext.request.contextPath}/photos/Eclipse Vision_transparent.png"
			alt="Eclipse Vision Logo" />
		</a>
	</div>
	<div class="search-bar">
		<form action="${pageContext.request.contextPath}/SearchServlet"
			method="post">
			<input type="text" placeholder="Search items..." name="search">
			<button type="submit">Search</button>
		</form>
	</div>
	<nav>
		<ul>
			<%
			if (cookieUsername != null) {
			%>
			<li><a
				href="${pageContext.request.contextPath}/HomeproductServlet">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/pages/about.jsp">About
					us</a></li>
			<li><a href="${pageContext.request.contextPath}/ProfileServlet">Profile</a></li>
			<li><a href="${pageContext.request.contextPath}/CartServlet"><i
					class="fas fa-shopping-cart"></i> Cart</a></li>
			<li><a href="${pageContext.request.contextPath}/LogoutServlet"><i
					class="fas fa-user"></i> Logout</a></li>
			<%
			} else {
			%>
			<li><a
				href="${pageContext.request.contextPath}/HomeproductServlet">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/pages/about.jsp">About
					us</a></li>
			<li><a href="${pageContext.request.contextPath}/pages/login.jsp"><i
					class="fas fa-user-plus"></i>LOGIN</a></li>
			<li><a
				href="${pageContext.request.contextPath}/pages/register.jsp">Register</a></li>
			<%
			}
			%>

		</ul>
	</nav>

</header>