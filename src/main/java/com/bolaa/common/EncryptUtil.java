package com.bolaa.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具类
 * 
 * md5加密出来的长度是32位
 * 
 * sha加密出来的长度是40位
 */
public class EncryptUtil {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

	public static String METHOD = "AES"; // optional value AES/DES/DESede

	public static String CIPHER_ALGORITHM = "AES"; // optional value
													// AES/DES/DESede

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// md5加密测试
			String md5_1 = md5("中文");
			// sha加密测试
			String sha_1 = sha("中文");

			// aes
			String message = "password";
			String key = "";
			String entryptedMsg = encryptKey(message, key);

			String decryptedMsg = detryptStr(entryptedMsg, key);
			
			//rsa
			generateKey();
			encrypt();
			decrypt();
		} catch (Exception e) {
			logger.error("系统异常,{}",e);	
		}

	}

	/**
	 * 加密
	 * 
	 * @param inputText
	 * @return
	 */
	public static String e(String inputText) {
		return md5(inputText);
	}

	/**
	 * 二次加密
	 * 
	 * @param inputText
	 * @return
	 */
	public static String md5AndSha(String inputText) {
		return sha(md5(inputText));
	}

	/**
	 * md5加密
	 * 
	 * @param inputText
	 * @return
	 */
	public static String md5(String inputText) {
		return encrypt(inputText, "md5");
	}

	/**
	 * sha加密
	 * 
	 * @param inputText
	 * @return
	 */
	public static String sha(String inputText) {
		return encrypt(inputText, "sha-1");
	}

	/**
	 * md5或者sha-1加密
	 * 
	 * @param inputText
	 *            要加密的内容
	 * @param algorithmName
	 *            加密算法名称：md5或者sha-1，不区分大小写
	 * @return
	 */
	private static String encrypt(String inputText, String algorithmName) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes("UTF8"));
			byte s[] = m.digest();
			// m.digest(inputText.getBytes("UTF8"));
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			logger.error("系统异常,{}",e);	
		} catch (UnsupportedEncodingException e) {
			logger.error("系统异常,{}",e);	
		}
		return encryptText;
	}

	/**
	 * 返回十六进制字符串
	 * 
	 * @param arr
	 * @return
	 */
	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	private static Key getSecretKey(String key) throws Exception {
		SecretKey securekey = null;
		if (key == null) {
			key = "";
		}
		KeyGenerator keyGenerator = KeyGenerator.getInstance(METHOD);
		keyGenerator.init(new SecureRandom(key.getBytes()));
		securekey = keyGenerator.generateKey();
		return securekey;
	}

	public static String encryptKey(String data, String key) throws Exception {
		SecureRandom sr = new SecureRandom();
		Key securekey = getSecretKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		byte[] bt = cipher.doFinal(data.getBytes());
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}

	public static String detryptStr(String message, String key) throws Exception {
		SecureRandom sr = new SecureRandom();
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		Key securekey = getSecretKey(key);
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		byte[] res = new BASE64Decoder().decodeBuffer(message);
		res = cipher.doFinal(res);
		return new String(res);
	}

	public static void generateKey() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			KeyPair kp = kpg.genKeyPair();
			PublicKey pbkey = kp.getPublic();
			PrivateKey prkey = kp.getPrivate();
			// 保存公钥
			FileOutputStream f1 = new FileOutputStream("pubkey.dat");
			ObjectOutputStream b1 = new ObjectOutputStream(f1);
			b1.writeObject(pbkey);
			// 保存私钥
			FileOutputStream f2 = new FileOutputStream("privatekey.dat");
			ObjectOutputStream b2 = new ObjectOutputStream(f2);
			b2.writeObject(prkey);
		} catch (Exception e) {
		}
	}

	public static void encrypt() throws Exception {
		String s = "Hello World!";
		// 获取公钥及参数e,n
		FileInputStream f = new FileInputStream("pubkey.dat");
		ObjectInputStream b = new ObjectInputStream(f);
		RSAPublicKey pbk = (RSAPublicKey) b.readObject();
		BigInteger e = pbk.getPublicExponent();
		BigInteger n = pbk.getModulus();
		// 获取明文m
		byte ptext[] = s.getBytes("UTF-8");
		BigInteger m = new BigInteger(ptext);
		// 计算密文c
		BigInteger c = m.modPow(e, n);
		// 保存密文
		String cs = c.toString();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("encrypt.dat")));
		out.write(cs, 0, cs.length());
		out.close();
	}

	public static void decrypt() throws Exception {
		// 读取密文
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("encrypt.dat")));
		String ctext = in.readLine();
		BigInteger c = new BigInteger(ctext);
		// 读取私钥
		FileInputStream f = new FileInputStream("privatekey.dat");
		ObjectInputStream b = new ObjectInputStream(f);
		RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
		BigInteger d = prk.getPrivateExponent();
		// 获取私钥参数及解密
		BigInteger n = prk.getModulus();
		BigInteger m = c.modPow(d, n);
		// 显示解密结果
		byte[] mt = m.toByteArray();
		for (int i = 0; i < mt.length; i++) {
			//System.out.print((char) mt[i]);
		}
	}

}
