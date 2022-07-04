package bookstore.dao.impl;

import bookstore.dao.BookDao;
import bookstore.pojo.Book;
import com.myssm.basedao.BaseDAO;
import com.myssm.exception.DAOException;

import java.util.List;

public class BookDaoImpl extends BaseDAO<Book> implements BookDao {

    @Override
    public List<Book> getBookList() {
        return super.executeQuery("select * from t_book where bookStatus=0;");
    }

    @Override
    public Book getBookById(Integer id) {
        return super.load("select * from t_book where id=?",id);
    }

    @Override
    public void subBookStore(Book book, Integer count) {
        super.executeUpdate("Update t_book set bookCount=? where id=?;",book.getBookCount()-count,book.getId());
    }

    @Override
    public void addBookStore(Book book, Integer count) {
        super.executeUpdate("Update t_book set bookCount=? where id=?;",book.getBookCount()+count,book.getId());
    }

    @Override
    public void addBookSale(Book book, Integer count) {
        super.executeUpdate("Update t_book set saleCount=? where id=?;",book.getSaleCount()+count,book.getId());
    }


}
