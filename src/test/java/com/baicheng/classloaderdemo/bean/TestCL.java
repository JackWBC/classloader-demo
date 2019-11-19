package com.baicheng.classloaderdemo.bean;

/**
 * @author baicheng
 * @since 2019-11-19 16:36
 *
 * 只在构造器中输出由哪个类加载
 */
public class TestCL {

    public TestCL(){
        System.out.println(this.getClass().getClassLoader().toString());
    }
}
