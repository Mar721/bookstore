package bookstore.controllers;

import bookstore.pojo.Order;
import bookstore.pojo.User;
import bookstore.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService;
    public String checkOut(HttpSession session){
        Order order=new Order();

        Date now=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss");
        String nowStr=simpleDateFormat.format(now);
        String[] split = nowStr.split("-");
        String join = String.join("", split);

        User currUser = (User) session.getAttribute("currUser");

        order.setOrderNo(UUID.randomUUID().toString()+"_"+join);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderUser(currUser);
        order.setOrderMoney(currUser.getCart().getTotalMoney());
        order.setOrderStatus(0);

        orderService.addOrder(order);
        session.setAttribute("currOrder",order);
        return "redirect:page.do?operate=page&page=cart/checkout";
    }

    public String getOrderList(HttpSession session){
        User user=(User) session.getAttribute("currUser");
        List<Order> orderList=orderService.getOrderList(user);
        user.setOrderList(orderList);
        return "order/order";
    }

}
