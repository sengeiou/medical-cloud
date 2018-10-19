package com.zhou.medical.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * <p>
 * Title: iSoftStone
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 基础加密组件
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * 
 * <p>
 * Company: iSoftStone
 * </p>
 * 
 * @author lm
 * @version 1.0
 */
public abstract class SecurityUtils {

	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";
	public static final String KEY_DES = "DES";

	/**
	 * 二进制转哈希码
	 * 
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public static String byte2hex(byte[] b) throws Exception {
		StringBuilder hs = new StringBuilder();
		String stmp;
		// 加密后16位数
		// for (int i = 0; i < bts.length/2; i++) {
		// 加密后32位数
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				hs.append('0');
			}
			hs.append(stmp);
		}
		return hs.toString();
	}

	/**
	 * 哈希码转二进制
	 * 
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public static byte[] hex2byte(String s) throws Exception {
		byte[] bs = s.getBytes();
		if ((bs.length % 2) != 0) {
			throw new IllegalArgumentException();
		}
		byte[] bh = new byte[bs.length / 2];
		for (int n = 0; n < bs.length; n += 2) {
			bh[n / 2] = (byte) Integer.parseInt(new String(bs, n, 2), 16);
		}
		return bh;
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		// return (new BASE64Encoder()).encodeBuffer(key);
		return Base64Utils.encode(key);
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		// return (new BASE64Decoder()).decodeBuffer(key);
		return Base64Utils.decode(key);
	}

	/**
	 * MD5加密
	 * 
	 * @param source
	 * @return
	 */
	public static String encryptMD5(String source) throws Exception {
		if (null == source) {
			throw new NullPointerException();
		}
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		byte[] data = source.getBytes("UTF-8");
		md5.update(data, 0, data.length);

		// 可能会出现31位
		// return new BigInteger(1, md5.digest()).toString(16);
		return byte2hex(md5.digest());
	}

	/**
	 * 多次MD5加密
	 * 
	 * @param source
	 * @param times
	 *            加密次数
	 * @return
	 */
	public static String encryptMD5(String source, int times) {
		String sourceTemp = source;
		try {
			for (int i = 0; i < times; i++) {
				sourceTemp = SecurityUtils.encryptMD5(sourceTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sourceTemp;
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DES);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
		// SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
		return secretKey;
	}

	/**
	 * 生成密钥, 暂时不用，手机端和平台端同时执行加密结果不一样，SecureRandom随机后init后key不一样
	 * 
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String initKey(String seed) throws Exception {
		SecureRandom secureRandom = null;
		if (seed != null) {
			secureRandom = new SecureRandom(decryptBASE64(seed));
		} else {
			secureRandom = new SecureRandom();
		}
		KeyGenerator kg = KeyGenerator.getInstance(KEY_DES);
		kg.init(secureRandom);
		SecretKey secretKey = kg.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * DES加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptDES(String data, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));
		Cipher cipher = Cipher.getInstance(KEY_DES);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return byte2hex(cipher.doFinal(data.getBytes()));
	}

	/**
	 * DES解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptDES(String data, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));
		Cipher cipher = Cipher.getInstance(KEY_DES);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return new String(cipher.doFinal(hex2byte(data)));
	}

	/**
	 * 用户通信data加密
	 * 
	 * @param phone
	 *            手机号码
	 * @param pipelinekey
	 *            和手机端商量好的密钥
	 * @return
	 * @throws Exception
	 */
	public static String encryptData(String phone, String pipelinekey) throws Exception {
		char[] pchars = phone.toCharArray();
		int phonel = pchars.length;
		int phonesum = 0;
		for (int i = 0; i < phonel; i++) {
			phonesum += (int) Character.getNumericValue(pchars[i]);
		}
		int n = (phonesum % 5) + 1;
		String enpipelinekey = pipelinekey;
		for (int i = 0; i < n; i++) {
			enpipelinekey = encryptMD5(enpipelinekey);
		}
		StringBuilder sourcebuf = new StringBuilder();
		sourcebuf.append(RandomUtils.generateRandom(4));
		for (int i = 0; i < phonel; i++) {
			sourcebuf.append(pchars[phonel - i - 1]);
		}
		return encryptDES(sourcebuf.toString(), enpipelinekey);
	}

	/**
	 * 通过IMSI查询手机号码专用token
	 * 
	 * @param phone
	 *            和手机端商量好的密钥
	 * @param pipelinekey
	 *            和手机端商量好的密钥
	 * @return
	 * @throws Exception
	 */
	public static String encryptTokenForPHONEIMSI(String phone, String pipelinekey) throws Exception {
		char[] pchars = phone.toCharArray();
		int phonel = pchars.length;
		int phonesum = 0;
		for (int i = 0; i < phonel; i++) {
			phonesum += (int) Character.getNumericValue(pchars[i]);
		}
		int n = (phonesum % 5) + 1;
		String enpipelinekey = pipelinekey;
		for (int i = 0; i < n; i++) {
			enpipelinekey = encryptMD5(enpipelinekey);
		}
		StringBuilder sourcebuf = new StringBuilder();
		for (int i = 0; i < phonel; i++) {
			sourcebuf.append(pchars[phonel - i - 1]);
		}
		return encryptDES(sourcebuf.toString(), enpipelinekey);
	}

	/**
	 * 用户通信data解密
	 * 
	 * @param phone
	 *            手机号码
	 * @param key
	 *            加密好的DATA
	 * @param pipelinekey
	 *            和手机端商量好的密钥
	 * @return
	 * @throws Exception
	 */
	public static String decryptData(String phone, String key, String pipelinekey) throws Exception {
		if (null == phone || null == key || null == pipelinekey || phone.length() != 11 || key.length() != 32) {
			throw new IllegalArgumentException();
		}
		char[] pchars = phone.toCharArray();
		int phonel = pchars.length;
		int phonesum = 0;
		for (int i = 0; i < phonel; i++) {
			phonesum += (int) Character.getNumericValue(pchars[i]);
		}
		int n = (phonesum % 5) + 1;
		String enpipelinekey = pipelinekey;
		for (int i = 0; i < n; i++) {
			enpipelinekey = encryptMD5(enpipelinekey);
		}
		return decryptDES(key, enpipelinekey);
	}

	/**
	 * 用户通信data验证
	 * 
	 * @param phone
	 *            手机号码
	 * @param key
	 *            加密好的DATA
	 * @param pipelinekey
	 *            和手机端商量好的密钥
	 * @return
	 * @throws Exception
	 */
	public static boolean checkingData(String phone, String key, String pipelinekey) throws Exception {
		if (null == phone || null == key || pipelinekey == null || phone.length() != 11 || key.length() != 32) {
			throw new IllegalArgumentException();
		}
		char[] pchars = phone.toCharArray();
		int phonel = pchars.length;
		int phonesum = 0;
		for (int i = 0; i < phonel; i++) {
			phonesum += (int) Character.getNumericValue(pchars[i]);
		}
		int n = (phonesum % 5) + 1;
		String enpipelinekey = pipelinekey;
		for (int i = 0; i < n; i++) {
			enpipelinekey = encryptMD5(enpipelinekey);
		}
		String de_key = decryptDES(key, enpipelinekey);
		de_key = de_key.substring(4, 15);
		// 倒序输出
		StringBuilder sphone = new StringBuilder();
		for (int i = 0; i < phonel; i++) {
			sphone.append(pchars[phonel - i - 1]);
		}
		return sphone.toString().equals(de_key);
	}

	/**
	 * 用户真实token生成
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public static String generateToken(String phone, String registerkey) throws Exception {
		if (null == phone || null == registerkey || phone.length() != 11 || registerkey.length() != 4) {
			throw new IllegalArgumentException();
		}
		StringBuilder sourcebuf = new StringBuilder();
		sourcebuf.append(registerkey).append(phone);
		return sourcebuf.toString();
	}

	/**
	 * Token加密
	 * 
	 * @param phone
	 *            手机号码
	 * @param registerkey
	 *            为指定的4位数
	 * @param pipelinekey
	 *            和手机端商量好的密钥
	 * @return
	 * @throws Exception
	 */
	public static String encryptToken(String phone, String registerkey, String pipelinekey) throws Exception {
		char[] pchars = phone.toCharArray();
		int phonel = pchars.length;
		int phonesum = 0;
		for (int i = 0; i < phonel; i++) {
			phonesum += (int) Character.getNumericValue(pchars[i]);
		}
		int n = (phonesum % 5) + 1;
		String enpipelinekey = pipelinekey;
		for (int i = 0; i < n; i++) {
			enpipelinekey = encryptMD5(enpipelinekey);
		}
		StringBuilder sourcebuf = new StringBuilder();
		sourcebuf.append(registerkey);
		for (int i = 0; i < phonel; i++) {
			sourcebuf.append(pchars[i]);
		}
		return encryptDES(sourcebuf.toString(), enpipelinekey);
	}

	/**
	 * Token加密
	 * 
	 * @param phone
	 *            手机号码
	 * @param temp_token
	 *            为map或者数据库中的userToken
	 * @param pipelinekey
	 *            和手机端商量好的密钥
	 * @return
	 * @throws Exception
	 */
	public static String encryptTempToken(String phone, String temp_token, String pipelinekey) throws Exception {
		char[] pchars = phone.toCharArray();
		int phonel = pchars.length;
		int phonesum = 0;
		for (int i = 0; i < phonel; i++) {
			phonesum += (int) Character.getNumericValue(pchars[i]);
		}
		int n = (phonesum % 5) + 1;
		String enpipelinekey = pipelinekey;
		for (int i = 0; i < n; i++) {
			enpipelinekey = encryptMD5(enpipelinekey);
		}
		return encryptDES(temp_token, enpipelinekey);
	}

	/**
	 * Token解密
	 * 
	 * @param phone
	 *            手机号码
	 * @param key
	 *            加密的Token
	 * @param pipelinekey
	 *            和手机端商量好的密钥
	 * @return
	 * @throws Exception
	 */
	public static String decryptToken(String phone, String key, String pipelinekey) throws Exception {
		if (null == phone || null == key || null == pipelinekey || key.length() != 32) {
			throw new IllegalArgumentException();
		}
		char[] pchars = phone.toCharArray();
		int phonel = pchars.length;
		int phonesum = 0;
		for (int i = 0; i < phonel; i++) {
			phonesum += (int) Character.getNumericValue(pchars[i]);
		}
		int n = (phonesum % 5) + 1;
		String enpipelinekey = pipelinekey;
		for (int i = 0; i < n; i++) {
			enpipelinekey = encryptMD5(enpipelinekey);
		}
		return decryptDES(key, enpipelinekey);
	}

	/**
	 * Token验证
	 * 
	 * @param phone
	 *            手机号码
	 * @param key
	 *            加密的Token
	 * @param pipelinekey
	 *            和手机端商量好的密钥
	 * @return
	 * @throws Exception
	 */
	public static boolean checkingToken(String phone, String key, String registerkey, String pipelinekey) throws Exception {
		if (null == phone || null == key || pipelinekey == null || registerkey == null || phone.length() != 11 || key.length() != 32 || phone.length() != 11 || registerkey.length() != 4) {
			throw new IllegalArgumentException();
		}
		char[] pchars = phone.toCharArray();
		int phonel = pchars.length;
		int phonesum = 0;
		for (int i = 0; i < phonel; i++) {
			phonesum += (int) Character.getNumericValue(pchars[i]);
		}
		int n = (phonesum % 5) + 1;
		String enpipelinekey = pipelinekey;
		for (int i = 0; i < n; i++) {
			enpipelinekey = encryptMD5(enpipelinekey);
		}
		String deKey = decryptDES(key, enpipelinekey);
		String deKey1 = deKey.substring(0, 4);
		String deKey2 = deKey.substring(4);
		// 倒序输出
		StringBuilder sphone = new StringBuilder();
		for (int i = 0; i < phonel; i++) {
			sphone.append(pchars[i]);
		}
		return registerkey.equals(deKey1) && sphone.toString().equals(deKey2);
	}

}