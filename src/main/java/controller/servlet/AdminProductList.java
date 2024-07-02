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

/**
 * Servlet implementation class AdminProductList
 */

@WebServlet("/AdminProductList")
public class AdminProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetch all product details from the database
		List<ProductModel> products = dbController.getAllProducts();
		// Print Console Message
		System.out.println("\n Admin Product Page Loading....");

		// Set the list of products as an attribute in the request object
		request.setAttribute("products", products);

		// Forward the request to the home.jsp page
		request.getRequestDispatcher("/pages/adminproductlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
