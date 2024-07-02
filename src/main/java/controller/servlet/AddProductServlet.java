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
import model.ProductModel;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.ADD_PRODUCT_SERVLET })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB)

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController dbController = new DatabaseController();

	public AddProductServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Printing parameters
		String name = request.getParameter(StringUtils.NAME);
		String description = request.getParameter(StringUtils.DESCRIPTION);
		int price = Integer.parseInt(request.getParameter(StringUtils.PRICE));
		Part imagePart = request.getPart(StringUtils.IMAGE);
		int stock = Integer.parseInt(request.getParameter(StringUtils.STOCK));

		// Print parameters
		System.out.println("Admin HomePage Loading....");

		// Assuming you have a constructor in ProductModel that accepts all these
		// parameters
		ProductModel productModel = new ProductModel(name, description, price, imagePart, stock);
		int result = dbController.addProduct(productModel);
		String savePath = StringUtils.IMAGE_DIR_PATH;
		String fileName = productModel.getImage();

		if (!fileName.isEmpty() && fileName != null) {
			imagePart.write(savePath + fileName);
		}

		if (result == 1) {
			response.sendRedirect(request.getContextPath() + "/pages/adminpanel.jsp");
			/*
			 * request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
			 */
		} else {
			// Error message
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println("Failed to add product!");
		}
	}

}