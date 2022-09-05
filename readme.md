#Test Automation Assignment Enablon

Application Requirements:

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