package controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.AddToCartModel;
import model.OrderModel;
import model.PasswordEncryptionWIthAes;
import model.ProductModel;
import model.RegisterModel;
import util.StringUtils;

public class DatabaseController {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/eclipse_vision";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
	}

	public int addUser(RegisterModel RegisterModel) {
		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(StringUtils.INSERT_USER)) {

			String encryptedPassword = PasswordEncryptionWIthAes.encryptPassword(RegisterModel.getPassword(),
					"U3CdwubLD5yQbUOG92ZnHw==");

			st.setString(1, RegisterModel.getFirstName());
			st.setString(2, RegisterModel.getLastName());
			st.setString(3, RegisterModel.getUserName());
			st.setString(4, RegisterModel.getEmail());
			st.setString(5, RegisterModel.getAddress());
			st.setString(6, RegisterModel.getPhoneNumber());
			st.setString(7, encryptedPassword);
			st.setString(8, RegisterModel.getImageUrlFromPart());

			return st.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public String getUserRole(String email, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, email);

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return rs.getString("role");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getUserLoginInfo(String email, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
			st.setString(1, email);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String userDb = rs.getString("email");
				String encryptedPassword = rs.getString("password");

				// Decrypt password from database and compare
				String decryptedPassword = PasswordEncryptionWIthAes.decryptPassword(encryptedPassword,
						"U3CdwubLD5yQbUOG92ZnHw==");

				if (decryptedPassword != null && userDb.equals(email) && decryptedPassword.equals(password)) {
					return 1; // Login successful
				} else {
					return 0; // Password mismatch
				}
			} else {
				// No matching record found
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean isUserNameExists(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USER_USERNAME_INFO);
			st.setString(1, username);

			ResultSet rs = st.executeQuery();

			return rs.next();

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return false;

		}
	}

	public boolean isPhoneNumber(String phoneNumber) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USER_PHONE_INFO);
			st.setString(1, phoneNumber);

			ResultSet rs = st.executeQuery();

			return rs.next();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isEmailExists(String email) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USER_EMAIL_INFO);
			st.setString(1, email);

			ResultSet rs = st.executeQuery();

			return rs.next();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public RegisterModel getProfileByEmail(String email) {
		RegisterModel profile = null;
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO)) {
			st.setString(1, email);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String userName = rs.getString("user_name");
					String address = rs.getString("address");
					String phoneNumber = rs.getString("phone_number");
					String imageUrl = rs.getString("image");

					profile = new RegisterModel();
					profile.setFirstName(firstName);
					profile.setLastName(lastName);
					profile.setUserName(userName);
					profile.setEmail(email);
					profile.setAddress(address);
					profile.setPhoneNumber(phoneNumber);
					profile.setImageUrlFromPart(imageUrl);
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("error retrieving server");
			ex.printStackTrace();
		}
		return profile;
	}

	public boolean updateProfile(RegisterModel profile) {
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(StringUtils.UPDATE_USER_INFO)) {
			stmt.setString(1, profile.getFirstName());
			stmt.setString(2, profile.getLastName());
			stmt.setString(3, profile.getUserName());
			stmt.setString(4, profile.getAddress());
			stmt.setString(5, profile.getPhoneNumber());
			stmt.setString(6, profile.getEmail());

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("\n User Details Updated:");
				return true;
			} else {
				System.out.println("\n No User Updated:");
				return false;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Error updating profile: " + ex.getMessage());
			return false;
		}
	}

	public int addProduct(ProductModel productModel) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.INSERT_PRODUCT)) {

			st.setString(1, productModel.getName());
			st.setString(2, productModel.getDescription());
			st.setInt(3, productModel.getPrice());
			st.setString(4, productModel.getImage());
			st.setInt(5, productModel.getStock());
			return st.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public List<ProductModel> getAllProducts() {
		List<ProductModel> products = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_PRODUCT_INFO);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductId(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getInt("price"));
				products.add(product);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return products;
	}

	public List<ProductModel> searchProducts(String searchData) {
		List<ProductModel> products = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.Search_Product)) {
			if (searchData.matches("\\d+")) {
				double price = Double.parseDouble(searchData);
				st.setString(1, "");
				st.setDouble(2, price);
			} else {
				st.setString(1, "%" + searchData + "%");
				st.setNull(2, Types.INTEGER);
			}
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					ProductModel product = new ProductModel();
					product.setProductId(rs.getInt("product_id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setImage(rs.getString("image"));
					product.setStock(rs.getInt("stock"));
					product.setPrice(rs.getInt("price"));
					products.add(product);
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return products;
	}

	public List<ProductModel> getProductDetails(String productName) {
		List<ProductModel> products = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.GET_product_name)) {

			st.setString(1, productName);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					ProductModel product = new ProductModel();
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setStock(rs.getInt("stock"));
					product.setPrice(rs.getInt("price"));
					product.setImage(rs.getString("image"));
					products.add(product);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return products;
	}

	public ProductModel getUPProductDetails(String AdminproductId) {
		ProductModel product = null;
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.GET_Product_BY_ID)) {
			st.setString(1, AdminproductId);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					product = new ProductModel();
					product.setProductId(rs.getInt("product_id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setImage(rs.getString("image"));
					product.setStock(rs.getInt("stock"));
					product.setPrice(rs.getInt("price"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return product;
	}

	public int updateProductdetails(ProductModel productModel, String PId) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_PRODUCT_INFO)) {
			st.setString(1, productModel.getName());
			st.setString(2, productModel.getDescription());
			st.setInt(3, productModel.getPrice());
			st.setInt(4, productModel.getStock());
			st.setString(5, PId);

			int rowsUpdated = st.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Product updated successfully.");
			} else {
				System.out.println("Failed to update product.");
			}
			return rowsUpdated;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("An error occurred while updating the product.");
			return 0;
		}
	}

	public int deleteProductAdminInfo(String ProductId) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_PRODUCT);
			st.setString(1, ProductId);

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0; // Return 1 if deletion is successful, otherwise return 0
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1; // Return -1 for any exceptions
		}
	}

	public int addCart(String AdminproductId, String email) {
		try (Connection con = getConnection();
				PreparedStatement checkStmt = con.prepareStatement(StringUtils.CHECK_CART_ITEM);
				PreparedStatement insertStmt = con.prepareStatement(StringUtils.INSERT_CART);
				PreparedStatement updateStmt = con.prepareStatement(StringUtils.UPDATE_CART)) {

			// Check if the product already exists in the cart
			checkStmt.setString(1, email);
			checkStmt.setString(2, AdminproductId);
			try (ResultSet rs = checkStmt.executeQuery()) {
				if (rs.next()) {
					// Product already exists, update the quantity
					int currentQuantity = rs.getInt("quantity");
					updateStmt.setInt(1, currentQuantity + 1);
					updateStmt.setString(2, email);
					updateStmt.setString(3, AdminproductId);
					return updateStmt.executeUpdate();
				} else {
					// Product doesn't exist, add a new cart item
					insertStmt.setString(1, email);
					insertStmt.setString(2, AdminproductId);
					return insertStmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public List<AddToCartModel> getAllCarts(String email) {
		List<AddToCartModel> cartss = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(StringUtils.GET_CARTS)) {

			st.setString(1, email);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					AddToCartModel cart = new AddToCartModel();
					cart.setCartId(rs.getInt("cart_id"));
					cart.setProductId(rs.getInt("product_id"));
					cart.setQuantity(rs.getInt("quantity"));
					cart.setStatus(rs.getString("status"));
					cart.setImage(rs.getString("image"));
					cart.setProductName(rs.getString("name"));
					cart.setProductPrice(rs.getInt("price"));
					cart.setTotalPrice(rs.getInt("total_price"));
					cartss.add(cart);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return cartss;
	}

	public int deleteCart(String cartId) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_CART_PRODUCT);
			st.setString(1, cartId);

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0; // Return 1 if deletion is successful, otherwise return 0
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1; // Return -1 for any exceptions
		}
	}

	public int addOrder(String cartId, String quantity) {
		try (Connection con = getConnection()) {
			// Update the quantity on the cart
			PreparedStatement updateSt = con.prepareStatement(StringUtils.UPDATE_QUANTITY);
			updateSt.setString(1, quantity);
			updateSt.setString(2, cartId);
			int updateResult = updateSt.executeUpdate();

			// If the quantity update is successful, proceed to add the order
			if (updateResult > 0) {
				PreparedStatement addOrderSt = con.prepareStatement(StringUtils.ADD_ORDER);
				addOrderSt.setString(1, cartId);
				int addOrderResult = addOrderSt.executeUpdate();

				return addOrderResult > 0 ? 1 : 0; // Return 1 if insertion is successful, otherwise return 0
			} else {
				return 0; // Return 0 if the quantity update fails
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1; // Return -1 for any exceptions
		}
	}

	public List<OrderModel> getUserOrders(String email) {
		List<OrderModel> orders = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.GET_USERS_ORDERS)) {
			// Set the user ID parameter in the prepared statement
			st.setString(1, email);

			// Execute the query
			try (ResultSet rs = st.executeQuery()) {
				// Iterate through the result set
				while (rs.next()) {
					// Create a new OrderModel object for each row
					OrderModel order = new OrderModel();
					// Set the attributes of the OrderModel object using data from the result set
					order.setProductName(rs.getString("name"));
					order.setQuantity(rs.getInt("quantity"));
					order.setPrice(rs.getInt("price"));
					order.setTotalPrice(rs.getInt("TotalPrice"));
					order.setOrderDate(rs.getString("order_date"));
					order.setStatus(rs.getString("order_status"));

					// Add the OrderModel object to the list
					orders.add(order);
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Handle any SQL or class loading exceptions
			ex.printStackTrace(); // Log the exception for debugging
		}
		// Return the list of OrderModel objects
		return orders;
	}

	public List<OrderModel> getAllOrders() {
		List<OrderModel> orders = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_ORDERS)) {
			// Execute the query
			try (ResultSet rs = st.executeQuery()) {
				// Iterate through the result set
				while (rs.next()) {
					// Create a new OrderModel object for each row
					OrderModel order = new OrderModel();
					// Set the attributes of the OrderModel object using data from the result set
					order.setOrderId(rs.getInt("order_id"));
					order.setCustomer(rs.getString("CustomerName"));
					order.setProductName(rs.getString("name"));
					order.setQuantity(rs.getInt("quantity"));
					order.setPrice(rs.getInt("price"));
					order.setTotalPrice(rs.getInt("TotalPrice"));
					order.setOrderDate(rs.getString("order_date"));
					order.setStatus(rs.getString("order_status"));

					// Add the OrderModel object to the list
					orders.add(order);
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Handle any SQL or class loading exceptions
			ex.printStackTrace(); // Log the exception for debugging
		}
		// Return the list of OrderModel objects
		return orders;
	}

	public int updateOrderStatus(String orderId, String newStatus) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_ORDER_STATUS);
			st.setString(1, newStatus);
			st.setString(2, orderId);
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0; // Return 1 if update is successful, otherwise return 0
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1; // Return -1 for any exceptions
		}
	}

	public boolean saveFeedback(String name, String email, String message) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.Feedback_FOrm);
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, message);

			int result = st.executeUpdate();
			return result > 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
