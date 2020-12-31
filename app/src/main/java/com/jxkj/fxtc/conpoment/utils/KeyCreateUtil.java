package com.jxkj.fxtc.conpoment.utils;

import com.blankj.utilcode.util.EncodeUtils;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * author： TongGuHermit
 * created on： 2019/1/10 18:37
 */

public class KeyCreateUtil {
	private static final String ALGORITHM = "RSA";
	/** 密钥长度，用来初始化 */
	private static final int KEYSIZE = 2048;


	public static void genKeyPair() throws NoSuchAlgorithmException {

		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom secureRandom = new SecureRandom();

		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		keyPairGenerator.initialize(KEYSIZE, secureRandom);
		//keyPairGenerator.initialize(KEYSIZE);

		/** 生成密匙对 */
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		/** 得到公钥 */
		Key publicKey = keyPair.getPublic();

		/** 得到私钥 */
		Key privateKey = keyPair.getPrivate();

		byte[] publicKeyBytes = publicKey.getEncoded();
		byte[] privateKeyBytes = privateKey.getEncoded();

		String publicKeyBase64 = EncodeUtils.base64Encode2String(publicKeyBytes);
		String privateKeyBase64 = EncodeUtils.base64Encode2String(privateKeyBytes);

		System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
		System.out.println("publicKeyBase64:" + publicKeyBase64);

		System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
		System.out.println("privateKeyBase64:" + privateKeyBase64);
	}

}
