package com.yeob.xunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCase {
    protected final String name;

    public TestCase(String name) {
        this.name = name;
    }

    public void run(TestResult result) {
        result.testStarted();

        setUp();

        try {
            Method method = this.getClass().getMethod(this.name);
            method.invoke(this);
        } catch (Exception e) {
            result.testFailed();
        }

        tearDown();

    }

    public void setUp() {

    }

    public void tearDown() {

    }

}
