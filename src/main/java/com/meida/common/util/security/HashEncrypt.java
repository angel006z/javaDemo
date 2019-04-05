package com.meida.common.util.security;

public class HashEncrypt {
     /**
     * 后台加密算法
     * 明码：admin  ->加密后：15b2cedb20de534ad43b54303ae9a772
     * 明码：111111 ->加密后：54809f31a5f991aeba92d0f910367544
     * @param s md5(szSource)=明码后的md5
     * @return
     */
    public static String backendPassword(String s)
    {
        //md5(sha1(md5(明码))+sha1(md5(明码))）
        return MD5Utils.md5(SHA1Utils.sha1(s) + SHA1Utils.sha1(s));
    }

    public static void main(String[] args) throws Exception {
        System.out.println("admin:" + backendPassword(MD5Utils.md5("admin")));
        System.out.println("111111:" + backendPassword(MD5Utils.md5("111111")));
    }
}
