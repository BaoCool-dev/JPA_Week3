package LTW3.Dao;

import java.util.List;

import LTW3.Entity.User;

public interface UserDao {
	User get(String userName);

	boolean insert(User user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);

	void updateStatus(User user);

	void insertRegister(User user);

	User findById(int userId);

	void update(User user);

	List<User> findAll();

}
