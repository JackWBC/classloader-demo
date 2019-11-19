package com.baicheng.classloaderdemo.classloader1;

import java.io.*;

/**
 * @author baicheng
 * @since 2019-11-19 16:38
 *
 * 沿用双亲委派
 *
 * 重写findClass方法，此方法要做的事情是读取Test.class字节流并传入父类的defineClass方法即可。
 * 然后就可以通过自定义类加载器ClassLoaderTest对TestCL.class进行加载,完成加载后会输出“TestLoader”
 */
public class ClassLoaderTest extends ClassLoader {

    private String name;

    public ClassLoaderTest(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
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
        ClassLoaderTest loader = new ClassLoaderTest(
                ClassLoaderTest.class.getClassLoader(), "ClassLoaderTest");
        Class clazz;
        try {
            clazz = loader.loadClass("com.baicheng.classloaderdemo.bean.TestCL");
            Object object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
