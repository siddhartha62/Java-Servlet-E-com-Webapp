package model;
import java.io.File;

import javax.servlet.http.Part;

public class ProductModel {
	private int ProductId;
	private String name;
    private String description;
    private int price;
    private String image;
    private int stock;

    public ProductModel(String name, String description, int price, Part image, int stock) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = this.getImageString(image);
		this.stock = stock;
	}
    public ProductModel(){
    	
    }
	// Getters and setters

	
    public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	private String getImageString(Part imagePart) {
		String savePath = util.StringUtils.SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (imagePart == null){
			return null;
		}
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = imagePart.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			return "a";
		}
		return imageUrlFromPart;
	}
}