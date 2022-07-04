package bookstore.pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User user;

    private Double priceCount;

    public CartItem() {
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Book book, Integer buyCount, User user) {
        this.book = book;
        this.buyCount = buyCount;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPriceCount() {
        priceCount=0.0;
        BigDecimal bigDecimalPrice=new BigDecimal(getBook().getPrice().toString());
        BigDecimal bigDecimalByCount=new BigDecimal(buyCount.toString());
        BigDecimal multiply = bigDecimalPrice.multiply(bigDecimalByCount);
        return priceCount=multiply.doubleValue();

    }
}
