package bookstore.dao;

import bookstore.pojo.CartItem;
import bookstore.pojo.User;

import java.util.List;

public interface CartItemDao {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    List<CartItem> getCartItemList(User user);
    void delCartItem(CartItem cartItem);
}
