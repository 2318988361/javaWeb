package com.hspedu.Utils;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class WebUtils {
    /**
     * 将一个字符串数字转换成int，转换失败返回传入的defaultVal
     * @param strNum
     * @param defaultVal
     * @return
     */
    public static int parseInt(String strNum,int defaultVal){
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println("转换败，格式不正确");
        }
        return defaultVal;
    }
}