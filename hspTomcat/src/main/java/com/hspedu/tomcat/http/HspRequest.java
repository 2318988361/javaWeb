package com.hspedu.tomcat.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
/*
封装http请求
比如：method(get)  uri(/hspCalServlet) 还有参数列表
HspRequest  作用类似原生servlet中的HttpServletRequest
请求头第一行 GET /calServlet?num1=9&num2=k&name=jia HTTP/1.1
先只考虑get请求
 */
public class HspRequest {
    private String method;
    private String uri;
    //存放参数列表  参数名-参数值 -> HashMap
    private HashMap<String,String> parametersMapping =
            new HashMap<>();
    private InputStream inputStream = null;
    //构造器
    //inputStream 和 对应http请求的socket关联
    public HspRequest (InputStream inputStream){
        this.inputStream = inputStream;
        //完成对http请求数据的封装
        encapHttpRequest();
    }

    private void encapHttpRequest(){
        System.out.println("encapHttpRequest被调用");
        //转为BufferedReader
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            //读取第一行
            String requestLine = bufferedReader.readLine();
            String[] requestLineArr = requestLine.split(" ");
            //获取得到method值
            method = requestLineArr[0];
            //解析取出uri,有？有参数，需要分割，没有？不需要分割
            int index = requestLineArr[1].indexOf("?");
            if (index == -1){//如果为-1表示没有参数列表
                uri = requestLineArr[1];
            }else{
                //0-index index位置并不包含
                uri = requestLineArr[1].substring(0,index);
                //获取参数列表,从index + 1直到最后
                String parameters = requestLineArr[1].substring(index + 1);
                //防止用户故意只提交？或&
                String[] parametersPair = parameters.split("&");
                if (null != parametersPair && !"".equals(parametersPair)){
                    //再次分割
                    for (String parameterPair : parametersPair) {
                        String[] parameterVal = parameterPair.split("=");
                        if (parameterVal.length == 2){
                            parametersMapping.put(parameterVal[0],parameterVal[1]);
                        }
                    }
                }
                //这里不能关闭流 因为inputStream 和 socket关联
//                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //request对象有一个重要方法
    public String getParameter(String name){
        if (parametersMapping.containsKey(name)){
            return parametersMapping.get(name);
        }else{
            return "";
        }

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "HspRequest{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", parametersMapping=" + parametersMapping +
                '}';
    }
}
