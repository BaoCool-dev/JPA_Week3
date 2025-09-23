package LTW3.Dao;

import LTW3.Entity.AppUser;

public interface UserDao {

	void deleteUser(int id);

	void updateUser(AppUser user);

	AppUser getUserById(int id);

	void saveUser(AppUser user);
	
	}

