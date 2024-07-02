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

/**
 * Servlet implementation class ProfileUpdateServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = "/ProfileUpdateServlet")
public class ProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	public ProfileUpdateServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPut(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");

			// Retrieve form parameters
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String userName = request.getParameter("username");
			String address = request.getParameter("Address");
			String phoneNumber = request.getParameter("phonenumber");

			// Create a new RegisterModel instance
			RegisterModel profile = new RegisterModel();
			profile.setFirstName(firstName);
			profile.setLastName(lastName);
			profile.setUserName(userName);
			profile.setEmail(email);
			profile.setAddress(address);
			profile.setPhoneNumber(phoneNumber);

			// Update profile in the database
			boolean success = dbController.updateProfile(profile);

			if (success) {
				// Redirect to a success page
				response.sendRedirect(request.getContextPath() + "/ProfileServlet");
			} else {
				// Redirect to an error page
				System.out.println("Error on updating Profile");
				response.sendRedirect(request.getContextPath() + "/ProfileServlet");
			}
		} else {
			// Handle case where user is not logged in
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}
}
