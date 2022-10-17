package com.hspedu.tomcat.handler;

import com.hspedu.tomcat.HspTomcatV3;
import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;
import com.hspedu.tomcat.servlet.HspCalServlet;
import com.hspedu.tomcat.servlet.HspHttpServlet;
import com.hspedu.tomcat.utils.WebUtils;

import java.io.*;
import java.net.Socket;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 对http请求和响应进行处理
 */
public class HspRequestHandler implements Runnable{

    //定义Socket
    private Socket socket = null;

    public HspRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //可以对客户端进行IO交互编程
        try {
            InputStream inputStream = socket.getInputStream();

//            //把inputStream -> BufferedReader 方便按行读取
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            System.out.println("=======hsptomcatV2接收的数据如下：");
//            String mes = "";
//            while((mes = bufferedReader.readLine()) != null){
//                if (mes.length() == 0) {
//                    break;
//                }
//                System.out.println(mes);
//            }

            //先死后活
            HspRequest hspRequest = new HspRequest(inputStream);
//            String num1 = hspRequest.getParameter("num1");
//            String num2 = hspRequest.getParameter("num2");
//            System.out.println("请求的参数num1=" + num1);
//            System.out.println("请求的参数num2=" + num2);
//            System.out.println(hspRequest);

            //同HspResponse 对象， 返回数据给浏览器
            HspResponse hspResponse = new HspResponse(socket.getOutputStream());

            //创建一个HspCalServlet对象   用反射构建对象
//            HspCalServlet hspCalServlet = new HspCalServlet();
//            hspCalServlet.doGet(hspRequest,hspResponse);
            //先获取uri
            String uri = hspRequest.getUri();
            //判断uri是什么资源，静态资源读取该资源，返回给浏览器 content-type text/html
            //目前没有完整tomcat作用，获取静态资源很难，cal.html放入classes 方便使用
            if (WebUtils.isHtml(uri)){
                String content = WebUtils.readHtml(uri.substring(1));
                content = HspResponse.respHeader + content;
                //得到outputStream  返回信息(静态页面)给浏览器
                OutputStream outputStream = hspResponse.getOutputStream();
                outputStream.write(content.getBytes());
                outputStream.flush();
                outputStream.close();
                socket.close();
                return;

            }
            String servletName = HspTomcatV3.servletUrlMapping.get(uri);
            if (servletName == null){
                servletName = "";
            }
            //通过uri获取servletName 在获取servlet实例
            //编译类型是hspHttpServlet  运行类型是HspCalServlet
            HspHttpServlet hspHttpServlet = HspTomcatV3.servletMapping.get(servletName);

            if (hspHttpServlet != null){
                //oop动态绑定机制 调用运行类型的doGET或者doPOST
                hspHttpServlet.service(hspRequest,hspResponse);
            }else{
                //没有这个servlet ，返回404提示信息
                String resp = HspResponse.respHeader + "<h1>404 Not Found</h1>";
                OutputStream outputStream = hspResponse.getOutputStream();
                outputStream.write(resp.getBytes());
                outputStream.flush();
                outputStream.close();
            }

//            String resp = HspResponse.respHeader + "<h1>hspResponse 返回的信息  hi 你好<h1/>";
//            OutputStream outputStream = hspResponse.getOutputStream();
//            outputStream.write(resp.getBytes());
//            outputStream.flush();
//            outputStream.close();

//            //构建一下http响应头
//            String respHeader = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: text/html;charset=utf-8\r\n\r\n";
//            String resp = respHeader + "hi,你的未来在哪里？";
//            //返回数据给客户端  封装成htp的响应
//            OutputStream outputStream = socket.getOutputStream();
//            outputStream.write(resp.getBytes());//字符串装成字节数组
//            System.out.println("=======hsptomcatv2返回的数据：");
//            System.out.println(resp);
//            outputStream.flush();
//            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //确保socket关闭
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
