import java.util.Scanner;

public class Nimbus {
    private final Task[] tasks;
    private int numTasks;

    public Nimbus() {
        this.tasks = new Task[100];
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

    /*
    private static void echo(String s) {
        if (s.equalsIgnoreCase("bye")) {
            Nimbus.exit();
            return;
        }
        System.out.println(s);
    }
    */

    private static void help() {
        String help = """
                todo [task] -- add a task without a deadline
                deadline [task] /by [time] --add a task with a deadline
                event [task] /from [time] /to [time] --add a task with specific start and end time
                list --list all tasks
                mark [x] --mark task x as done
                unmark [x] --mark task x as not done
                bye --exit""";
        System.out.println(help);
    }

    public void addToDo(String s) {
        this.tasks[this.numTasks] = new Todo(s);
    }

    public void addDeadline(String s) {
        this.tasks[this.numTasks] = new Deadline(s);
    }

    public void addEvent(String s) {
        this.tasks[this.numTasks] = new Event(s);
    }

    public void addTask(String s) {
        String[] command = s.split(" ", 2);

        if (command.length < 2) {
            System.out.println("Invalid command, enter -help for a list of commands");
            return;
        } else if (command[0].equalsIgnoreCase("todo")) {
            this.addToDo(command[1]);
        } else if (command[0].equalsIgnoreCase("deadline")) {
            this.addDeadline(command[1]);
        } else if (command[0].equalsIgnoreCase("event")) {
            this.addEvent(command[1]);
        } else {
            System.out.println("Invalid command, enter -help for a list of commands");
            return;
        }

        this.numTasks++;
        System.out.println("Task added: " + command[1].replace("/", ""));
    }

    public void listTasks() {
        if (this.numTasks == 0) {
            System.out.println("No tasks found.");
        }
        for (int i = 0; i < this.numTasks; i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            tasks[i].print();
        }
    }

    public void markTask(int i) {
        this.tasks[i - 1].setIsDone(true);
        System.out.println("Task marked as done.");
    }

    public void unmarkTask(int i) {
        this.tasks[i - 1].setIsDone(false);
        System.out.println("Task marked as undone.");
    }

    public static void main(String[] args) {
        Nimbus chatBot = new Nimbus();
        Scanner scanner = new Scanner(System.in);

        Nimbus.greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("Enter -help to see the list of commands");
            } else if (input.equalsIgnoreCase("bye")) {
                Nimbus.exit();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                chatBot.listTasks();
            } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                chatBot.markTask(input.split(" ")[1].charAt(0) - '0');
            } else if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
                chatBot.unmarkTask(input.split(" ")[1].charAt(0) - '0');
            } else if (input.equalsIgnoreCase("-help")) {
                Nimbus.help();
            } else {
                chatBot.addTask(input);
            }
            Nimbus.drawLine();
        }
        Nimbus.drawLine();
    }
}