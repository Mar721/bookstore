package bookstore.controllers;

import bookstore.dao.UserDao;
import bookstore.pojo.Cart;
import bookstore.pojo.User;
import bookstore.service.CartItemService;
import bookstore.service.UserService;
import com.google.code.kaptcha.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {
    private UserService userService;
    private CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session, HttpServletResponse resp){
        User user = userService.login(uname, pwd);
        if(user!=null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser",user);

            Cookie cookieUname=new Cookie("uname",uname);
            Cookie cookiePwd=new Cookie("pwd",pwd);
            resp.addCookie(cookieUname);
            resp.addCookie(cookiePwd);

            return "redirect:book.do?operate=index";
        }
        return "user/login";
    }

    public String register(String uname,String pwd,String email,String verifyCode,HttpSession session,HttpServletResponse resp){
        String attribute = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        if (verifyCode==null||!verifyCode.equals(attribute)){
            try {
                PrintWriter out=resp.getWriter();
                out.println("<script type=\"text/javascript\">alert('验证码不正确!');" +
                        "window.location.href='page.do?operate=page&page=user/regist';</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            User user=new User(uname,pwd,email);
            userService.register(user);
            try {
                PrintWriter out=resp.getWriter();
                out.println("<script type=\"text/javascript\">alert('注册成功!');" +
                        "window.location.href='page.do?operate=page&page=user/login';</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public String ckUname(String uName){
        User user = userService.getUser(uName);
        if(user!=null){
            //用户名已经被占用，不可以注册
            return "json:{'uname':'1'}";

        }else{
            //用户名可以注册
            return "json:{'uname':'0'}";
            //return "ajax:0";
        }
    }
}
