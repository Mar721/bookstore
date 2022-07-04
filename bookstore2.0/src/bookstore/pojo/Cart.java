package bookstore.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Cart {
    Map<Integer,CartItem> cartItemMap;  //购物车中购物项的集合，map集合中的key就是book的id
    Double totalMoney;                 //购物车的总金额
    Integer totalCount;                 //购物车中购物项的数量
    Integer totalBookCount;            //购物车中书本的总数量
    public Cart() {
    }

    public Integer getTotalBookCount() {
        totalBookCount=0;
        if(cartItemMap!=null&&cartItemMap.size()>0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for(Map.Entry<Integer,CartItem> cartItemEntry:entries){
                totalBookCount+=cartItemEntry.getValue().getBuyCount();
            }
        }
        return totalBookCount;
    }


    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney=0.0;
        if(cartItemMap!=null&&cartItemMap.size()>0){
            Set<Map.Entry<Integer, CartItem>> entrySet = cartItemMap.entrySet();
            for(Map.Entry<Integer,CartItem> cartItemEntry:entrySet){
                CartItem value = cartItemEntry.getValue();
                totalMoney+=value.getPriceCount();
            }
        }
        return totalMoney;
    }



    public Integer getTotalCount() {
        totalCount=0;
        if(cartItemMap!=null&&cartItemMap.size()>0){
            return cartItemMap.size();
        }
        return totalCount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItemMap=" + cartItemMap +
                ", totalMoney=" + totalMoney +
                ", totalCount=" + totalCount +
                ", totalBookCount=" + totalBookCount +
                '}';
    }
}
