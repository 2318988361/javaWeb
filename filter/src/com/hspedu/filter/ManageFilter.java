package com.hspedu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class ManageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ManageFilter 的 init被调用。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ManageFilter的doFilter被调用。。。");


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Object username = httpServletRequest.getSession().getAttribute("username");

        if (username != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("ManageFilter被销毁。。。");
    }
}
