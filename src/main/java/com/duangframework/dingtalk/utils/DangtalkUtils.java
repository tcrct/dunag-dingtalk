package com.duangframework.dingtalk.utils;

import com.duangframework.kit.ToolsKit;

import java.util.Random;

/**
 * 加解密工具类
 */
public class DangtalkUtils {

    private static final String BASE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 获取随机字符串
     *
     * @return
     */
    public static String getRandomStr(int count) {

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(BASE_STRING.length());
            sb.append(BASE_STRING.charAt(number));
        }
        return sb.toString();
    }


    /**
     * int转byte数组,高位在前
     */
    public static byte[] int2Bytes(int count) {
        byte[] byteArr = new byte[4];
        byteArr[3] = (byte) (count & 0xFF);
        byteArr[2] = (byte) (count >> 8 & 0xFF);
        byteArr[1] = (byte) (count >> 16 & 0xFF);
        byteArr[0] = (byte) (count >> 24 & 0xFF);
        return byteArr;
    }

    /**
     * 高位在前bytes数组转int
     *
     * @param byteArr
     * @return
     */
    public static int bytes2int(byte[] byteArr) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            count <<= 8;
            count |= byteArr[i] & 0xff;
        }
        return count;
    }

    /**
     *  集合转字符串
     * @param collections   集合
     * @param joinStr   分隔字符
     * @return
     */
    public static String collections2String(java.util.Collection<String> collections, String joinStr) {
        StringBuilder str = new StringBuilder();
        for(String value : collections) {
            str.append(value).append(joinStr);
        }
        if(ToolsKit.isNotEmpty(str)) {
            str.deleteCharAt(str.length() - 1);
        }
        return str.toString();
    }
}
