package com.himanshu.springcachedemo.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class LongRunningService {

    String[] dataArray = new String[]{"Zero", "One", "Two", "Three", "Four", "Five"};

    @Cacheable("data")
    public String longRunningMethod(int index) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return dataArray[index];
    }

    @Cacheable("data2")
    public String longRunningMethod2(int index) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return dataArray[index];
    }
}
