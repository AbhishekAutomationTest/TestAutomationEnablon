package com.learning.util

import org.testng.*


class MyTestListenerAdapter : TestListenerAdapter() {
    override fun onTestFailure(result: ITestResult) {
        if (result.method.retryAnalyzer != null) {
            val retryAnalyzer: RetryAnalyzer = result.method.retryAnalyzer as RetryAnalyzer
            if (retryAnalyzer.isRetryAvailable()) {
            } else {
                result.status = ITestResult.FAILURE
            }
            Reporter.setCurrentTestResult(result)
        }
    }

    override fun onFinish(context: ITestContext) {
        val failedTestCases = context.failedTests.allResults.iterator()
        while (failedTestCases.hasNext()) {
            println("failedTestCases")
            val failedTestCase = failedTestCases.next()
            val method = failedTestCase.method
            if (context.failedTests.getResults(method).size > 1) {
                println("failed test case remove as dup:" + failedTestCase.testClass.toString())
                failedTestCases.remove()
            } else {
                if (context.passedTests.getResults(method).size > 0) {
                    println("failed test case remove as pass retry:" + failedTestCase.testClass.toString())
                    failedTestCases.remove()
                }
            }
        }
    }
}