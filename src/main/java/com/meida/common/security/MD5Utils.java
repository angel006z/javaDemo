package com.meida.common.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * MD5加密
 * @author dqwu
 *
 */
public class MD5Utils {
	
	
    /**
	 * MD5 摘要计算(String).
	 *
	 * @param src String
	 * @throws Exception
	 * @return String
	 */
	public static final String md5Digest(String src) throws Exception {
		if (StringUtils.isBlank(src)) return StringUtils.EMPTY;
		
		byte[] digest = null;
		try {
			//取得MD5的MessageDigest
			MessageDigest md = MessageDigest.getInstance("MD5");
			//对其进行更新
			md.update(src.getBytes());
			//MessageDigest从digest中获值
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			//MD5的算法抛出异常
			throw e;
		}
		 //把密文转换成十六进制的字符串形式
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };// 用来将字节转换成16进制表示的字符 
        int j = digest.length;
        char str[] = new char[j * 2];
        
        int k = 0;
        
        for (int i = 0; i < j; i++) {
            byte byte0 = digest[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
	}
	
	/**
	 * 使用DES和MD5双重加密
	 * @param src
	 * @return
	 */
	public static String md5AndDes(String src) {
		try {
			src = DesUtils.encrypt(src);
			return md5Digest(src);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
     * 对字符串进行MD5签名
     * 
     * @param text
     *            明文
     * 
     * @return 密文
     */
    public static String md5(String text) {

        return DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));

    }
	
	/**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
	
}
