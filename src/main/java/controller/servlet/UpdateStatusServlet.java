package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.db.DatabaseController;

/**
 * Servlet implementation class UpdateStatusServlet
 */
@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateStatusServlet() {
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
		String orderId = request.getParameter("orderId");
		String newStatus = request.getParameter("orderStatus");

		int result = dbController.updateOrderStatus(orderId, newStatus);

		if (result > 0) {
			// If the update was successful, redirect to a success page
			System.out.println("\n Admin: Order Status updated Successfully");
			response.sendRedirect(request.getContextPath()+"/AllOrders");
		} else {
			// If the update failed, redirect to an error page
			System.out.println("\n Admin: Order Status Failed To updates");
			response.sendRedirect(request.getContextPath()+"/AllOrders");
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
