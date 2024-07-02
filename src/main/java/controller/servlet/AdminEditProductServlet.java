package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.db.DatabaseController;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class AdminEditProductServlet
 */
@WebServlet("/AdminEditProductServlet")
public class AdminEditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminEditProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String AdminproductId = request.getParameter("productId");
		// Print Console Message
		System.out.println("\n Admin: Edit Page Loading....");

		ProductModel products = dbController.getUPProductDetails(AdminproductId);

		// Set the list of products as an attribute in the request object
		request.setAttribute("products", products);

		// Forward the request to the home.jsp page
		request.getRequestDispatcher("/pages/AdminEditProduct.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPut(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String AdminproductId = request.getParameter("productId");
		String name = request.getParameter(StringUtils.NAME);
		String description = request.getParameter(StringUtils.DESCRIPTION);
		int price = Integer.parseInt(request.getParameter(StringUtils.PRICE));
		int stock = Integer.parseInt(request.getParameter(StringUtils.STOCK));

		ProductModel productModel = new ProductModel();

		productModel.setName(name);
		productModel.setDescription(description);
		productModel.setPrice(price);
		productModel.setStock(stock);

		int result = dbController.updateProductdetails(productModel, AdminproductId);

		if (result == 1) {
			response.sendRedirect(request.getContextPath() + "/AdminProductList");
		} else {
			// Error message
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println("Failed to add product!");
		}
	}

}
