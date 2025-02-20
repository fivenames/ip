package Nimbus;

import Nimbus.Task.Deadline;
import Nimbus.Task.Event;
import Nimbus.Task.Task;
import Nimbus.Task.Todo;
import java.util.ArrayList;

import java.util.Scanner;

public class Nimbus {
    private final ArrayList<Task> tasks;
    private int numTasks;

    public Nimbus() {
        this.tasks = new ArrayList<Task>();
        this.numTasks = 0;
    }

    private static void greet() {
        String logo = """
                 _   _    _____    __  __   ____     _   _    ____ \s
                | \\ | |  |_   _|  |  \\/  |  | __ )  | | | |  / ___|\s
                |  \\| |    | |    | |\\/| |  |  _ \\  | | | |  \\___ \\\s
                | . ` |   _| |_   | |  | |  | |_) | | |_| |   ___) |
                |_| \\_|  |_____|  |_|  |_|  |____/   \\___/   |____/
                """;
        Nimbus.drawLine();
        System.out.println(logo);
        System.out.println("""
                Hello! I'm Nimbus
                What can I do for you?
                """);
        Nimbus.drawLine();
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void drawLine() {
        System.out.println("-----------------------------------------------------");
    }

    private static void help() {
        String help = """
                todo [task] -- add a task without a deadline
                deadline [task] /by [time] --add a task with a deadline
                event [task] /from [time] /to [time] --add a task with specific start and end time
                list --list all tasks
                mark [x] --mark task x as done
                unmark [x] --mark task x as not done
                delete [x] --delete task x
                bye --exit""";
        System.out.println(help);
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
    }

    public void listTasks() {
        if (this.numTasks == 0) {
            System.out.println("No tasks found.");
            return;
        }
        for (int i = 0; i < this.numTasks; i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            tasks.get(i).print();
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

    public static void main(String[] args) {
        Nimbus chatBot = new Nimbus();
        Scanner scanner = new Scanner(System.in);

        Nimbus.greet();
        label:
        while (true) {
            String input = scanner.nextLine();
            try {
                CommandParser parser = new CommandParser(input, chatBot.numTasks);
                switch (parser.getCommandWord()) {
                case "bye":
                    Nimbus.exit();
                    break label;
                case "list":
                    chatBot.listTasks();
                    break;
                case "mark":
                    chatBot.markTask(Integer.parseInt(parser.getArguments()));
                    break;
                case "unmark":
                    chatBot.unmarkTask(Integer.parseInt(parser.getArguments()));
                    break;
                case "delete":
                    chatBot.deleteTask(Integer.parseInt(parser.getArguments()));
                    break;
                case "-help":
                    Nimbus.help();
                    break;
                default:
                    chatBot.addTask(parser.getCommandWord(), parser.getArguments());
                    break;
                }
            } catch (NimbusException e) {
                e.handleException();
            }
            Nimbus.drawLine();
        }
        Nimbus.drawLine();
    }
}