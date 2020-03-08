package com.baicheng.classloaderdemo.classloader;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author baicheng
 * @since 2019-11-19 16:07
 */
public class MyClassLoaderTest {

    @Test
    public void testMyClassLoader() throws IllegalAccessException, InstantiationException, MalformedURLException, ClassNotFoundException {
        URL url = new URL("file:/~/.m2/repository/javax/servlet/javax.servlet-api/4.0.1/javax.servlet-api-4.0.1.jar");
        ClassLoader myloader = new URLClassLoader(new URL[]{url});
        Class c = myloader.loadClass("com.baicheng.classloaderdemo.classloader.Test3");
        System.out.println("----------");
        Test3 t3 = (Test3) c.newInstance();
        System.out.println(t3);
    }
}
class Test3 {
    static {
        System.out.println("Test3的静态初始化块执行了！");
    }
}