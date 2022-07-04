package bookstore.service;

import bookstore.pojo.User;

public interface UserService {
    User login(String uname, String pwd);
    void register(User user);
    User getUser(String uname);
}
