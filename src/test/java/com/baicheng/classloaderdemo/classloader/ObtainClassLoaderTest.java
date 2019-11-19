package com.baicheng.classloaderdemo.classloader;

import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.HelloWorld;

/**
 * @author baicheng
 * @since 2019-11-19 15:50
 */
public class ObtainClassLoaderTest {

    @Test
    public void testObtainClassLoader() {
        HelloWorld hello = new HelloWorld();
        Class c = hello.getClass();
        ClassLoader loader = c.getClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }

    @Test
    public void testClassLoader() throws ClassNotFoundException {
        ClassLoader loader = HelloWorld.class.getClassLoader();
        System.out.println(loader);
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        loader.loadClass("com.baicheng.classloaderdemo.classloader.Test2");
        //使用Class.forName()来加载类，默认会执行初始化块
//                Class.forName("com.baicheng.classloaderdemo.classloader.Test2");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
//                Class.forName("com.baicheng.classloaderdemo.classloader.Test2", false, loader);
    }
}
class Test2 {
    static {
        System.out.println("静态初始化块执行了！");
    }
}