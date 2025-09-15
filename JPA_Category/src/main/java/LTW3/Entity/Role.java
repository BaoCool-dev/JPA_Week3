package LTW3.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;

	@Column(nullable = false, unique = true, length = 50)
	private String name; // ADMIN, USER, SELLER ...

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> users;

	// ===== Constructors =====
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	// ===== Getter & Setter =====
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	// ===== Thêm alias để controller dễ gọi =====
	public int getRoleId() {
		return (id != null) ? id : 0;
	}
}
