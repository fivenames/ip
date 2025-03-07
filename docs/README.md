# Nimbus User Guide

Nimbus is a chatbot capable of taking down different types of Tasks. It can storage the data into a local text file.
Nimbus also stores the status of each task, modifying the task status as Done/Not Done is possible.

## Adding TODO

Add a TODO task
command keyword: todo
argument: task description

Example: `todo complete CS2113 homework`

Expected output:
```
Task added: complete CS2113 homework.
```

## Adding Deadline

Add a task with a deadline
command keyword: deadline
argument: task description, "/by" deadline

Example: `deadline complete CS2113 homework /by 5PM`

Expected output:
```
Task added: complete CS2113 homework by: 5PM.
```

## Adding Event

Add a task with a duration
command keyword: event
argument: task description, "/from" starting time, "/to" ending time

Example: `event CS2113 exam /from 12 PM /to 2 PM`

Expected output:
```
Task added: CS2113 exam from: 12 PM to: 2 PM.
```

## Listing task

List the current task recorded
command keyword: list
argument: no argument expected

Example: `list`

Expected output:
```
1. [T][X] Complete CS2113 homework
2. [D][] Submit applications (by: 10PM)
3. [E][] CS exam (from: 3PM to: 4PM) 
```
Where [T/D/E] specify the type of task, [X] specify task status

## Delete task

Delete a task from the list
command keyword: delete
argument: an integer representing the index of the task in the task list. Use list function to check the corresponding index.

Example: `delete 2`

Expected output:
```
Task deleted. 
```

## Find task

Find a task from the record
command keyword: find
argument: a keyword that is present in the target task.

Example: `find exam`

Expected output:
```
3. [E][] CS exam (from: 3PM to: 4PM) 
```

## Mark or Unmark task

Mark a task to be complete or incomplete
command keyword: mark/unmark
argument: an integer representing the index of the task in the task list. Use list function to check the corresponding index.

Example: `mark 1`

Expected output:
```
Task marked as done. 
```

Example: `unmark 3`

Expected output:
```
Task marked as not done. 
```

## Save task

Save the data into a txt file locally
command keyword: save
argument: no argument expected

Example: `save`

Expected output:
```
Task list has been saved to ./data/nimbus.txt 
```

## Others

Help: get a list of possible commands
Keyword: -help

Exit: exits the program
Keyword: bye
Note: save command is needed to store the memory