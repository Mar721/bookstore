package bookstore.controllers;

import bookstore.pojo.Book;
import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;
import bookstore.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class CartController {
    private CartItemService cartItemService;
    public String addCart(Integer bookId, HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        //这里book不只需要id
        CartItem cartItem=new CartItem(new Book(bookId),1,currUser);
        //在cartItemService中更新购物车
        cartItemService.addOrUpdateCartItem(cartItem,currUser.getCart());
        //本来这里重置session是因为购物车有更新，但发现getAttribute获取的是地址，所以可以直接修改session中user的购物车信息
        //session.setAttribute("currUser",currUser);
        return "redirect:page.do?operate=page&page=index";
    }

    //在购物车中修改数量
    public String editCart(Integer cartItemId,Integer byCount){
        cartItemService.updateCartItem(new CartItem(cartItemId,byCount));
        return null;
    }

    //删除购物车中的某一项
    public String delCartItem(Integer id,HttpSession session){
        cartItemService.delCartItem(new CartItem(id));
        User currUser = (User) session.getAttribute("currUser");
        //更新购物车
        Cart cart = cartItemService.getCart(currUser);
        currUser.setCart(cart);
        return null;
    }
    
    //清空购物车
    public String clearCart(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        Cart cart = currUser.getCart();
        Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
        for(CartItem cartItem:cartItemMap.values()){
            cartItemService.delCartItem(cartItem);
        }
        cartItemMap.clear();
        return "cart/cart";
    }



    //这个方法为了前后端分离
    public String cartInfo(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(currUser);
        Gson gson=new Gson();
        String jsonStr = gson.toJson(cart);
        return "json:"+jsonStr;
    }
}
