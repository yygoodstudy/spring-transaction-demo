package com.yy.service;

public interface AccountService {
    void transferIn(String inName, Double money);

    void transferOut(String outName, Double money);

    void in(String inName, Double money);

    void out(String outName, Double money);

}
