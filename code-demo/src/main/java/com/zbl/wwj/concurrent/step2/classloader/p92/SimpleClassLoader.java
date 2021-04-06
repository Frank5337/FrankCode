package com.zbl.wwj.concurrent.step2.classloader.p92;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class SimpleClassLoader extends ClassLoader {

    private static final String DEFAULT_DIR = "D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\classloader4";

    private String dir = DEFAULT_DIR;

    private String classLoaderName;

    /**
     * @param name xxx.xxx.xxx.x
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        File classFile = new File(dir, classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + " not found under " + dir);
        }

        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + "failed ");
        }

        return this.defineClass(name, classBytes, 0, classBytes.length);


    }

    /**
     * 重写loadClass 就能打破双亲委派机制
     *
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = null;
        if (name.startsWith("java.")) {
            try {
                //系统的就会双亲委派了
                ClassLoader system = ClassLoader.getSystemClassLoader();
                System.out.println("system: " + system);
                clazz = system.loadClass(name);
                if (clazz != null) {
                    if (resolve) {
                        resolveClass(clazz);
                    }
                    return clazz;
                }
            } catch (Exception e) {
                //ignore
            }
        }
        try {
            clazz = findClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (clazz == null && getParent() != null) {
            clazz = getParent().loadClass(name);
        }
        return clazz;
    }

    private byte[] loadClassBytes(File classFile) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(classFile);
        ) {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SimpleClassLoader() {
        super();
    }

    public SimpleClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public SimpleClassLoader(String classLoaderName, ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }

    public void setClassLoaderName(String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }
}
