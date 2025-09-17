package LTW3.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role") // đúng với bảng trong DB
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleID") // trùng với cột trong DB
	private Integer roleId;

	@Column(nullable = false, unique = true, length = 50)
	private String name; // ADMIN, USER, MANAGER ...

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> users;

	// ===== Constructors =====
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	// ===== Getter & Setter =====
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
