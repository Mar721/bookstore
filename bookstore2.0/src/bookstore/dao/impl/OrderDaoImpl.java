package bookstore.dao.impl;

import bookstore.dao.OrderDao;
import bookstore.pojo.Order;
import bookstore.pojo.User;
import com.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDaoImpl extends BaseDAO<Order> implements OrderDao {
    @Override
    public void addOrder(Order order) {
        Integer id=super.executeUpdate("insert into t_order(orderNo,orderDate,orderUser,orderMoney,orderStatus)values(?,?,?,?,?);",
                order.getOrderNo(),order.getOrderDate(),order.getOrderUser().getId(),order.getOrderMoney(),order.getOrderStatus());
        //返回插入的行数，即id,参数order是没有id值的，所以在下面赋值，引用类型传的是地址值，所以可以修改
        order.setId(id);
    }

    @Override
    public void delOrder(Order order) {
        super.executeUpdate("delete from t_order where id=?;",order.getId());
    }

    @Override
    public List<Order> getOrderList(User user) {
        return super.executeQuery("select * from t_order where orderUser=?;",user.getId());
    }

//    public Integer getOrderTotalBookCount(Order order) {
//        String sql = "SELECT SUM(t3.buyCount) AS totalBookCount , t3.orderBean " +
//                "FROM (SELECT t1.id , t2.buyCount , t2.orderBean FROM t_order t1 INNER JOIN t_order_item t2 " +
//                "ON t1.id = t2.orderBean WHERE t1.orderUser = ? " +
//                ") t3 WHERE t3.orderBean = ? GROUP BY t3.orderBean" ;
//        return ((BigDecimal)executeComplexQuery(sql,order.getOrderUser().getId(),order.getId())[0]).intValue();
//    }
}
