# Test Automation Assignment Enablon

### Application Requirements (for self Understanding):
1. Todo App allows user to perform below actions:
   * User can add new tasks
   * User can modify already added tasks by double-click on it
   * User can delete already added tasks by clicking X button
   * User can view the entire tasks list
   * User can also see the count of total added tasks.

2. Complete & Not-Complete State
   * By default, each new tasks will be added as 'Not Complete' state
   * User can change the state from 'not completed' to 'Completed' & vice versa by clicking on radio button against the task.
   * Once the task is in completed state, it will be shown as 'strikethrough'.
   * If list has atleast 1 completed task, 'clear completed' button would be visible.
   * On click of 'Clear Completed' button, all completed tasks will be deleted from the list.

3. Tasks with duplicate names are allowed to be added
4. Task name can include any of the character including special characters, numerical & alphabets of any language.
5. Task name can not be empty spaces. Minimum length of task name should be atleast 1 character. (e.g. If Existing task is edited with empty value or blank spaces, it should get removed from the list.)
6. Task list is locally stored & should not be persisted after clearing from local storage.
7. Newly added task should appear at the end of the list & list order can not be altered.


## Functional Test Scenarios:

### Positive:
1. verify user can add view edit & delete the task
2. verify user can see the correct count of left items after add or delete the task
3. verify user can mark multiple tasks as completed or not-completed & left-items count are correctly updated
4. verify user can clear all completed tasks from the list
5. verify user should be allowed to add task with duplicate names (not yet implemented)
6. verify task data is persisted across multiple tabs in same browser (not yet implemented)

### Negative: ###
1. verify user can not add a task with empty task name
2. Verify when existing task is updated with empty name, it gets deleted automatically
3. verify task list is not persisted after clearing local storage
4. verify not-completed tasks should not be removed when click clear-completed button

## Testing Framework & Patterns:
### 1. TestNG framework:
#### Why?
1. Good to scale while executing cross-browser testing across multiple devices, browsers, and their versions.
2. Active community for support & future updates.
3. In-built reporting solutions 
4. Good annotation support making it easy to organize & maintain testcases with good readability.

### 2. Page Object Model Pattern with Page Factory:
#### Why?
1. user interactions are abstracted from the core test implementation, making it easy to adapt to the changing project requirements.
2. Since Object repository is seperated from testcases, same can be reused for different purposes & different tools. e.g. Functional test using testNG, Acceptance Testusing Jbehave/Cucmber etc.
3. Page Factory further simplifies locating and initializing the web element variables.

## Test Suites Structure:

![image](https://user-images.githubusercontent.com/42365090/188415087-5ad24f72-4cd5-4718-9b5e-a937bed6ab63.png)

Note: Each Test is configured with total retry count = 3. Test will be considered as failed only if it fails all 3 times.

## Testing Reports:
1. TestNG emailableReporter Listerners are used for reporting which generates a test report in the root folder with name **'emailable-report.html'**
2. Since default test reports doesn't provide the cleanup for retry tests, an additional **MyTestListenerAdapter.class** is implemented to clean up the reports for retry counts. 
3. To analyze the failed test on runtime & enable retry, **RetryAnalyzer** & **AnnotationTransformer** listener is implemented.

![image](https://user-images.githubusercontent.com/42365090/188405730-a5eebf68-14ea-4506-8d8b-c6a261ead40a.png)


## Pre-requisite to run Testcases: 
1. Make sure Chrome Browser version is **104.0.5112.101**
2. Make sure Firefox is installed with any latest version
2. Make sure you have Run Configuration with Below settings: 
1. OutputDirectory --> This is the path where testreport will be generated with name **emailable-report.html**
2. Add a Listeners as **org.testng.reporters.EmailableReporter**
<img width="991" alt="image" src="https://user-images.githubusercontent.com/42365090/188408121-fbe54d2b-c82a-4a59-b114-db1882d602c7.png">

3. Now, Click Run with your TestNG run configuration. 
You can also run by right-click on testng.xml file & select 'Run <configuration name>'  e.g. Run 'TestSuite' 
<img width="512" alt="image" src="https://user-images.githubusercontent.com/42365090/188409176-9708ceae-362d-4588-a151-b89cae728ced.png">
