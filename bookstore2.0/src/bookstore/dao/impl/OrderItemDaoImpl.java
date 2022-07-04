package bookstore.dao.impl;

import bookstore.dao.OrderItemDao;
import bookstore.pojo.Order;
import bookstore.pojo.OrderItem;
import com.myssm.basedao.BaseDAO;

import java.math.BigDecimal;

public class OrderItemDaoImpl extends BaseDAO<OrderItem> implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item(book,buyCount,orderBean)values(?,?,?);",
                orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrder().getId());
    }

    public Integer getOrderTotalBookCount(Order order) {
        String sql = "select sum(buyCount) as totalBookCount, orderBean" +
                " from t_order_item where orderBean=? GROUP BY orderBean;" ;
        return ((BigDecimal)executeComplexQuery(sql,order.getId())[0]).intValue();
    }
}
