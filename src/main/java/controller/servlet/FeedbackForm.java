package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.db.DatabaseController;

/**
 * Servlet implementation class FeedbackForm
 */
@WebServlet("/FeedbackForm")
public class FeedbackForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FeedbackForm() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");

		boolean success = dbController.saveFeedback(name, email, message);

		if (success) {
			System.out.println("\n User: Feedback Done");
			response.sendRedirect(request.getContextPath()+"/pages/about.jsp");
		} else {
			System.out.println("\n User: Feedback Not Submitted");
			response.sendRedirect(request.getContextPath()+"/pages/about.jsp");
		}
	}
}
