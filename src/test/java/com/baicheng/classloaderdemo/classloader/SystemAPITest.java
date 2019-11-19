package com.baicheng.classloaderdemo.classloader;

import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author baicheng
 * @since 2019-11-19 15:43
 */
public class SystemAPITest {

    /**
     * System API doc文档 打印
     */
    @Test
    public void testPrintSystemAPI() {

        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }
}
