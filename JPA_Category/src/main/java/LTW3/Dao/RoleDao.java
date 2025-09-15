package LTW3.Dao;

import java.util.List;

import LTW3.Entity.Role;

public interface RoleDao {
	Role findById(int roleId); // tìm role theo id

	Role findByName(String name); // tìm role theo tên

	List<Role> findAll(); // lấy tất cả role

	void insert(Role role); // thêm role

	void update(Role role); // cập nhật role

	void delete(int roleId); // xóa role
}
