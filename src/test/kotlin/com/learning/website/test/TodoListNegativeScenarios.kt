package com.learning.website.test

import org.testng.annotations.Test

class TodoListNegativeScenarios() : TestBase() {

    @Test
    fun `verify user can not add a task with empty task name`() {

        val taskTitle = "   "
        val initialTaskCount = 3
        givenThatTaskListAlreadyExistWithCount(initialTaskCount)

        //while adding new task with empty spaces
        todoListPage.isTodoPageDisplayed()
        todoListPage.addTask(taskTitle)
        todoListPage.expectCountOfTasksInListToBe(initialTaskCount)
    }

    @Test
    fun `Verify when existing task is updated with empty name, it gets deleted automatically`(){
        val taskTitle = "   "
        val initialTaskCount = 3
        givenThatTaskListAlreadyExistWithCount(initialTaskCount)

        //while editing existing task
        todoListPage.isTodoPageDisplayed()
        todoListPage.editTaskAt(1,taskTitle)
        todoListPage.expectCountOfTasksInListToBe(initialTaskCount - 1)
    }

    @Test
    fun `verify task list is not persisted after clearing local storage`() {

        val taskTitle = ""
        val initialTaskCount = 3
        givenThatTaskListAlreadyExistWithCount(initialTaskCount)
        clearLocalStorage()
        todoListPage.expectListViewIsGONE()
    }

    @Test
    fun `verify not-completed tasks should not be removed when click clear-completed button`() {
        val initialTaskCount = 3
        givenThatTaskListAlreadyExistWithCount(initialTaskCount)

        //add 1 task
        val taskName = "Not Completed Task"
        todoListPage.addTask(taskName)

        todoListPage.clickCheckboxToggleAt(1)
        todoListPage.clickCheckboxToggleAt(2)
        todoListPage.clickClearCompletedButtons()
        todoListPage.expectTotalCountOfItemsLeft(initialTaskCount-1)
        todoListPage.expectTaskIsPresentInList(taskName)
    }
}