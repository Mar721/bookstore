package bookstore.service;

import bookstore.pojo.Order;
import bookstore.pojo.User;

import java.util.List;

public interface OrderService {
    //这个是结账方法
    void addOrder(Order order);

    List<Order> getOrderList(User user);
}
