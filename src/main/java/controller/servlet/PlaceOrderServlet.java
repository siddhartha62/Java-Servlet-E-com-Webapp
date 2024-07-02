package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.db.DatabaseController;

/**
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlaceOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cartId = request.getParameter("cartId");
		String quantity = request.getParameter("quantity");

		try {
			int result = dbController.addOrder(cartId, quantity);

			if (result == 1) {
				System.out.println("\n User: Order Success");
				request.getRequestDispatcher("/CartServlet").forward(request, response);
			} else {
				System.out.println("\n User: Error on Ordering");
				request.getRequestDispatcher("/CartServlet").forward(request, response);
			}
		} catch (Exception ex) {
			// Exception occurred, forward to an error page
			System.out.println("\n User: Error on Ordering");
			request.getRequestDispatcher("/CartServlet").forward(request, response);
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
