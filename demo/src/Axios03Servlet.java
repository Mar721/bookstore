import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/axios03.do")
public class Axios03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String str=null;
        while ((str=reader.readLine())!=null){
            stringBuffer.append(str);
        }
        str=stringBuffer.toString();
        Gson gson = new Gson();
        User user=gson.fromJson(str, User.class);
        System.out.println("user = " + user);

        user.setUname("test");
        user.setPwd("1234");

        String userJsonStr=gson.toJson(user);
        resp.setCharacterEncoding("UTF-8");
        //说明发送的类型
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(userJsonStr);
    }
}
