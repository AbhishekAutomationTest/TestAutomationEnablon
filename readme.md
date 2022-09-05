# Test Automation Assignment Enablon

### Application Understanding (for self Understanding):
1. Todo App allows user to perform below actions:
   a. User can add new tasks
   b. User can modify already added tasks by double-click on it
   c. User can delete already added tasks by clicking X button
   d. User can view the entire tasks list
   e. User can also see the count of total added tasks.

2. Complete & Not-Complete State
   a. By default, each new tasks will be added as 'Not Complete' state
   b. User can change the state from 'not completed' to 'Completed' & vice versa by clicking on radio button against the task.
   c. Once the task is in completed state, it will be shown as 'strikethrough'.
   d. If list has atleast 1 completed task, 'clear completed' button would be visible.
   e. On click of 'Clear Completed' button, all completed tasks will be deleted from the list.

3. Tasks with duplicate names are allowed to be added
4. Task name can include any of the character including special characters, numerical & alphabets of any language.
5. Minimum length of task name should be atleast 1 character. (e.g. If Existing task is edited with empty value, it should get removed from the list.)
6. Task list is locally stored & should not be persisted after clearing from local storage.
7. Newly added task should appear at the end of the list & list order can not be altered.


## Functional Test Scenarios:

### Positive:
1. verify user can add view edit & delete the task
2. verify user can see the correct count of left items after add or delete the task
3. verify user can mark multiple tasks as completed or not-completed & left-items count are correctly updated
4. verify user can clear all completed tasks from the list
5. verify user should be allowed to add task with duplicate names
6. verify task data is persisted across multiple tabs in same browser

### Negative: ###
1. verify user can not add a task with empty task name
2. Verify when existing task is updated with empty name, it gets deleted automatically
3. verify task list is not persisted after clearing local storage
4. verify not-completed tasks should not be removed when click clear-completed button

## Testing Framework & Patterns:
### 1. TestNG framework:
1. Good to scale while executing cross-browser testing across multiple devices, browsers, and their versions.
2. Active community for support & future updates.
3. In-built reporting solutions 
4. Good annotation support making it easy to organize & maintain testcases with good readability.

### 2. Page Object Model Pattern with Page Factory:
1. user interactions are abstracted from the core test implementation, making it easy to adapt to the changing project requirements.
2. Since Object repository is seperated from testcases, same can be reused for different purposes & different tools. e.g. Functional test using testNG, Acceptance Testusing Jbehave/Cucmber etc.
3. Page Factory further simplifies locating and initializing the web element variables.

## Test Suites Structure:

![image](https://user-images.githubusercontent.com/42365090/188407176-5095f8b3-9d17-4fb0-a0f4-89af4efc376d.png)

Note: Each Test is configured with total retry count = 3. If test is passed one out of 3 times, It will be marked as Passed.

## Testing Reports:
1. TestNG reports are used for reporting which generates a report in the root folder with name 'emailable-report.html'
2. Since default test reports doesn't provide the cleanup for retry tests, an additional MyTestListenerAdapter is implemented to clean up the reports for retry counts.

![image](https://user-images.githubusercontent.com/42365090/188405730-a5eebf68-14ea-4506-8d8b-c6a261ead40a.png)
