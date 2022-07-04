package bookstore.service.impl;

import bookstore.dao.CartItemDao;
import bookstore.pojo.Book;
import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;
import bookstore.service.BookService;
import bookstore.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao;
    private BookService bookService;
    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDao.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDao.updateCartItem(cartItem);
    }

    //在service中进行判断是增加还是更新，就不需要在controller层进行考虑
    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        if (cart!=null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap==null){
                //一般用不上
                cartItemMap=new HashMap<>();
            }
            if (cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+1);
                updateCartItem(cartItemTemp);
            }else{
                //这里cartItem中的book是空的，只有id所以需要补充，本来是用下面那个更新购物车碰巧成功了
                //因为getCart是从数据库中获取了cartItem,并且补充了book，所以刚好弥补了bug
                //注释了之后，bug又出现了，所以需要在这里获取book
                addCartItem(cartItem);

                //感觉不如直接调用更新购物车
                Book book=bookService.getBookById(cartItem.getBook().getId());
                cartItem.setBook(book);
                cartItem.getPriceCount();
                cartItemMap.put(cartItem.getBook().getId(),cartItem);

                //前后端分离新增
                cart.getTotalBookCount();
                cart.getTotalCount();
                cart.getTotalMoney();
            }
        }else {
            //一般用不上
            //addCartItem(cartItem);
            throw new RuntimeException("购物车为null");
        }

        //更新购物车,这里不需要手动更新
        //user.setCart(this.getCart(user));
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = cartItemDao.getCartItemList(user);

        Map<Integer,CartItem> map=new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            //数据库查询出来的book只有id，需要获取完整的book
            Book bookById = bookService.getBookById(cartItem.getBook().getId());
            cartItem.setBook(bookById);
            cartItem.getPriceCount();
            map.put(cartItem.getBook().getId(), cartItem);
        }
        Cart cart=new Cart();
        cart.setCartItemMap(map);

        cart.getTotalBookCount();
        cart.getTotalCount();
        cart.getTotalMoney();
        return cart;
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        cartItemDao.delCartItem(cartItem);
    }
}
