package com.hspedu.tomcat.servlet;

import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;
import com.hspedu.tomcat.utils.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class HspCalServlet extends HspHttpServlet {

    @Override
    public void doGet(HspRequest request, HspResponse response) {
        int num1 = WebUtils.parseInt(request.getParameter("num1"), 0);
        int num2 = WebUtils.parseInt(request.getParameter("num2"), 0);

        int result = num1 + num2;

        //返回计算结果，给浏览器
        //outputStream 和 当前的socket关联
        OutputStream outputStream = response.getOutputStream();
        String resMes = HspResponse.respHeader + "<h1>" + num1 + "+" + num2 + "=" + result + "反射+xml创建" + "<h1>";
        try {
            outputStream.write(resMes.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HspRequest request, HspResponse response) {
        this.doGet(request,response);
    }

    @Override
    public void init(ServletConfig var1) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
