package com.yeob.xunit;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSuite implements Test {
    List<Test> tests = new ArrayList<>();

    public TestSuite(Class<? extends TestCase> testClass) {
        Arrays.stream(testClass.getDeclaredMethods())
//                .filter(method -> method.getName().startsWith("test"))
                .filter(method -> method.getAnnotation(com.yeob.xunit.annotation.Test.class) != null)
                .forEach(method -> {
                    try {
                        add(testClass.getConstructor(String.class).newInstance(method.getName()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public TestSuite() {

    }

    public void add(Test test) {
        tests.add(test);
    }

    public void run(TestResult result) {
        tests.forEach(test -> {
            test.run(result);
        });
    }
}
