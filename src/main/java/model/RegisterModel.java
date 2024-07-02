package model;

import java.io.File;

import javax.servlet.http.Part;

public class RegisterModel {
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String address;
	private String phoneNumber;
	private String password;
	private String ImageUrlFromPart;

	public RegisterModel(String firstName, String lastName, String userName, String email, String address,
			String phoneNumber, Part imagePart, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.ImageUrlFromPart = getImageUrl(imagePart);
	}

	public RegisterModel() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageUrlFromPart() {
		return ImageUrlFromPart;
	}

	public void setImageUrlFromPart(String ImageUrlFromPart) {
		this.ImageUrlFromPart = ImageUrlFromPart;
	}

	private String getImageUrl(Part imagePart) {
		String savePath = "/home/arch/eclipse-workspace/EclipseVision/src/main/webapp/resources/images";
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
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
			imageUrlFromPart = "default.jpg";
		}
		return imageUrlFromPart;
	}

}
