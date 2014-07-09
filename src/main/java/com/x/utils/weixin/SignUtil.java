package com.x.utils.weixin;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * @ClassName: SignUtil.java
 * @Description: 请求校验工具类
 * 
 * @author Kenmy
 * @version V1.0
 * @Date 2014-1-8下午10:41:29
 */
public class SignUtil {

	private static Logger log = Logger.getLogger(SignUtil.class);

	// 与接口配置信息中的Token要一致
	private static String token = "11111";

	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return: boolean
	 * @author: Kenmy
	 * @Date: 2014-1-8 下午10:53:40
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token、timestamp、nonce三个参数进行字典排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个三叔字符串拼接成一个字符串经行SHA1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (Exception e) {
			log.error(e);
		}
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return: String
	 * @author: Kenmy
	 * @Date: 2014-1-8 下午10:51:42
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * @Title: byteToHexStr
	 * @Description: 将字节转换为十六进制字符串
	 * @param mByte
	 * @return: String
	 * @author: Kenmy
	 * @Date: 2014-1-8 下午10:49:49
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
