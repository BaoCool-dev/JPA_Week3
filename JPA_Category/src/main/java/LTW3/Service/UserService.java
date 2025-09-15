package LTW3.Service;

import java.util.List;


import LTW3.Entity.User;

public interface UserService {
	User login(String userName, String password);

	User get(String userName);

	void insert(User user);

	boolean register(String username, String password, String email, String fullName, String phone);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);

	void assignRole(int userId, int roleId);

	void removeRole(int userId);
	User findById(int userId);
	List<User> findAll(); 

}
