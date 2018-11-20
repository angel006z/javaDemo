package com.meida.common.util.security;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.meida.common.util.StringUtils;
import com.meida.exception.ApplicationException;

/**
 * DES/ECB 加密解密
 * @author xh
 * @version 2016-5-11 上午9:19:26 
 */
public class DESECBUtils {
	private final static String DES = "DES";
	private final static String DES_ECB = "DES/ECB/NoPadding"; // 加密模式
	private static final String PASSWORD_CRYPT_KEY = "0f1571c947d9e859";

	/**
	 * DES/ECB进行加密
	 * @param data 
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) throws Exception {
		if (data == null) {
			return null;
		}
		byte[] bt = encrypt(StringUtils.hexToBytes(data), StringUtils.hexToBytes(PASSWORD_CRYPT_KEY));
		return StringUtils.bytesToHex(bt);
	}

	/**
	 * DES/ECB进行解密
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data) throws Exception {
		if (data == null) {
			return null;
		}
		byte[] bt = decrypt(StringUtils.hexToBytes(data), StringUtils.hexToBytes(PASSWORD_CRYPT_KEY));
		return StringUtils.bytesToHex(bt);
	}

	/**
	 * DES/ECB加密
	 * @author xh
	 * @date 2016-5-11 上午9:33:08
	 * @param datasource 明文
	 * @param keys 密钥
	 * @return
	 */
	public static byte[] encrypt(byte[] datasource, byte[] keys) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(keys);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(DES_ECB);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	/**
	 * DES/ECB解密
	 * @author xh
	 * @date 2016-5-11 上午9:32:35
	 * @param src 密文
	 * @param keys 密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] src, byte[] keys) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(keys);
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(DES_ECB);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return cipher.doFinal(src);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	// 测试
	public static void main(String args[]) throws Exception {
		// 密文：da02ce3a89ecac3b
		String msg = "2B4991D03C11A002"; // 明文
		String key = "0f1571c947d9e859"; // 密钥
		byte[] a = StringUtils.hexToBytes(msg);
		; // 待加密内容
		byte[] b = StringUtils.hexToBytes(key); // 密钥，长度要是8的倍数
		try {
			// 加密
			/*
			 * byte[] result = encrypt(a, b); System.out.println("加密后：" +
			 * StringUtils.bytesToHex(result));
			 * 
			 * // 解密 result = decrypt(result, b); System.out.println("解密后：" +
			 * StringUtils.bytesToHex(result));
			 */

			// String s = encrypt(msg);
			// System.out.println(s);
			// System.out.println(decrypt(s));

			System.out.println("解密：" + decrypt(msg));

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
