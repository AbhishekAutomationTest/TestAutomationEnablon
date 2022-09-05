package com.learning.util

import org.testng.IRetryAnalyzer
import org.testng.ITestResult
import java.util.concurrent.atomic.AtomicInteger





class RetryAnalyzer : IRetryAnalyzer {
    var counter = 0
    var retryLimit = 2

    var count = AtomicInteger(retryLimit)

    fun isRetryAvailable(): Boolean {
        return count.toInt() > 0
    }
    /*
	 * (non-Javadoc)
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 *
	 * This method decides how many times a test needs to be rerun.
	 * TestNg will call this method every time a test fails. So we
	 * can put some code in here to decide when to rerun the test.
	 *
	 * Note: This method will return true if a tests needs to be retried
	 * and false it not.
	 *
	 */
    override fun retry(result: ITestResult): Boolean {
        var retry = false
        if (isRetryAvailable()) {
            println("Going to retry test case: " + result.method + ", " + (retryLimit - count.toInt() + 1) + " out of " + retryLimit);
            retry = true
            count.decrementAndGet()
        }
        return retry
    }
}