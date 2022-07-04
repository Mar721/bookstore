package bookstore.dao;

import bookstore.pojo.Order;
import bookstore.pojo.User;

import java.util.List;

public interface OrderDao {
    void addOrder(Order order);
    void delOrder(Order order);

    List<Order> getOrderList(User user);
    //查询订单中书本总数量，找到更好的办法，这个方法弃用，新方法在orderItem中
//    Integer getOrderTotalBookCount(Order order);
}
