package com.cskaoyan.mall.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String getMd5(String content){
        //先获得正文的字节
        byte[] bytes = content.getBytes();
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //将正文的字节转换成md5的字节
            StringBuffer sb = new StringBuffer();
            byte[] digestBytes = md5.digest(bytes);
            for (byte digestByte : digestBytes) {
                int i = digestByte & 0xff;

                String s = Integer.toHexString(i);
                if (s.length() == 1){
                    sb.append(0);
                }
                sb.append(s);
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return "";
    }
    //sault盐
    public static String getMd5(String content,String salt){
        //实现了加盐
        content = content + "{!"+ salt + "abc";
        return getMd5(content);
    }
}
