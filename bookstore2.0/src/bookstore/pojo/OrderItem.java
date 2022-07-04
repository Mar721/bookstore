package bookstore.pojo;

public class OrderItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Book book, Integer buyCount, Order order) {
        this.book = book;
        this.buyCount = buyCount;
        this.order = order;
    }

    public OrderItem(Integer id) {
        this.id = id;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
