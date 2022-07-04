package bookstore.service;

import bookstore.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    Book getBookById(Integer id);
    void saleBook(Book book,Integer count);
    void addBookStore(Book book,Integer count);
}
