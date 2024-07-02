package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.db.DatabaseController;

/**
 * Servlet implementation class AddtoCartServlet
 */
@WebServlet("/AddtoCartServlet")
public class AddtoCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddtoCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productId = request.getParameter("productId");

		// Print Console Message
		System.out.println("\n User: Adding Product to Cart...");
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			int result = dbController.addCart(productId, email);

			if (result > 0) {
				System.out.println("User: Successfully Added to Cart...");
				response.sendRedirect(request.getContextPath() + "/CartServlet");
			} else {
				// Send response based on result
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				out.println("Failed to add product to cart.");
			}
		} else {
			request.setAttribute("errorMessage", "Login the User For Adding items to Cart");
	        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
