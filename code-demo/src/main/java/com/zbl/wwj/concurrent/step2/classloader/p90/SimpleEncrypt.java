package com.zbl.wwj.concurrent.step2.classloader.p90;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class SimpleEncrypt {

    private static final String plain = "Hello ClassLoader";

    private static final byte ENCRYPT_FACTOR = (byte) 0xff;

    public static void main(String[] args) {
        byte[] bytes = plain.getBytes();
        byte[] encrypt = encrypt(bytes);
        System.out.println(new String(encrypt));
        byte[] e2 = encrypt(encrypt);
        System.out.println(new String(e2));

    }

    private static byte[] encrypt(byte[] bytes) {
        byte[] encrypt = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            encrypt[i] = (byte) (bytes[i] ^ ENCRYPT_FACTOR);
        }
        return encrypt;
    }

}
