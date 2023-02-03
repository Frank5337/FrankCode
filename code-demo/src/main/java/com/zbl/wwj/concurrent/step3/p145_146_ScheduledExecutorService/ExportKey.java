package com.zbl.wwj.concurrent.step3.p145_146_ScheduledExecutorService;


import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/21 16:23
 * @Description: 从jks文件中导出私钥和证书
 * @Version: $
 */
public class ExportKey {
    private File keystoreFile;
    private String keyStoreType;
    private char[] password;
    private String alias;
    private File exportedPrivateKeyFile;
    private File exportedPublicKeyFile;

    public static KeyPair getKeyPair(KeyStore keystore, String alias,char[] password) {
        try {
            Key key = keystore.getKey(alias, password);
            if (key instanceof PrivateKey) {
                Certificate cert = keystore.getCertificate(alias);
                PublicKey publicKey = cert.getPublicKey();
                return new KeyPair(publicKey, (PrivateKey) key);
            }
        } catch (UnrecoverableKeyException e) {

        } catch (NoSuchAlgorithmException e) {

        } catch (KeyStoreException e) {

        }
        return null;
    }

    public void exportPrivate() throws Exception {
        KeyStore keystore = KeyStore.getInstance(keyStoreType);
        BASE64Encoder encoder = new BASE64Encoder();
        keystore.load(new FileInputStream(keystoreFile), password);
        KeyPair keyPair = getKeyPair(keystore, alias, password);
        PrivateKey privateKey = keyPair.getPrivate();
        String encoded = encoder.encode(privateKey.getEncoded());
        FileWriter fw = new FileWriter(exportedPrivateKeyFile);
        fw.write("-----BEGIN PRIVATE KEY-----\n");
        fw.write(encoded);
        fw.write("\n");
        fw.write("-----END PRIVATE KEY-----");
        fw.close();
    }
    public void exportCertificate() throws Exception {
        KeyStore keystore = KeyStore.getInstance(keyStoreType);
        BASE64Encoder encoder = new BASE64Encoder();
        keystore.load(new FileInputStream(keystoreFile), password);
        Certificate cert = keystore.getCertificate(alias);
        String encoded = encoder.encode(cert.getEncoded());
        FileWriter fw = new FileWriter(exportedPublicKeyFile);
        fw.write("-----BEGIN CERTIFICATE-----\n");
        fw.write(encoded);
        fw.write("\n");
        fw.write("-----END CERTIFICATE-----");
        fw.close();
    }

    public static void main(String args[]) throws Exception {
        ExportKey export = new ExportKey();
        export.keystoreFile = new File("E:\\software\\qiyewxFiles\\WXWork\\1688851943938006\\Cache\\File\\2021-04\\www.capaa.com.cn.jks");
        export.keyStoreType = "JKS";
        export.password = "123456".toCharArray();
        export.alias = "www.capaa.com.cn";
        export.exportedPrivateKeyFile = new File("C:\\Users\\ThinkPad\\Desktop\\11\\160447\\新建文件夹\\test\\java\\proxyLocal.crt.192.168.242.82_8443");
        export.exportedPublicKeyFile = new File("C:\\Users\\ThinkPad\\Desktop\\11\\160447\\新建文件夹\\test\\java\\proxyLocal.pem.192.168.242.82_8443");
        export.exportPrivate();
        export.exportCertificate();
    }
}