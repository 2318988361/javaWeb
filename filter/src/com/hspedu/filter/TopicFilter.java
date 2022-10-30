package com.hspedu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class TopicFilter implements Filter {
    //属性  存放禁用词
    private String[] forbidWords = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TopicFilter初始化");
        //获取禁用词
        String forbidWord = filterConfig.getInitParameter("forbidWords");
        forbidWords = forbidWord.split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TopicFilter的doFilter被调用。。。");
        //设置编码
        servletRequest.setCharacterEncoding("utf-8");
        String username = servletRequest.getParameter("username");
        String topic = servletRequest.getParameter("topic");

        //username为null，或者评论里包括苹果香蕉，返回topic.jsp，并提示有敏感词
        for (String forbidWord : forbidWords) {
            if (topic.contains(forbidWord)) {
                System.out.println("转发");
                servletRequest.setAttribute("alert", "您的评论里包含敏感词");
                servletRequest.getRequestDispatcher("/topic.jsp").forward(servletRequest, servletResponse);
                return;
            }
        }
        System.out.println("通过");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("TopicFilter过滤器被销毁。。");

    }
}
