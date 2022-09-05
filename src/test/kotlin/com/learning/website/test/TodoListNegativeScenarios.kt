package com.learning.website.test

import org.testng.annotations.Test

class TodoListNegativeScenarios() : TestBase() {

    @Test
    fun `verify task name can not contain only spaces while add or edit a task`() {

        val taskTitle = "   "
        val initialTaskCount = 3
        givenThatTaskListAlreadyExistWithCount(initialTaskCount)

        //while adding new task
        todoListPage.isTodoPageDisplayed()
        todoListPage.addTask(taskTitle)
        todoListPage.expectCountOfTasksInListToBe(initialTaskCount)

        //while editing existing task
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
    fun `verify task with duplicate Names should not be allowed`() {
    }
}