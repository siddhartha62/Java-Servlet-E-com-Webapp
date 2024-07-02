package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.db.DatabaseController;
import model.AddToCartModel;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
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

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");

			List<AddToCartModel> cartss = dbController.getAllCarts(email);
			// Print Console Message
			System.out.println("\n User: Cart Page Page Loading....");
			// Set the list of products as an attribute in the request object
			request.setAttribute("carts", cartss);

			// Forward the request to the home.jsp page
			request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Login the User For Viewing to Cart");
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
