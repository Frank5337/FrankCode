package com.zbl.code.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AesUtils {

    private static final String s_key = "NjM3NzZiNjNmZGVlYg==";

    protected final static Logger logger = LoggerFactory.getLogger(AesUtils.class);

    private static final String keyString = new String(Base64.decodeBase64(s_key));

    public static String getKeyString() {
        return keyString;
    }

    // 加密
    public static String aesEncryptBase64(String sSrc) {
        if (StringUtils.isBlank(sSrc)) {
            return null;
        }
        String sKey = new String(Base64.decodeBase64(s_key));
        try {
            byte[] keyBytes = Arrays.copyOf(sKey.getBytes("ASCII"), 16);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return new String(Base64.encodeBase64(encrypted));//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception ex) {
            logger.error("加密出错:" + ex.getMessage());
            return null;
        }
    }

    /**
     * 判断是否为BASE加密
     */
    public static boolean isBase64(String val) {
        try {
            byte[] key = Base64.decodeBase64(val);
            String strs = new String(key);
            String result = new String(Base64.encodeBase64(strs.getBytes()));
            if (result.equalsIgnoreCase(val)) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            logger.error("判断Base64:" + ex.getMessage());
            return false;
        }
    }

    // 解密
    public static String aesDecryptBase64(String sSrc) {
        if (StringUtils.isBlank(sSrc)) {
            return sSrc;
        }
        String sKey = new String(Base64.decodeBase64(s_key));
        try {
            byte[] keyBytes = Arrays.copyOf(sKey.getBytes("ASCII"), 16);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (IllegalBlockSizeException eibse) {
                logger.error("解密入参出错:" + eibse.getMessage());
                return null;
            } catch (Exception e) {
                logger.error("解密出错:" + e.getMessage());
                return null;
            }
        } catch (Exception ex) {
            logger.error("解密异常:" + ex.getMessage());
            return sSrc;
        }
    }


    // 加密
    public static String aesEncryptBase64(String sSrc, String sKey) {
        try {
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            byte[] keyBytes = Arrays.copyOf(sKey.getBytes("ASCII"), 16);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return new String(Base64.encodeBase64(encrypted));//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception ex) {
            logger.error("加密出错:" + ex.getMessage());
            return null;
        }
    }

    // 解密
    public static String aesDecryptBase64(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return sSrc;
            }
            byte[] keyBytes = Arrays.copyOf(sKey.getBytes("ASCII"), 16);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                logger.error("解密出错:" + e.getMessage());
                return sSrc;
            }
        } catch (Exception ex) {
            logger.error("解密出错:" + ex.getMessage());
            return sSrc;
        }
    }

    // 解密
    public static String aesDecryptHex(String password, String strKey) {
        try {
            byte[] keyBytes = Arrays.copyOf(strKey.getBytes("ASCII"), 16);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cleartext = password.getBytes("UTF-8");
            byte[] encrypted1 = new Hex().decode(cleartext);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("解密出错:" + e.getMessage());
                return password;
            }
        } catch (Exception ex) {
            logger.error("解密出错:" + ex.getMessage());
            return password;
        }
    }

    public static String aesEncryptHex(String password, String strKey) {
        try {
            byte[] keyBytes = Arrays.copyOf(strKey.getBytes("ASCII"), 16);

            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] cleartext = password.getBytes("UTF-8");
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(Hex.encodeHex(ciphertextBytes));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        System.err.println(aesDecryptBase64("NK32"));
    }

}
