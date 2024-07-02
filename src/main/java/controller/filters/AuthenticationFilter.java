/*
 * package controller.filters;
 * 
 * import java.io.IOException;
 * 
 * import javax.servlet.Filter; import javax.servlet.FilterChain; import
 * javax.servlet.FilterConfig; import javax.servlet.ServletException; import
 * javax.servlet.ServletRequest; import javax.servlet.ServletResponse; import
 * javax.servlet.annotation.WebFilter; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * //@WebFilter("/*") public class AuthenticationFilter implements Filter {
 * 
 * @Override public void destroy() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void doFilter(ServletRequest request, ServletResponse
 * response, FilterChain chain) throws IOException, ServletException {
 * HttpServletRequest req = (HttpServletRequest) request; HttpServletResponse
 * res = (HttpServletResponse) response;
 * 
 * 
 * 
 * String uri = req.getRequestURI();
 * 
 * boolean isLogin = uri.endsWith("login.jsp"); boolean isRegister =
 * uri.endsWith("register.jsp"); boolean isProduct =
 * uri.endsWith("addproduct.jsp"); boolean isLoginServlet =
 * uri.endsWith("LoginServlet"); boolean isLogoutServlet =
 * uri.endsWith("LogoutServlet"); boolean isProductServlet =
 * uri.endsWith("ProductServlet");
 * 
 * HttpSession session = req.getSession(false); boolean isLoggedIn = session !=
 * null && session.getAttribute("email") != null; String userRole = (String)
 * session.getAttribute("role");
 * 
 * if(!isLoggedIn && (isLogin || isLoginServlet || isRegister)) {
 * chain.doFilter(request, response); } else if (isLoggedIn &&
 * "user".equals(userRole) && isLogoutServlet){
 * res.sendRedirect(req.getContextPath() + "/pages/home.jsp"); } else if
 * (isLoggedIn && "admin".equals(userRole) && isLogoutServlet) {
 * res.sendRedirect(req.getContextPath() + "/pages/adminpanel.jsp"); }
 * 
 * else { chain.doFilter(request, response); } }
 * 
 * @Override public void init(FilterConfig arg0) throws ServletException { //
 * TODO Auto-generated method stub
 * 
 * }
 * 
 * }
 */