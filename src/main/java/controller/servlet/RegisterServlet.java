package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.db.DatabaseController;
import model.RegisterModel;
import util.StringUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtils.REGISTER_SERVLET)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		PrintWriter printOut = response.getWriter();
		response.setContentType("text/html");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter printOut = response.getWriter();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		Part imagePart = request.getPart("image");

		RegisterModel registerModel = new RegisterModel(firstName, lastName, userName, email, address, phoneNumber,
				imagePart, password);

		String savePath = StringUtils.IMAGE_DIR_PATH;
		String fileName = registerModel.getImageUrlFromPart();

		if (!fileName.isEmpty() && fileName != null) {
			imagePart.write(savePath + fileName);
		}

		int result = dbController.addUser(registerModel);

		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
		} else {
			printOut.println("<p>Registration failed.Please try again</p>");
		}

		if (!isValidName(firstName) || !isValidName(lastName)) {
			printOut.println("Please enter the correct first name and last name.");
			return;
		}

		if (!isValidUsername(userName)) {
			printOut.println("Please enter the correct username without any special character");
			return;
		}
		if (!isValidPhoneNumber(phoneNumber)) {
			printOut.println("Please enter the correct phone number.Must contain 10 digits and start with 9.");
			return;
		}
		if (!isValidPassword(password)) {
			printOut.println(
					"Please enter the valid password wirh one special character, one capital letter and a number.");
		}

	}

	private boolean isValidName(String name) {
		return name.matches("[A-Z][a-zA-Z]+");
	}

	private boolean isValidUsername(String username) {
		return username.matches("[a-zA-Z0-9]+");
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber.matches("9[0-9]{9}");
	}

	private boolean isValidPassword(String password) {
		return password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
	}

}
