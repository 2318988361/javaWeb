package com.hspedu.tomcat;

import com.hspedu.tomcat.handler.HspRequestHandler;
import com.hspedu.tomcat.servlet.HspCalServlet;
import com.hspedu.tomcat.servlet.HspHttpServlet;
import com.hspedu.tomcat.servlet.HspServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 通过xml+反射来初始化容器
 */
public class HspTomcatV3 {
    //第一个容器servletMapping
    public static final ConcurrentHashMap<String, HspHttpServlet> servletMapping
            = new ConcurrentHashMap<>();

    //第二个容器servletUrlMapping
    public static final ConcurrentHashMap<String, String> servletUrlMapping
            = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        HspTomcatV3 hspTomcatV3 = new HspTomcatV3();
        hspTomcatV3.init();
        //启动hspTomcat容器
        hspTomcatV3.run();
    }

    //启动HspTomcatV3容器
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("=====HspTomcatV3在8080监听");
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                HspRequestHandler hspRequestHandler = new HspRequestHandler(socket);
                new Thread(hspRequestHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //直接对两个容器初始化
    public void init(){
       //读取web.xml => dom4j =>
        //得到web.xml文件的路径
        String path = HspTomcatV3.class.getResource("/").getPath();
        //使用dom4j技术完成读取
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(path + "web.xml"));
            System.out.println("document = " + document);
            //得到根元素
            Element rootElement = document.getRootElement();
            //得到根元素下面的所有元素
            List<Element> elements = rootElement.elements();
            //遍历并过滤 servlet servlet-mapping
            for (Element element : elements) {
                if ("servlet".equalsIgnoreCase(element.getName())){
                    //这时一个servlet配置
//                    System.out.println("发现了一个servlet");
                    //使用反射将该servlet实例放入到servletMapping
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    servletMapping.put(servletName.getText(),
                            (HspHttpServlet) Class.forName(servletClass.getText().trim()).newInstance());

                }else if("servlet-mapping".equalsIgnoreCase(element.getName())){
                    //这是一个servlet-mapping
//                    System.out.println("发现了一个servlet-mapping");

                    Element servletName = element.element("servlet-name");
                    Element urlPattern = element.element("url-pattern");
                    servletUrlMapping.put(urlPattern.getText().trim(),servletName.getText().trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //验证这两个容器是否初始化
        System.out.println("servletMapping=" + servletMapping);
        System.out.println("servletUrlMapping=" + servletUrlMapping);
    }
}
