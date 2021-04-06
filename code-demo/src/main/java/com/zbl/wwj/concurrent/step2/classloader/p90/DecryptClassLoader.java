package com.zbl.wwj.concurrent.step2.classloader.p90;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class DecryptClassLoader extends ClassLoader {

    private static final String DEFAULT_DIR = "D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\classloader3";

    private String dir = DEFAULT_DIR;

    public DecryptClassLoader(ClassLoader parent) {
        super(parent);
    }

    public DecryptClassLoader() {
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        File classFile = new File(dir, classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + "not found under " + dir);
        }
        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + "failed ");
        }
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    private byte[] loadClassBytes(File classFile) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(classFile);
        ) {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(len ^ EncryptUtils.ENCRYPT_FACTOR);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
