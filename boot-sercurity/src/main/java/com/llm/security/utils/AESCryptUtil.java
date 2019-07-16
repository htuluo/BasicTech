package com.llm.security.utils;

/**
 * @description:
 * @author: luolm
 * @createTime： 10:57  2018/1/24
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author luolm
 * @version V1.0
 * @desc AES 加密工具类
 */
@Slf4j
public class AESCryptUtil {

    private static final String KEY_ALGORITHM = "AES";

    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * 默认加密key
     */
    private static final String DEFAULT_ENCRYPT_KEY = "2En!HE%SYQc#W-B3";

    /**
     *偏移量
     */
    private static final String IV_STRING = "16-Bytes--String";

    /**
     * 加密，默认加密key
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        return encrypt(content, DEFAULT_ENCRYPT_KEY);
    }

    /**
     * 解密，默认加密Key
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        return decrypt(content, DEFAULT_ENCRYPT_KEY);
    }


    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");
            // 注意，为了能与 iOS 统一, 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
            byte[] enCodeFormat = password.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

            // 指定加密的算法、工作模式和填充方式
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(byteContent);

            //通过Base64转码返回
            return new BASE64Encoder().encode(encryptedBytes);
        } catch (Exception ex) {
            log.error("AESCryptUtil#encrypt catch error,msg-{},", ex.getLocalizedMessage(), ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {

        try {
            //实例化

            byte[] enCodeFormat = password.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            //执行操作
            byte[] result = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            log.error("AESCryptUtil#decrypt catch error,msg-{},", ex.getLocalizedMessage(), ex);
        }

        return null;
    }


}
