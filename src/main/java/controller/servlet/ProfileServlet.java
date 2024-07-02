package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.db.DatabaseController;
import model.RegisterModel;
import model.OrderModel;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");

			// Assuming you have a method to retrieve profile details based on the username
			List<OrderModel> orders = dbController.getUserOrders(email);
			RegisterModel profile = dbController.getProfileByEmail(email);
			if (profile != null) {
				request.setAttribute("orders", orders);
				request.setAttribute("profile", profile);
				System.out.println("\n Profile Page Loading....");
				request.getRequestDispatcher("/pages/profile.jsp").forward(request, response);
			} else {
				// Handle case where profile is not found
				request.setAttribute("errorMessage", "Profile not found");
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
			}
		} else {
			// Handle case where user is not logged in
			request.setAttribute("errorMessage", "You are not logged in");
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
