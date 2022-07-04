package bookstore.dao;

import bookstore.pojo.Order;
import bookstore.pojo.OrderItem;

public interface OrderItemDao {
    void addOrderItem(OrderItem orderItem);
    Integer getOrderTotalBookCount(Order order);
}
