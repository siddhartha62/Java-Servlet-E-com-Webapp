package controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.db.DatabaseController;
import model.ProductModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/HomeproductServlet" })
public class HomeproductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	public HomeproductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Fetch all product details from the database
		List<ProductModel> products = dbController.getAllProducts();
		// Print parameters
		System.out.println("Homepage Loading....");

		// Set the list of products as an attribute in the request object
		request.setAttribute("products", products);

		// Forward the request to the home.jsp page
		request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
	}
}