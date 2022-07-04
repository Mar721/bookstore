package bookstore.dao;

import bookstore.pojo.User;

public interface UserDao {
    User login(String uname, String pwd);
    void addUser(User user);
    User getUser(String uname);
}
