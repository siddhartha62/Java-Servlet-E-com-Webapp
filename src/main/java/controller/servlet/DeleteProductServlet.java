package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.db.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteProductServlet" })
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isAdminDelete = request.getParameter("isAdminDelete");
		String isCartDelete = request.getParameter("isCartDelete");
		if ("yes".equals(isAdminDelete)) {
			String AdminproductId = request.getParameter("productId");
			if (AdminproductId == null || AdminproductId.isEmpty()) {
				// Handle case where productId is missing or empty
				response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_PRODUCT_DISPLAY_SERVLET
						+ "?error=missingProductId");
				return;
			}
			try {
				int result = dbController.deleteProductAdminInfo(AdminproductId);
				if (result == 1) {
					// Product successfully deleted
					System.out.println("\n Admin: Successfully Deleted Product");
					response.sendRedirect(
							request.getContextPath() + StringUtils.ADMIN_PRODUCT_DISPLAY_SERVLET + "?success=true");
				} else {
					// Product not found or deletion failed
					response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_PRODUCT_DISPLAY_SERVLET
							+ "?error=deleteFailed");
				}
			} catch (Exception e) {
				// Log the exception
				e.printStackTrace();
				// Redirect with error message
				response.sendRedirect(
						request.getContextPath() + StringUtils.ADMIN_PRODUCT_DISPLAY_SERVLET + "?error=serverError");
			}
		} else if ("yes".equals(isCartDelete)) {
			// cart delete
			String cartId = request.getParameter("cartId");
			if (cartId == null || cartId.isEmpty()) {
				// Handle case where productId is missing or empty
				response.sendRedirect(request.getContextPath() + "/CartServlet?error=missingcartId");
				return;
			}
			try {
				int result = dbController.deleteCart(cartId);
				if (result == 1) {
					// Product successfully deleted
					System.out.println("\n User: Successfully Deleted Product From Cart");
					response.sendRedirect(request.getContextPath() + "/CartServlet");
				} else {
					// Product not found or deletion failed
					response.sendRedirect(request.getContextPath() + "/CartServlet?error=deleteFailed");
				}
			} catch (Exception e) {
				// Log the exception
				e.printStackTrace();
				// Redirect with error message
				response.sendRedirect(request.getContextPath() + "/CartServlet?error=serverError");
			}
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