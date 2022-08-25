package com.yy.service.impl;

import com.yy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Date 2022/8/20 15:30
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;

    @Override
    public void transferIn(String inName, Double money) {
        String sql = "update account set balance = balance + ? where username = ?";
        int row = jdbcTemplate.update(sql, money, inName);
        if (row > 0) {
            System.out.println("从" + inName + "账户转入" + money + "成功！");
        } else {
            System.out.println("从" + inName + "账户转入" + money + "失败！");
        }
    }

    @Override
    public void transferOut(String outName, Double money) {
        String sql = "update account set balance = balance - ? where username = ?";
        int i = jdbcTemplate.update(sql, money, outName);
        if (i > 0) {
            System.out.println("从" + outName + "账户转出" + money + "成功！");
        } else {
            System.out.println("从" + outName + "账户转出" + money + "失败！");
        }
    }

    @Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
    public void transferMoney(String outName, String inName, Double money) {
        transferOut(outName, money);
        int x = 10;
        if (x == 10) {
            throw new RuntimeException();
        }

        transferIn(inName, money);
    }

    /**
     * spring 事务REQUIRES_NEW 不起作用的解决方法
     * spring 的事务传播这边就不提了，各种可百度到。但在用REQUIRES_NEW的时候，发现没有起作用。
     * 原因：spring的事务管理通过切面实现，如果直接使用this.方法()或者方法()，不会触发切面中对事务的管理。应使用该方法所在的类的实例.方法()。
     * 解决方案1：需要将两个方法分别写在不同的类里。
     * 解决方案2：方法写在同一个类里，但调用B方法的时候，将service自己注入自己，用这个注入对象来调用B方法。
     *
     * @param inName
     * @param money
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED) // Propagation.REQUIRED：默认的事务传播行为
    public void in(String inName, Double money) {
//            out("张三丰", money); // 如果直接使用this.方法()或者方法(),调用本类中的方法，那么不会触发切面中对该事务的管理，也就是说
        // 被调用的方法不会添加事务管理，没有事务；导致Propagation.REQUIRED失效。

        String sql = "update account set balance = balance + ? where username = ?";
        // 模拟从某人账户转入money
        jdbcTemplate.update(sql, money, inName);
        accountService.out("张三丰", money); // out方法在in方法中被调用，out方法会加入到in方法的事务当中
//        int i = 10 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void out(String outName, Double money) {
        String sql = "update account set balance = balance - ? where username = ?";
        // 模拟从某人账户转money
        jdbcTemplate.update(sql, money, outName);
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
