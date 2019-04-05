package com.meida.common.util.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
/**
 * AES加密 有问题待解决
 * 参照网站：http://tool.chacuo.net/cryptaes
 * 以下是具体参数
 * AES加密模式：ECB
 * 填充：zeropadding
 * 数据块：128位
 *密码：8C8AE240-2B07-4228-99F4-6C16FCC48CC4
 *偏移量：
 *输出：base64
 *字符集：utf8
 *
 *源字符：111111
 *加密后：7gLqAuJaBWUs0VX3qP6WHQ==
 *
 *源字符：中华人民共和国
 *加密后：90+kJlWVTDrZB0YGnf8ya8fV9warovDi4l2FD6rEYao=
 *
 *源字符：admin
 *加密后：w7X1lpkXwAbAMF7ZGK98Ew==
 */
public class AESUtils {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception ex) {
            Logger.getLogger(AESUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(AESUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    private final  static String aesKey = "8C8AE240-2B07-4228-99F4-6C16FCC48CC4";
    public static String encrypt(String content)
    {
        return encrypt(content, aesKey);
    }
    public static String decrypt(String content)
    {
        return decrypt(content, aesKey);
    }
    public static void main(String[] args) {
        String s1 = encrypt("111111");
        System.out.println("111111:" + s1);
        System.out.println(s1+":"+decrypt(s1));

        String s2 = encrypt("中华人民共和国");
        System.out.println("中华人民共和国:" + s2);
        System.out.println(s2+":"+decrypt(s2));

        String s3 = encrypt("admin");
        System.out.println("admin:" + s3);
        System.out.println(s3+":"+decrypt(s3));
    }
}
