package LTW3.Service.Impl;

import LTW3.Dao.UserDao;
import LTW3.Entity.AppUser;
import LTW3.Service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(AppUser user) {
        userDao.saveUser(user);
    }

    @Override
    public AppUser getUser(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUser(AppUser user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
}
