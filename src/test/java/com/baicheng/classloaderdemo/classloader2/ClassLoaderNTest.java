package com.baicheng.classloaderdemo.classloader2;

import java.io.*;

/**
 * @author baicheng
 * @since 2019-11-19 17:16
 *
 * 默认的loadClass方法是实现了双亲委派机制的逻辑，即会先让父类加载器加载，当无法加载时才由自己加载。
 * 这里为了破坏双亲委派机制必须重写loadClass方法，即这里先尝试交由System类加载器加载，
 * 加载失败才会由自己加载。它并没有优先交给父类加载器，这就打破了双亲委派机制
 */
public class ClassLoaderNTest extends ClassLoader {

    private String name;

    public ClassLoaderNTest(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        ClassLoader system = getSystemClassLoader();
        try {
            clazz = system.loadClass(name);
        } catch (Exception e) {
            //ignore
        }
        if (clazz != null) {
            return clazz;
        }
        return findClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(new File(this.getClass().getResource("/").getPath() + "TestCL.class"));
            int c = 0;
            while (-1 != (c = is.read())) {
                baos.write(c);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) {
        ClassLoaderNTest loader = new ClassLoaderNTest(
                ClassLoaderNTest.class.getClassLoader(), "ClassLoaderNTest");
        Class clazz;
        try {
            clazz = loader.loadClass("com.baicheng.classloaderdemo.bean.TestCL");
            Object object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
