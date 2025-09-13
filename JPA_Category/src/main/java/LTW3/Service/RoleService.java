package LTW3.Service;

import java.util.List;

import LTW3.Entity.Role;

public interface RoleService {
	  Role findByName(String name);

	List<Role> findAll();
}
