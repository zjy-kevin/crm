package com.yue.crm.web.filter;

import com.yue.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入到验证有没有登录过过滤器");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path=request.getServletPath();
        if ("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)){
            chain.doFilter(req,resp);
        }else{
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }
    }
}
