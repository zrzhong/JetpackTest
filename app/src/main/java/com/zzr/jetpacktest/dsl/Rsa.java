package com.zzr.jetpacktest.dsl;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

public class Rsa {

	private static final int MAX_ENCRYPT_BLOCK = 117;
	private static final String ALGORITHM = "RSA";

	/**
	 * 生成密钥对。注意这里是生成密钥对KeyPair，再由密钥对获取公私钥
	 * 
	 * @return
	 * @throws IOException
	 */
	public static java.util.Map<String, String> generateKeyBytes() {
		java.util.Map<String, String> map = new HashMap<String, String>();
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			map.put("publicKey", Base64.encode(publicKey.getEncoded()));
			map.put("privateKey", Base64.encode(privateKey.getEncoded()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return map;
	}

	private static PublicKey getPublicKeyFromX509(String algorithm,
			String bysKey) throws NoSuchAlgorithmException, Exception {
		byte[] decodedKey = Base64.decode(bysKey);
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		return keyFactory.generatePublic(x509);
	}

	public static String encrypt(String content, String key) {
		try {
			PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, key);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pubkey);
			byte plaintext[] = content.getBytes("UTF-8");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			int inputLen = plaintext.length;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher
							.doFinal(plaintext, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher
							.doFinal(plaintext, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			String s=Base64.encode(encryptedData);
			return s.replace("/", "-").replace("+", "*");

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	 * RSA签名
	 * 
	 * @param content
	 *            待签名数据
	 * @param privateKey
	 *            商户私钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名值
	 */
	public static String sign(String content, String privateKey,
			String input_charset) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(input_charset));

			byte[] signed = signature.sign();

			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * RSA验签名检查
	 * 
	 * @param content
	 *            待签名数据
	 * @param sign
	 *            签名值
	 * @param ali_public_key
	 *            支付宝公钥
	 * @param input_charset
	 *            编码格式
	 * @return 布尔值
	 */
	public static boolean verify(String content, String sign,
			String ali_public_key, String input_charset) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(ali_public_key);
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(input_charset));

			boolean bverify = signature.verify(Base64.decode(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	* 解密
	* @param content 密文
	* @param private_key 私钥
	* @param input_charset 编码格式
	* @return 解密后的字符串
	 * @throws Exception 
	*/
	public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);
        java.io.InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;
        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;
            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }
            writer.write(cipher.doFinal(block));
        }
        return new String(writer.toByteArray(), input_charset);
    }


	/**
	 * 得到私钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.decode(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
}
