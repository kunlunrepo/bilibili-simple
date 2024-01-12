package com.bilibili.service.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;


/**
 * description : RSA加解密工具类
 *
 * @author kunlunrepo
 * date :  2024-01-12 14:28
 */
public class RSAUtil {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQk33iNdA8Iey7J6XrBsidqn6u8EDLWPHsfEUgLQ3qiTikhPKDTzZkpAfU/O0x6NvSKa7Dp0+uqWT3vnW1De0+3u8mCYdVfOdH94VG4xg5U5UrRJei8HhPiXuvKQ+6NBtebCCW5adZ4pBgOiU14cJLhVmm+dYiLo3IDD5LqrlomQIDAQAB";

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJCTfeI10Dwh7LsnpesGyJ2qfq7wQMtY8ex8RSAtDeqJOKSE8oNPNmSkB9T87THo29IprsOnT66pZPe+dbUN7T7e7yYJh1V850f3hUbjGDlTlStEl6LweE+Je68pD7o0G15sIJblp1nikGA6JTXhwkuFWab51iIujcgMPkuquWiZAgMBAAECgYA1UT9mciQWWQh9yNRmhXzssFjB2TZ8B5RIe1fe0t7D9NEf0yvAgzDzEo8U3CX5dv/CVL7vxr8bEbt7phCwsa8hJiLEOr7hLZaJzXVTbvfqb91oCZGNkqDQ3NJfGBMVgUmltEYF2Bbk3U0NDyat+Gu54tRd2OH+adJYKsD0XYeDBQJBAN5FE8E04A4FA1q8mQbVTSVJDYIEJwOrdC0r3iZ7za5CyXGk+br8pFalRePFaksRGdN32+mYhDKVNrNHspAObVMCQQCmhBsD+xiWrmpnrzeIfCW1cX8qRC3/RMkq0ACw3l6YedNFdN2Tb5WsRHmcbCI9y8mfLHiG/X1R+zHZKG67EKjjAkAmvAkGSY2mQ89i160fWLq5/bIh71FRPWbgnF15fWfJr4/lgyeWI4MMKn80g2nTrSZACQpE+jRHkGNY+OywWCNLAkEAli5nvztkfeJpDYK2b16pE/B9ZL2BTs3XMcnQFbU5VAPsTKSOgz8MmwZXOIE+kMWP3wPY4McXlC0eVGFnHUh1SQJAeAl3RPk+XbZDMYfPkStRJwocG9Ap+88mwTgR1I7uPzZ1aM84/WsQskiVMXv2SZLmMWvYtnhIKosL6IACp2AcDA==";


    // 获取公钥
    public static RSAPublicKey getPublicKey() throws Exception {
        // base64解码
        byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new java.security.spec.X509EncodedKeySpec(decoded));
    }

    // 获取私钥
    public static RSAPrivateKey getPrivateKey() throws Exception {
        // base64解码
        byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new java.security.spec.PKCS8EncodedKeySpec(decoded));
    }

    // 将指定字符串加密
    public static String encrypt(String source) throws Exception {
        // 获取Cipher
        Cipher cipher = getEncryptCipher();
        // 加密
        return Base64.encodeBase64String(cipher.doFinal(source.getBytes(StandardCharsets.UTF_8)));
    }

    // 将指定字符串解密
    public static String decrypt(String encrypted) throws Exception {
        // 获取Cipher
        Cipher cipher = getDecryptCipher();
        // 解码
        byte[] inputByte = Base64.decodeBase64(encrypted.getBytes(StandardCharsets.UTF_8));
        // 解密
        return new String(cipher.doFinal(inputByte));
    }

    // 生成解密Cipher (Cipher 在Java加密处理中是一个核心类，主要用于执行各种密码学操作，如加密和解密数据)
    public static Cipher getDecryptCipher() throws Exception {
        byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
        // 生成私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new java.security.spec.PKCS8EncodedKeySpec(decoded));
        // 生成Cipher
        Cipher cipher = Cipher.getInstance("RSA");
        // 初始化 (模式：解密)
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher;
    }

    // 生成加密Cipher
    public static Cipher getEncryptCipher() throws Exception {
        byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
        // 生成公钥
        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new java.security.spec.X509EncodedKeySpec(decoded));
        // 生成Cipher
        Cipher cipher = Cipher.getInstance("RSA");
        // 初始化 (模式：加密)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = RSAUtil.encrypt("123456");
        System.out.println(encrypt);

        String decoded = RSAUtil.decrypt(encrypt);
        System.out.println(decoded);
    }

}
