package com.learning.pages.webpages

import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import kotlin.test.assertEquals

class TodomvcPage(private val driver: WebDriver) {

    lateinit var wait : WebDriverWait
    lateinit var action : Actions

    @FindBy(xpath=".//todo-app")
    private val todoPage: WebElement? = null

    @FindBy(xpath=".//*")
    private val allPageDOM : List<WebElement>? = null

    @FindBy(xpath = ".//input[contains(@class, 'new-todo')]")
    private val searchBox: WebElement? = null

    @FindBy(xpath = ".//*[@class='todo-list']")
    private val todoListView: WebElement? = null

    @FindBy(xpath = ".//*[@class='todo-list']/li")
    private val todoListItems: List<WebElement>? = null

    @FindBy(xpath = ".//*[@class='todo-list']/li//label")
    private val listItemLabels: List<WebElement>? = null

    @FindBy(xpath = ".//*[@class='todo-list']/li//input[@class = 'toggle']")
    private val listItemToggleInputs: List<WebElement>? = null

    @FindBy(xpath = ".//*[@class='edit']")
    private val listItemEditInput: WebElement? = null

    @FindBy(xpath = ".//*[@class='todo-list']/li//button[@class='destroy']")
    private val listItemDestroyButtons: List<WebElement>? = null

    @FindBy(xpath = ".//*[@class='completed']")
    private val completedListItems: List<WebElement>? = null

    @FindBy(xpath = ".//*[@class='todo-count']")
    private val todoItemsCount: WebElement? = null

    @FindBy(xpath = ".//button[@class='clear-completed']")
    private val clearCompletedButton: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        wait = WebDriverWait(driver, 20)
        action = Actions(driver)
    }

    fun addTask(taskTitle: String) {
        wait.until(ExpectedConditions.visibilityOf(searchBox))
        searchBox?.sendKeys(taskTitle)
        searchBox?.sendKeys(Keys.RETURN)
    }

    fun deleteTaskAtPosition(position : Int) {
        val element = listItemDestroyButtons!![position-1]
        action.moveToElement(listItemLabels!![position-1]).perform()
        wait.until(ExpectedConditions.elementToBeClickable(element))
        element.click()
    }

    fun deleteFirstOccurrenceOfTaskWithTitle(listIndex : Int, taskTitle : String) {
        var isMatched = false
        for (i in 0 until listItemLabels!!.lastIndex) {
            if (listItemLabels[i].text == taskTitle && !isMatched) {
                deleteTaskAtPosition(i)
                isMatched = true
            }
        }
    }

    fun editTaskAt(position: Int, newTaskTitle: String) {
        val item = todoListItems!![position - 1]
        action.doubleClick(item).perform()
        wait.until(ExpectedConditions.elementToBeClickable(listItemEditInput))
        listItemEditInput!!.clear()
        action.doubleClick(item).perform()
        listItemEditInput.sendKeys(newTaskTitle)
        listItemEditInput.sendKeys(Keys.RETURN)
    }

    fun editFirstOccurrenceOfTaskWithTitle(oldTaskTitle: String, newTaskTitle: String) {
        var isMatched = false
        for (i in 0 until listItemLabels!!.lastIndex) {
            if (listItemLabels[i].text == oldTaskTitle && !isMatched) {
                editTaskAt(i, newTaskTitle)
                isMatched = true
            }
        }
    }

    fun expectTaskTitleIsUpdatedAt(position: Int, newTaskTitle: String){
        val itemLabel = listItemLabels?.get(position -1)
        assertEquals(itemLabel?.text, newTaskTitle)
    }

    fun isTodoPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(todoPage))
        assertEquals(todoPage?.isDisplayed, true)
    }

    fun expectCountOfTasksInListToBe(expectedCount: Int) {
        wait.until(ExpectedConditions.visibilityOf(todoListView))
        assertEquals(expectedCount, todoListItems!!.size)
    }

    fun expectTaskIsAddedAsLastItem(taskTitle: String) {
        wait.until(ExpectedConditions.visibilityOf(todoListView))
        assertEquals(listItemLabels!!.last()!!.text, taskTitle)
    }

    fun expectListViewIsGONE() {
        for (element in allPageDOM!!) {
            if (element.tagName == "ul"){
                Assert.fail()
                return
            }
        }
    }

    fun expectTotalCountOfItemsLeft(maxCount: Int) {
        assert(todoItemsCount!!.text.contains(maxCount.toString()))
    }

    fun clickCheckboxToggleAt(position: Int) {
        listItemToggleInputs!![position-1].click()
    }

    fun expectTaskIsMarkedAsCompletedAt(position: Int) {
        val item = todoListItems!![position-1]
        assertEquals(item.getAttribute("class"),"completed")
    }

    fun expectTaskIsMarkedAsNotCompletedAt(position: Int) {
        val item = todoListItems!![position-1]
        assertEquals(item.getAttribute("class"),"")
    }

    fun expectClearCompletedButtonIsVisible() {
        wait.until(ExpectedConditions.visibilityOf(clearCompletedButton))
        assertEquals( true, clearCompletedButton!!.isDisplayed)
    }

    fun clickClearCompletedButtons() {
        wait.until(ExpectedConditions.visibilityOf(clearCompletedButton))
        clearCompletedButton!!.click()
    }

    fun expectTaskIsPresentInList(taskName: String) {
        for (label in listItemLabels!!) {
            if (label.text == taskName){
                return
            }
        }
        Assert.fail()
    }
}

