package com.hspedu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //做转发
//        System.out.println("转发成功");
//        request.getRequestDispatcher("/Views/user/user.html").forward(request,response);

        //重定向
        System.out.println("重定向");
//        response.sendRedirect("/webpath/Views/user/user.html");
        String contextPath = getServletContext().getContextPath();
        response.sendRedirect(contextPath + "/Views/user/user.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
