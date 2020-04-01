/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.utils;

/**
 * 加密工具类
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @date 03/26/2020 11:07 上午
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 编码工具类
 * 1.将byte[]转为各种进制的字符串
 * 2.base 64 encode
 * 3.base 64 decode
 * 4.获取byte[]的md5值
 * 5.获取字符串md5值
 * 6.结合base64实现md5加密
 * 7.AES加密
 * 8.AES加密为base 64 code
 * 9.AES解密
 * 10.将base 64 code AES解密
 * @author uikoo9
 * @version 0.0.7.20140601
 */
@Slf4j
public class DESUtils {

    public static  KeyGenerator kgen ;
    static  {
        try {
            kgen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            log.warn(e.getMessage());
        }
    }




    public static void main(String[] args) throws Exception {

        /*String content = "我";
        System.out.println("加密前：" + content);

        String key = "V10010";
        System.out.println("加密密钥和解密密钥：" + key);

        String encrypt = aesEncrypt(content, key);
        System.out.println("加密后：" + encrypt);

        String decrypt = aesDecrypt(encrypt, key);
        System.out.println("解密后：" + decrypt);*/
        String content = "我sadfdsaf";

        Long time = System.currentTimeMillis();
        String a1 = DESUtils.aesEncrypt(content, "abc");
        String aa1= DESUtils.aesDecrypt(a1,"abc");
        System.out.println(System.currentTimeMillis()-time);


        long beginTime=System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            String a = DESUtils.aesEncrypt(content, "abc");
            System.out.println(a);
            String aa = DESUtils.aesDecrypt(a,"abc");
            System.out.println(aa);
        }
        long beginTime2=System.currentTimeMillis();
        System.out.println("----------------------->"+(beginTime-beginTime2));
    }

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    /**
     * 获取byte[]的md5值
     * @param bytes byte[]
     * @return md5
     * @throws Exception
     */
    public static byte[] md5(byte[] bytes) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);

        return md.digest();
    }

    /**
     * 获取字符串md5值
     * @param msg
     * @return md5
     * @throws Exception
     */
    public static byte[] md5(String msg) throws Exception {
        return StringUtils.isEmpty(msg) ? null : md5(msg.getBytes());
    }

    /**
     * 结合base64实现md5加密
     * @param msg 待加密字符串
     * @return 获取md5后转为base64
     * @throws Exception
     */
    public static String md5Encrypt(String msg) throws Exception{
        return StringUtils.isEmpty(msg) ? null : base64Encode(md5(msg));
    }

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception{
        if (kgen == null) {
            kgen = KeyGenerator.getInstance("AES");
        }
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(encryptKey.getBytes());
        kgen.init(128, secureRandom);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));

    }

    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey){
        try {
            return bytesToHexString(aesEncryptToBytes(content, encryptKey));
        } catch (Exception e) {
            log.warn("信息加密失败!", e);
        }
        return null;
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        if (kgen == null) {
            kgen = KeyGenerator.getInstance("AES");
        }
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
        secureRandom.setSeed(decryptKey.getBytes());
        kgen.init(128, secureRandom);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey)  {
        try {
            return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(hexStringToBytes(encryptStr), decryptKey);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    // 将字节转化为字符
    public static char byteToChar(byte[] b) {
        int s = 0;
        if (b[0] > 0) {
            s += b[0];
        }
        if (b[0] < 0) {
            s += 256 + b[0];
        }
        s *= 256;
        if (b[1] > 0) {
            s += b[1];
        }
        if (b[1] < 0) {
            s += 256 + b[1];
        }
        char ch = (char) s;
        return ch;
    }

    public static String byteToHexString(byte b) {
        String hex = "";
        hex = Integer.toHexString(b & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return hex;
    }

    public static String bytesToHexString(byte[] bs) {
        StringBuffer sb = new StringBuffer();
        String hex = "";
        for (int i = 0; i < bs.length; i++) {
            hex = Integer.toHexString(bs[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String in) {
        byte[] arrB = in.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

}
