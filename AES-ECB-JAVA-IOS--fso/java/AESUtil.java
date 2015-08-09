package cn.creditease.fso.cupid.controller.normandy.util;

import java.security.Key;
import java.security.MessageDigest;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class AESUtil {

	// 密钥算法
	public static final String KEY_ALGORITHM = "AES";

	// 加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * 生成密钥
	 */
	public static String initkey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM); // 实例化密钥生成器
		kg.init(128); // 初始化密钥生成器:AES要求密钥长度为128,192,256位
		SecretKey secretKey = kg.generateKey(); // 生成密钥
		return Base64.encodeBase64String(secretKey.getEncoded()); // 获取二进制密钥编码形式
	}

	/**
	 * 转换密钥
	 */
	public static Key toKey(byte[] key) throws Exception {
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return 加密后的数据
	 * */
	public static String encrypt(String data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key)); // 还原密钥
		// 使用PKCS7Padding填充方式,这里就得这么写了(即调用BouncyCastle组件实现)
		// Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM); // 实例化Cipher对象，它用于完成实际的加密操作
		cipher.init(Cipher.ENCRYPT_MODE, k); // 初始化Cipher对象，设置为加密模式
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes())); // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return 解密后的数据
	 * */
	public static String decrypt(String data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k); // 初始化Cipher对象，设置为解密模式
		return new String(cipher.doFinal(Base64.decodeBase64(data))); // 执行解密操作
	}

	public static String getDigest(String data) throws Exception {
		MessageDigest alga = MessageDigest.getInstance("SHA-1"); // 生成一个
																	// MessageDigest
																	// 类 ,
																	// 确定计算方法
		alga.update(data.getBytes()); // 添加要进行计算摘要的信息
		byte[] digesta = alga.digest(); // 计算出摘要，(对于MD5是16位,SHA是20位)
		System.out.println("本信息摘要是 :" + byte2hex(digesta));
		return byte2hex(digesta);
	}

	public static byte[] getDigestByte(String data) throws Exception {
		MessageDigest alga = MessageDigest.getInstance("SHA-1"); // 生成一个
																	// MessageDigest
																	// 类 ,
																	// 确定计算方法
		alga.update(data.getBytes()); // 添加要进行计算摘要的信息
		byte[] digesta = alga.digest(); // 计算出摘要，(对于MD5是16位,SHA是20位)
		System.out.println("本信息摘要是 :" + byte2hex(digesta));
		return digesta;
	}

	public static boolean isVerify(String data, String digest) throws Exception {
		MessageDigest algb = MessageDigest.getInstance("SHA-1");
		algb.update(data.getBytes());
		String digestResult = byte2hex(algb.digest());
		System.out.println("digest=" + digest);
		System.out.println("veryInfo=" + digestResult);
		if (digest.equals(digestResult)) {
			System.out.println("信息检查正常");
			return true;
		} else {
			System.out.println("摘要不相同");
		}
		return false;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	/*public static void main(String[] args) throws Exception {

		System.out.println(getAESkey());
		// String source
		// = "{\"mobileNo\": \"13800001111\"," +
		// "\"viewSource\": \"112\"} ";
		String source = "{\"cooperationUserId\": \"16\","
				+ "\"viewSource\": \"112\"} ";
		System.out.println("原文：" + source);

		// String key = "nB/D3xKufNrrgXJ3Rr+TXg==";
		// String key = "4QhX9UN2QLIzzIMDy7J/eA==";
		String key = initkey();
		System.out.println("密钥：" + key);

		String encryptData = encrypt(source, key);
		System.out.println("加密：" + encryptData);

		String decryptData = decrypt(encryptData, key);
		System.out.println("解密: " + decryptData);
		String getDigest = getDigest(encryptData);
		System.out.println("摘要是否一致: " + isVerify(encryptData, getDigest));
	}*/
	public static void main(String[] args) throws Exception {
		String aeskey = getAESkey();
		System.out.println(aeskey);
		
		String plainText="sad法律框架234098@）（#*alsdkfjasdfkj";
		System.out.println("plainText:"+plainText);
		System.out.println("++++++++++++++++++++");
		String encodestring = encrypt2(plainText, aeskey);
		System.out.println("encodestring:"+encodestring);
		String decodestring = decrypt2(encodestring, aeskey);
		System.out.println("decodestring:"+decodestring);
		
	}

	/**
	 * 该部分为加密和解析接收到的APP端的数据。
	 * 
	 **/

	public static String encrypt2(String plainText, String keyStr) {
		try {
			if (keyStr == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (keyStr.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = keyStr.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
			IvParameterSpec iv = new IvParameterSpec(
					"0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(plainText.getBytes());
			return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	public static String decrypt2(String encryptData, String keyStr) {
		try {
			// 判断Key是否正确
			if (keyStr == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (keyStr.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = keyStr.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(
					"0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(encryptData);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	/**
	 * 生成随机密码
	 * 
	 * @return
	 */
	public static String getAESkey() {
		UUID uid = UUID.randomUUID();
		String passWord = uid.toString().substring(0, 8);
		return passWord + passWord;
	}

}
