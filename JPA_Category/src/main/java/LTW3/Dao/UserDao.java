package LTW3.Dao;

import LTW3.Entity.User;

public interface UserDao {
	User get(String userName);

	boolean insert(User user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);

	boolean sendEmail(User user);

	void updateStatus(User user);

	void insertRegister(User user);

}
