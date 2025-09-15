package LTW3.Entity;

import jakarta.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "Users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	private int userId; 

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
	private boolean status;

	@Column(name = "code", length = 50)
	private String code;

	@Column(name = "roleID")
	private int roleID;

	

	@ManyToOne
	@JoinColumn(name = "roleID", insertable = false, updatable = false)
	private Role role;

	public User() {
	}

	public User(int userId, String userName, String email, String fullName, String password, String images,
			String phone, boolean status, String code, int roleID) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.images = images;
		this.phone = phone;
		this.status = status;
		this.code = code;
		this.roleID = roleID;
	}

	public User(String userName, String email, String fullName, String password, boolean status, String code, int roleID) {
		this.userName = userName;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.status = status;
		this.code = code;
		this.roleID = roleID;
	}

	public User(String userName, String email, String fullName, String code) {
		this.userName = userName;
		this.email = email;
		this.fullName = fullName;
		this.code = code;
	}

	// ===== Getter & Setter =====
	public int getUserId() { // ✅ chuẩn camelCase
		return userId;
	}

	public void setUserId(int userId) { // ✅ chuẩn camelCase
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean i) {
		this.status = i;
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


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
