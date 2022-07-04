package bookstore.service.impl;

import bookstore.dao.UserDao;
import bookstore.pojo.User;
import bookstore.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Override
    public User login(String uname, String pwd) {
        return userDao.login(uname,pwd);
    }

    @Override
    public void register(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUser(String uname) {
        return userDao.getUser(uname);
    }
}
