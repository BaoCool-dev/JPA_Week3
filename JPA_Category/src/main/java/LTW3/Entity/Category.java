package LTW3.Entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
@NamedQueries({ @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"), })
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId")
	private int categoryId;

	@Column(name = "categoryCode", columnDefinition = "NVARCHAR(255)")
	private String categoryCode;

	@Column(name = "categoryName", columnDefinition = "NVARCHAR(255)")
	private String categoryName;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String images;

	@Column(name = "status", columnDefinition = "bit")
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = true) // hoặc false nếu bắt buộc phải có User
	private User user;

	@Column(name = "userID", insertable = false, updatable = false)
	private Integer userID; // Đổi int -> Integer




	public Category() {
		super();
	}

	public Category(int categoryId, String categoryCode, String categoryName, String images, boolean status) {
		super();
		this.categoryId = categoryId;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.images = images;
		this.status = status;
	}

	// ===== GETTER & SETTER =====
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	// === Thêm Getter & Setter cho user ===
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserID() {
		return userID;
	}

	// Không tạo setter cho userID vì JPA sẽ tự set từ quan hệ @ManyToOne
}
