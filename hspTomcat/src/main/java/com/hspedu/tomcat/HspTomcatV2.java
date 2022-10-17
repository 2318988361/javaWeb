package com.hspedu.tomcat;

import com.hspedu.tomcat.handler.HspRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class HspTomcatV2 {
    public static void main(String[] args) throws IOException {
        //在8080锻炼口监听
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("HspTomcatV2在8080监听");
        //没有关闭serverSocket，就一直监听
        while (!serverSocket.isClosed()){
            //socket是数据通道
            Socket socket = serverSocket.accept();
            //创建一个线程对象，socket给该线程
            new Thread(new HspRequestHandler(socket)).start();

        }
    }
}
