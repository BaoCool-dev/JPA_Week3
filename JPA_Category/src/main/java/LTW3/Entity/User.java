package LTW3.Entity;

import jakarta.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "Users") // Tên bảng trong database
public class User implements Serializable {
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tự tăng nếu DB hỗ trợ (SQL Server AUTO_INCREMENT)
	@Column(name = "userID")
	private int userID;

	@Column(name = "userName", nullable = false, unique = true, length = 50)
	private String userName;

	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;

	@Column(name = "fullName", length = 100)
	private String fullName;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "images", length = 255)
	private String images;

	@Column(name = "phone", length = 15)
	private String phone;

	@Column(name = "status")
	private int status;

	@Column(name = "code", length = 50)
	private String code;

	@Column(name = "roleID")
	private int roleID;

	@Column(name = "sellerID")
	private int sellerID;

	public User() {
	}

	public User(int userID, String userName, String email, String fullName, String password, String images,
			String phone, int status, String code, int roleID, int sellerID) {
		this.userID = userID;
		this.userName = userName;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.images = images;
		this.phone = phone;
		this.status = status;
		this.code = code;
		this.roleID = roleID;
		this.sellerID = sellerID;
	}

	public User(String userName, String email, String fullName, String password, int status, String code, int roleID) {
		super();
		this.userName = userName;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.status = status;
		this.code = code;
		this.roleID = roleID;
	}

	public User(String userName, String email, String fullName, String code) {
		super();
		this.userName = userName;
		this.email = email;
		this.fullName = fullName;
		this.code = code;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() { // sửa lại tên method chuẩn JavaBean
		return password;
	}

	public void setPassword(String password) { // sửa lại tên method chuẩn JavaBean
		this.password = password;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public int getSellerID() {
		return sellerID;
	}

	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}
}
