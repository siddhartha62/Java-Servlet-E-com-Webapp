package util;

import java.io.File;

public class StringUtils {

	public static final String INSERT_USER = "INSERT INTO register"
			+ "(first_name, last_name, user_name,email, address, phone_number, password, image)"
			+ "VALUES (?, ?, ?, ?, ?,?,?,?)";

	public static final String UPDATE_USER_INFO = "UPDATE register SET first_name=?, "
			+ "last_name=?, user_name=?, address=?, phone_number=? WHERE email=?";

	public static final String GET_ALL_USER_INFO = "SELECT * FROM register";

	public static final String GET_USER_ID = "SELECT userId FROM register WHERE email = ?";

	public static final String GET_LOGIN_USER_INFO = "SELECT * FROM register WHERE email = ? ";

	public static final String GET_USER_USERNAME_INFO = "SELECT * FROM register WHERE user_name=?";

	public static final String GET_USER_EMAIL_INFO = "SELECT * FROM register WHERE email=?";

	public static final String GET_USER_PHONE_INFO = "SELECT * FROM register WHERE phone_number=?";

	public static final String GET_ADMIN_INFO = "SELECT role FROM register WHERE email = ?";

	public static final String IMAGE_DIR = "eclipse\\EclipseVision\\src\\main\\webapp\\resources\\images\\";
	public static final String IMAGE_DIR_PATH = "D:" + File.separator + IMAGE_DIR;

	public static final String INSERT_PRODUCT = "INSERT INTO product" + "(name,description,price,image,stock)"
			+ "VALUES (?, ?, ?, ?, ?)";

	public static final String UPDATE_PRODUCT_INFO = "UPDATE product SET name = ?, description = ?, price = ?, stock = ? WHERE product_id = ?";
	public static final String GET_ALL_PRODUCT_INFO = "SELECT * FROM product";
	public static final String GET_product_name = "SELECT * FROM product WHERE name = ?";
	public static final String GET_Product_BY_ID = "SELECT * FROM product WHERE product_id = ?";
	public static final String DELETE_PRODUCT = "DELETE FROM product WHERE product_id = ?";
	public static final String REGISTER_SERVLET = "/RegisterServlet";
	public static final String Search_Product = "SELECT * FROM product WHERE name LIKE ? OR price >= ?";

	public static final String INSERT_CART = "INSERT INTO cart (user_id, product_id, quantity) VALUES ((SELECT user_id FROM register WHERE email = ?), ?, 1);";
	public static final String CHECK_CART_ITEM = "SELECT * FROM cart WHERE user_id = (SELECT user_id FROM register WHERE email = ?) AND product_id = ? AND status ='unOrdered';";
	public static final String UPDATE_CART = "UPDATE cart SET quantity = ? WHERE user_id = (SELECT user_id FROM register WHERE email = ?) AND product_id = ? AND status ='unOrdered';";

	public static final String GET_CARTS = "SELECT c.cart_id, c.product_id, c.quantity, c.status, p.image, p.name, p.price, c.quantity * p.price as total_price FROM cart c JOIN product p ON c.product_id = p.product_id JOIN register r ON c.user_id = r.user_id WHERE r.email = ? AND c.status = 'unOrdered';";
	public static final String DELETE_CART_PRODUCT = "DELETE FROM cart WHERE cart_id = ?";

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	public static final String IMAGE = "image_url";
	public static final String STOCK = "stock";

	public static final String ADMIN_DASHBOARD_PAGE = "/pages/adminpanel.jsp";
	public static final String ADMIN_addDASHBOARD_PAGE = "/pages/productList.jsp";

	// image Directories
	public static final String SAVE_PATH = "\\eclipse\\EclipseVision\\src\\main\\webapp\\resources\\images";
	// Start Servlet Route
	public static final String ADD_PRODUCT_SERVLET = "/AddProductServlet";
	public static final String PRODUCT_DISPLAY_SERVLET = "/ProductDisplayServlet";
	public static final String ADMIN_PRODUCT_DISPLAY_SERVLET = "/AdminProductList";
	// End Servlet Route

	public static final String ADD_ORDER = "INSERT INTO orders (cart_id, order_date) VALUES (?, NOW())";
	public static final String UPDATE_QUANTITY = "UPDATE cart SET quantity = ?, status = 'isOrdered' WHERE cart_id = ?";
	public static final String GET_USERS_ORDERS = "SELECT p.name, c.quantity, p.price, c.quantity * p.price AS TotalPrice, o.order_date, o.order_status FROM orders o JOIN cart c ON o.cart_id = c.cart_id JOIN product p ON c.product_id = p.product_id JOIN register r ON c.user_id = r.user_id WHERE r.email = ?;";
	public static final String GET_ALL_ORDERS = "SELECT o.order_id,CONCAT(r.first_name, ' ', r.last_name) AS CustomerName, p.name, c.quantity, p.price, c.quantity * p.price AS TotalPrice, o.order_date, o.order_status FROM orders o JOIN cart c ON o.cart_id = c.cart_id JOIN product p ON c.product_id = p.product_id JOIN register r ON c.user_id = r.user_id;";
	public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET order_status = ? WHERE order_id = ?;";
	// End Parameter names
	public static final String Feedback_FOrm = "INSERT INTO feedback (name, email, message) VALUES (?, ?, ?)";

}
