package bookstore.service;

import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;

public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);
    Cart getCart(User user);
    void delCartItem(CartItem cartItem);
}
