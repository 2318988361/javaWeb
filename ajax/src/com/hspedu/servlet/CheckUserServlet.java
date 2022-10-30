package com.hspedu.servlet;

import com.google.gson.Gson;
import com.hspedu.dao.BasicDAO;
import com.hspedu.javaBean.User;
import com.hspedu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckUserServlet")
public class CheckUserServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckServlet....");
//        String username = request.getParameter("uname");
//        System.out.println("uname" + username);
//        //如果user已经被占用，那么我们就返回king这个用户的json字符串
//        response.setContentType("text/html;charset=utf-8");
//        //到DB查询
//        User user = this.userService.getUserService(username);
//
//        if (user != null){//用户名已经存在，返回该user的json的字符串
//            Gson gson = new Gson();
//            String strUser = gson.toJson(user);
//            response.getWriter().write(strUser);
//        }else{
//            response.getWriter().write("");
//        }

        //jquery方法
        User user1 = new User(-1,"","");
        String username = request.getParameter("username");
        System.out.println("parameter" + username);
        User user = new UserService().getUserService(username);

        response.setContentType("text/json;charset=utf-8");
        Gson gson = new Gson();
        if (user != null){
            response.getWriter().write(gson.toJson(user));
        }else{
            response.getWriter().write(gson.toJson(user1));
        }
//        PrintWriter writer = response.getWriter();
//        if (user.getName() != ""){
//            User king = new User(100, "king", "2233@.com");
//            String strJson = new Gson().toJson(king);
//            writer.write(strJson);
//        }else{
//            writer.write("");
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
