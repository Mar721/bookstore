package bookstore.service.impl;

import bookstore.dao.CartItemDao;
import bookstore.dao.OrderDao;
import bookstore.dao.OrderItemDao;
import bookstore.pojo.*;
import bookstore.service.BookService;
import bookstore.service.CartItemService;
import bookstore.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private CartItemService cartItemService;
    private BookService bookService;
    @Override
    public void addOrder(Order order) {
        //1、增加新订单
        orderDao.addOrder(order);  //执行这一步后，order中的id已经有值

        Cart cart = order.getOrderUser().getCart();
        Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
        for(CartItem cartItem:cartItemMap.values()){
            //将购物车中的CartItem封装成OrderItem
            //2、增加新订单详情
            OrderItem orderItem=new OrderItem(cartItem.getBook(),cartItem.getBuyCount(),order);
            orderItemDao.addOrderItem(orderItem);
            //3、删除购物车项
            cartItemService.delCartItem(cartItem);
            //4、更新书本的销量和库存
            bookService.saleBook(cartItem.getBook(),cartItem.getBuyCount());
        }

        //清空购物车，这个bug找了一个小时
        cartItemMap.clear();

    }

    @Override
    public List<Order> getOrderList(User user) {
        List<Order> orderList = orderDao.getOrderList(user);
        for(Order order:orderList){
            Integer orderTotalBookCount = orderItemDao.getOrderTotalBookCount(order);
            order.setTotalBookCount(orderTotalBookCount);
        }
        return orderList;
    }
}
