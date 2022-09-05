package com.learning.website.test

import com.learning.pages.webpages.TodomvcPage
import com.learning.util.MyTestListenerAdapter
import com.learning.util.UtilResources
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.html5.LocalStorage
import org.openqa.selenium.html5.WebStorage
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Parameters
import java.net.URI
import java.util.concurrent.TimeUnit


abstract class TestBase {

    lateinit var driver: WebDriver
        private set

    lateinit var todoListPage: TodomvcPage
        private set

    lateinit var localStorage: LocalStorage
        private set

    @Parameters("browser")

    @BeforeClass
    fun setup(browser: String) {
        if (browser.equals("chrome", true)) {
            System.setProperty(
                UtilResources.getProperties("nameChromeDriver"),
                UtilResources.getProperties("pathDriver") + UtilResources.getProperties("exeChromeDriver")
            )
            driver = ChromeDriver()
        }
        else if (browser.equals("firefox", true)) {
            System.setProperty(
                UtilResources.getProperties("nameGeckoDriver"),
                UtilResources.getProperties("pathDriver") + UtilResources.getProperties("exeGeckoDriver")
            )
            driver = FirefoxDriver()
        }
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()
        driver.get(URI(UtilResources.getProperties("pageURL")).toString())

        initializeAllPages()
        initializeStorage()
    }

    @AfterClass
    fun driverClose() {
        driver.close()
    }

    fun initializeStorage() {
        localStorage = (driver as WebStorage).localStorage
    }

    fun initializeAllPages() {
        todoListPage = TodomvcPage(driver)
    }

    fun givenThatTaskListAlreadyExistWithCount(count: Int) {
        clearLocalStorage()

        for (i in 1..count) {
            val taskTitle = "New Task $i"
            todoListPage.addTask(taskTitle)
            todoListPage.expectTotalCountOfItemsLeft(i)
        }
    }

    fun clearLocalStorage() {
        localStorage.clear()
        driver.navigate().refresh()
    }
}