package com.yy.service.impl;

import com.yy.service.OrderService;

/**
 * @Description 测试jdk动态代理
 * @Date 2022/8/20 23:53
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void test1() {
        System.out.println("--执行test1--");
        test2();
    }

    @Override
    public void test2() {
        System.out.println("--执行test2--");
    }
}
