package com.zhou.medical.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * <p>Title: iSoftStone</p>
 * <p>Description: </p>
 * 随机生成工具类
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: iSoftStone</p>
 *
 * @author Lilibo
 * @version 1.0
 */
public class RandomUtils {

	/**
	 * 所有, 包含所有数字和大小写字母
	 */
	public static final int CHAR_ALL = 1;
	/**
	 * 完整, 包含所有数字和字母
	 */
	public static final int CHAR_INTACT = 2;
	/**
	 * 字母, 包含所有字母
	 */
	public static final int CHAR_LETTER = 3;
	/**
	 * 数字, 包含所有数字
	 */
	public static final int CHAR_NUMBER = 4;
	/**
	 * HEX, 十六进制数字和字母
	 */
	public static final int CHAR_HEX = 5;


	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String INTACTCHAR  = "0123456789abcdefghijklmnopqrstuvwxyz";
	private static final String LETTERCHAR  = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMBERCHAR  = "0123456789";
	private static final String HEXCHAR = "0123456789ABCDEF";

	/**
	 * 生成UUID字符串，去除"-"
	 * 
	 * @return UUID
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	/**
	 * 随机生成字符串, 大小写混合
	 * 
	 * @param length 生成的长度
	 * @return 随机字符串
	 */
	public static String generateRandom(int length) {
		StringBuilder strbuf = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			strbuf.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return strbuf.toString();
	}

	/**
	 * 随机生成字符串, 根据类型输出
	 * 
	 * @param length 生成的长度
	 * @param type 生成的类型
	 * @return 随机字符串
	 */
	public static String generateRandom(int length, int type) {
		String chars = ALLCHAR;
		switch (type) {
		case CHAR_ALL:
			chars = ALLCHAR;
			break;
		case CHAR_INTACT:
			chars = ALLCHAR;
			break;
		case CHAR_LETTER:
			chars = LETTERCHAR;
			break;
		case CHAR_NUMBER:
			chars = NUMBERCHAR;
			break;
		case CHAR_HEX:
			chars = HEXCHAR;
			break;
		default:
			chars = ALLCHAR;
			break;
		}
		StringBuilder strbuf = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			strbuf.append(chars.charAt(random.nextInt(chars.length())));
		}
		return strbuf.toString();
	}

	public static void main(String[] args) {
		// String restus = UUID.randomUUID().toString().toUpperCase();
		String restus = System.currentTimeMillis()+"";
		// String restus = generateUUID().toUpperCase();
		// String restus = generateRandom(4, RandomUtils.CHAR_INTACT);
		System.out.println(restus);
	}
}
