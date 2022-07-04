package bookstore.service.impl;

import bookstore.dao.BookDao;
import bookstore.pojo.Book;
import bookstore.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public Book getBookById(Integer id) {
        return bookDao.getBookById(id);
    }

    @Override
    public void saleBook(Book book, Integer count) {
        //减少库存
        bookDao.subBookStore(book,count);
        //增加销量
        bookDao.addBookSale(book,count);
    }

    @Override
    public void addBookStore(Book book, Integer count) {
        bookDao.addBookStore(book,count);
    }
}
