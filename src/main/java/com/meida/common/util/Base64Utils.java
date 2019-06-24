package com.meida.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {
     public static String decode(String s){
         if(StringUtils.isEmpty(s))
             return "";
         Base64.Decoder decoder = Base64.getDecoder();
         try {
             return new String(decoder.decode(s), "UTF-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
             return "";
         }
     }

     public static String encode(String s){
         if(StringUtils.isEmpty(s))
             return "";
         Base64.Encoder encoder = Base64.getEncoder();
         try {
            byte[] textByte=s.getBytes("UTF-8");
             String encodedText = encoder.encodeToString(textByte);
             return encodedText;
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
             return "";
         }
     }

    public static void main(String[] args) {
       String s1= encode("61252519921006140X你好呀https://www.baidu.com?a=b&c=1");
       String s2= encode("612525199210061402");
        System.out.println(s1);
        System.out.println(s2);

        String s3= decode(s1);
        String s4= decode(s2);
        System.out.println(s3);
        System.out.println(s4);
    }

}
