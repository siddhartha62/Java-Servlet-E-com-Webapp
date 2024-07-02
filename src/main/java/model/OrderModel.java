package model;


public class OrderModel {
	private int orderId;
	private String customer;
	private String productName;
	private String orderDate;
	private int quantity;
	private int Price;
	private int totalPrice;
	private String status;

	public OrderModel(int orderId, String customer, String productName, String orderDate, int quantity,
			int totalPrice, String status,int Price) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.productName = productName;
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.Price = totalPrice;
		this.status = status;
	}

	public OrderModel() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
