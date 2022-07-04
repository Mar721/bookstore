package bookstore.dao;

import bookstore.pojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> getBookList();

    Book getBookById(Integer id);

    void subBookStore(Book book, Integer count);

    void addBookStore(Book book, Integer count);

    void addBookSale(Book book, Integer count);
}
