package z_bookstore.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"*.do", "*.html"},
        initParams = {
                @WebInitParam(name = "whiteList",
                        value = "/bookstore/page.do?operate=page&page=user/login," +
                                "/bookstore/user.do?operate=login," +
                                "/bookstore/page.do?operate=page&page=user/regist," +
                                "/bookstore/user.do?operate=register")
        }
)
public class SessionFilter implements Filter {
    private List<String> whiteList;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String white = config.getInitParameter("whiteList");
        String[] split = white.split(",");
        whiteList = Arrays.asList(split);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str;
        if (queryString==null){
            str = uri;
        }else
            str = uri + "?" + queryString;
        if (whiteList.contains(str)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            HttpSession session = request.getSession();
            Object currUser = session.getAttribute("currUser");
            if (currUser == null) {
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else
                filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
