package bookstore.controllers;

import bookstore.pojo.Book;
import bookstore.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookService bookService;
    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList",bookList);
        return "redirect:page.do?operate=page&page=index";
    }
}
