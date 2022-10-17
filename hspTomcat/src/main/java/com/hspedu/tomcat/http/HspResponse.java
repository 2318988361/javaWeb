package com.hspedu.tomcat.http;

import java.io.OutputStream;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
/*
封装outputStream(socket关联)
返回http响应给浏览器
HspRespond作用类似于servlet中的HttpServletRespond
 */
public class HspResponse {
    private OutputStream outputStream = null;

    //写一个http响应头
    public static final String respHeader = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html;charset=utf-8\r\n\r\n";

    //创建HspResponse 对象时，传入outputStream是和Socket关联的
    public HspResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    //当我们需要给浏览器返回数据时，可以通过HspResponse 的输出流完成
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
