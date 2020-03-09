package com.yeob.xunit;

import com.yeob.xunit.annotation.Test;

public class TestCaseTest extends TestCase {

    public TestCaseTest(String name) {
        super(name);
    }

    public static TestSuite suite() {
        return new TestSuite(TestCaseTest.class);

//        TestSuite suite = new TestSuite(TestCaseTest.class);
//        suite.add(new TestCaseTest("testTemplateMethod"));
//        suite.add(new TestCaseTest("testResult"));
//        suite.add(new TestCaseTest("testFailedResultFormatting"));
//        suite.add(new TestCaseTest("testFailedResult"));
//        suite.add(new TestCaseTest("testSuite"));

//        return suite;
    }

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @Test
    public void testTemplateMethod() {
        WasRun wasRun = new WasRun("testMethod");
        TestResult result = new TestResult();
        wasRun.run(result);
        Assert.assertEquals("setUp testMethod tearDown", wasRun.log);
    }

    @Test
    public void result() {
        WasRun wasRun = new WasRun("testMethod");
        TestResult result = new TestResult();
        wasRun.run(result);
        Assert.assertEquals("1 run, 0 failed", result.getSummary());
    }

    @Test
    public void testFailedResultFormatting() {
        TestResult result = new TestResult();
        result.testStarted();
        result.testFailed();
        Assert.assertEquals("1 run, 1 failed", result.getSummary());
    }

    @Test
    public void testFailedResult() {
        WasRun wasRun = new WasRun("testBrokenMethod");
        TestResult testResult = new TestResult();
        wasRun.run(testResult);
        Assert.assertEquals("1 run, 1 failed", testResult.getSummary());
    }

    @Test
    public void testSuite() {
        TestSuite suite = new TestSuite();
        suite.add(new WasRun("testMethod"));
        suite.add(new WasRun("testBrokenMethod"));
        TestResult testResult = new TestResult();
        suite.run(testResult);

        Assert.assertEquals("2 run, 1 failed", testResult.getSummary());
    }
}
