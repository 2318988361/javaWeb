package com.hspedu.tomcat.utils;

import com.hspedu.tomcat.HspTomcatV3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class WebUtils {

    //将字符串转成数字侧方法
    public static int parseInt(String strNum,int defaultVal){
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println("不能转成数字");
        }
        return defaultVal;
    }

    //判断uri是不是html文件
    public static boolean isHtml(String uri){
        return uri.endsWith(".html");
    }

    //根据文件名读取该文件  ->String
    public static String readHtml(String fileName){
        String path = WebUtils.class.getResource("/").getPath();
//        System.out.println(path);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            //读取html代码字符传输给浏览器，浏览器通过对字符解析，后展示，像防御传输一堆命令给浏览器
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "cal.html"));
            String buf = "";
            while((buf = bufferedReader.readLine()) != null){
                stringBuilder.append(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }
}
