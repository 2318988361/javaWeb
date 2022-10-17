package com.hspedu.tomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class HspTomcatV1 {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket，在8080端口监听
        ServerSocket serverSocket = new ServerSocket(8080);
        //等待连接
        Socket socket = serverSocket.accept();

        //通过通道Socket接收客户端发送数据
        InputStream inputStream = socket.getInputStream();
        //转换为字符类型方便输出
        String res = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while( (res = bufferedReader.readLine()) != null){
            System.out.println(res);
        }

        //对客户端的请求进行响应
        OutputStream outputStream = socket.getOutputStream();
        //写响应头,要满足响应头的格式要求
        String respHeader = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html;charset=utf-8\r\n\r\n";
        String resp = respHeader + "hello ,未来的你";
        System.out.println(resp);
        outputStream.write(resp.getBytes());

        //输出资源刷新，关闭资源
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        socket.close();


    }
}
