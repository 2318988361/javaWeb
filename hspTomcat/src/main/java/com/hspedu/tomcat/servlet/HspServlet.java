package com.hspedu.tomcat.servlet;

import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;

import javax.servlet.*;
import java.io.IOException;
//保留三个核心方法

public interface HspServlet {
    void init(ServletConfig var1) throws ServletException;


    void service(HspRequest request, HspResponse response) throws IOException;


    void destroy();
}
