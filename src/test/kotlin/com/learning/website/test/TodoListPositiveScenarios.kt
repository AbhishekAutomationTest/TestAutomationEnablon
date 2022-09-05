package com.learning.website.test

import org.testng.annotations.Test

class TodoListPositiveScenarios() : TestBase() {

    @Test
    fun `verify user can add view edit & delete the task`() {

        clearLocalStorage()
        val taskTitle = "ABC"
        val newTaskTitle = "DEF"

        //add new task
        todoListPage.isTodoPageDisplayed()
        todoListPage.addTask(taskTitle)

        //view newly added task
        todoListPage.expectTaskIsAddedAsLastItem(taskTitle)

        //edit the task
        todoListPage.editTaskAt(1,newTaskTitle)
        todoListPage.expectTaskTitleIsUpdatedAt(1,newTaskTitle)

        //delete the task
        todoListPage.deleteTaskAtPosition(1)
        todoListPage.expectListViewIsGONE()
    }

    @Test
    fun `verify user can see the correct count of left items after add or delete the task`() {
        val maxCount = 3
        givenThatTaskListAlreadyExistWithCount(maxCount)

        todoListPage.expectTotalCountOfItemsLeft(maxCount)

        todoListPage.deleteTaskAtPosition(maxCount)
        todoListPage.expectTotalCountOfItemsLeft(maxCount-1)
    }

    @Test
    fun `verify user can mark multiple tasks as completed or not-completed & left-items count are correctly updated`() {
        val maxCount = 3
        givenThatTaskListAlreadyExistWithCount(maxCount)

        todoListPage.clickCheckboxToggleAt(1)
        todoListPage.expectTaskIsMarkedAsCompletedAt(1)
        todoListPage.expectTotalCountOfItemsLeft(maxCount-1)

        todoListPage.clickCheckboxToggleAt(1)
        todoListPage.expectTaskIsMarkedAsNotCompletedAt(1)
        todoListPage.expectTotalCountOfItemsLeft(maxCount)
    }

    @Test
    fun `verify user can clear all completed tasks from the list`() {
        val maxCount = 6
        givenThatTaskListAlreadyExistWithCount(maxCount)

        todoListPage.clickCheckboxToggleAt(maxCount)
        todoListPage.clickCheckboxToggleAt(maxCount-1)
        todoListPage.expectCountOfTasksInListToBe(maxCount)
        todoListPage.expectTotalCountOfItemsLeft(maxCount-2)

        todoListPage.expectClearCompletedButtonIsVisible()
        todoListPage.clickClearCompletedButtons()
        todoListPage.expectCountOfTasksInListToBe(maxCount-2)
        todoListPage.expectTotalCountOfItemsLeft(maxCount-2)
    }
}