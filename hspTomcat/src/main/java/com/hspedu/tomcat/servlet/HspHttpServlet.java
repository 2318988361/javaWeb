package com.hspedu.tomcat.servlet;

import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;

import java.io.IOException;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public abstract class HspHttpServlet implements HspServlet{

    @Override
    public void service(HspRequest request, HspResponse response) throws IOException {
        if ("GET".equals(request.getMethod())){
            this.doGet(request,response);
        }else if ("POST".equals(request.getMethod())){
            this.doPost(request,response);
        }
    }

    //使用了模板设计模式
    //让HspHttpServlet 子类 HspCalServlet实现

    public abstract void doGet(HspRequest request, HspResponse response);
    public abstract void doPost(HspRequest request, HspResponse response);

}
