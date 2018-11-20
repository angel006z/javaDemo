package com.meida.common.util.security;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import java.util.Base64;
import java.util.Base64.Decoder;
/**
 * DES加密解密类
 * @author dqwu
 *
 */
public class DesUtils {
	
	private static final String PASSWORD_CRYPT_KEY = "!6@#E_$R";
	
	private final static String DES = "DES"; 
	
	/**
	 * DES进行加密
	 * @param data 
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) throws Exception {
		byte[] bt = encrypt(data.getBytes("UTF-8"), PASSWORD_CRYPT_KEY.getBytes());
		String strs = Base64.getEncoder().encodeToString(bt);
		return strs;
	}

	/**
	 * DES进行解密
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data) throws IOException,
			Exception {
		if (data == null)
			return null;
		Decoder decoder = Base64.getDecoder();
		byte[] buf = decoder.decode(data);
		byte[] bt = decrypt(buf,PASSWORD_CRYPT_KEY.getBytes());
		return new String(bt,"UTF-8");
	}
	
	/**
	 * DES进行解密
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data,String key) throws IOException,
			Exception {
		if (data == null)
			return null;
		Decoder decoder = Base64.getDecoder();
		byte[] buf = decoder.decode(data);
		byte[] bt = decrypt(buf,key.getBytes());
		return new String(bt,"UTF-8");
	}


	/**
	 * Description 根据键值进行加密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}
	
	
	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}
}
