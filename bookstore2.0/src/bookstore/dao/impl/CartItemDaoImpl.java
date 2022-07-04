package bookstore.dao.impl;

import bookstore.dao.CartItemDao;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;
import com.myssm.basedao.BaseDAO;

import java.util.List;

public class CartItemDaoImpl extends BaseDAO<CartItem> implements CartItemDao {
    @Override
    public void addCartItem(CartItem cartItem) {
        Integer id = super.executeUpdate("insert into t_cart_item(book,buyCount,user)values(?,?,?);",
                cartItem.getBook().getId(), cartItem.getBuyCount(), cartItem.getUser().getId());
        cartItem.setId(id);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        super.executeUpdate("update t_cart_item set buyCount=? where id=?;", cartItem.getBuyCount(), cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return super.executeQuery("select * from t_cart_item where user=?;", user.getId());
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        super.executeUpdate("delete from t_cart_item where id=?;", cartItem.getId());
    }
}
