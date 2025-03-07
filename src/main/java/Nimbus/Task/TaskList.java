package Nimbus.Task;

import java.util.*;

public class TaskList {
    private final ArrayList<Task> tasks;
    private int numTasks;

    public TaskList(ArrayList<Task> tasks, int numTasks) {
        this.tasks = tasks;
        this.numTasks = numTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumTasks() {
        return numTasks;
    }

    public void addToDo(String s) {
        this.tasks.add(this.numTasks, new Todo(s));
    }

    public void addDeadline(String s) {
        this.tasks.add(this.numTasks, new Deadline(s));
    }

    public void addEvent(String s) {
        this.tasks.add(this.numTasks, new Event(s));
    }

    public void addTask(String command, String arg) {
        switch (command) {
        case "todo" -> this.addToDo(arg);
        case "deadline" -> this.addDeadline(arg);
        case "event" -> this.addEvent(arg);
        }

        this.numTasks++;
        System.out.println("Task added: " + arg.replace("/", ""));
        System.out.println("Current number of Tasks: " + this.numTasks);
    }

    public void listTasks() {
        if (this.numTasks == 0) {
            System.out.println("No tasks found.");
            return;
        }
        for (int taskIndex = 0; taskIndex < this.numTasks; taskIndex++) {
            System.out.print(taskIndex + 1);
            System.out.print(". ");
            tasks.get(taskIndex).print();
        }
    }

    public void findTask(String arg){
        boolean found = false;
        for (int taskIndex = 0; taskIndex < this.numTasks; taskIndex++){
            if(tasks.get(taskIndex).toString().contains(arg)){
                found = true;
                System.out.print(taskIndex + 1);
                System.out.print(". ");
                System.out.println(tasks.get(taskIndex));
            }
        }

        if(!found){
            System.out.println("Task with the keyword is not found.");
        }
    }

    public void markTask(int i) {
        this.tasks.get(i - 1).setIsDone(true);
        System.out.println("Task marked as done.");
    }

    public void unmarkTask(int i) {
        this.tasks.get(i - 1).setIsDone(false);
        System.out.println("Task marked as undone.");
    }

    public void deleteTask(int i) {
        this.tasks.remove(i - 1);
        this.numTasks--;
        System.out.println("Task deleted.");
    }
}