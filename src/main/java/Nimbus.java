import java.util.Scanner;

public class Nimbus {
    private final Task[] tasks;
    private int numTasks;

    public Nimbus() {
        this.tasks = new Task[100];
        this.numTasks = 0;
    }

    private static void greet() {
        Nimbus.draw_line();
        String logo = """
                 _   _    _____    __  __   ____     _   _    ____ \s
                | \\ | |  |_   _|  |  \\/  |  | __ )  | | | |  / ___|\s
                |  \\| |    | |    | |\\/| |  |  _ \\  | | | |  \\___ \\\s
                | . ` |   _| |_   | |  | |  | |_) | | |_| |   ___) |
                |_| \\_|  |_____|  |_|  |_|  |____/   \\___/   |____/
                """;
        System.out.println(logo);
        System.out.println("""
                Hello! I'm Nimbus
                What can I do for you?
                """);
        Nimbus.draw_line();
    }

    private static void exit() {
        Nimbus.draw_line();
        System.out.println("Bye. Hope to see you again soon!");
        Nimbus.draw_line();
    }

    private static void draw_line() {
        System.out.println("-----------------------------------------------------");
    }

    private static void echo(String s) {
        if (s.equalsIgnoreCase("bye")) {
            Nimbus.exit();
            return;
        }

        Nimbus.draw_line();
        System.out.println(s);
        Nimbus.draw_line();
    }

    private static void help() {
        Nimbus.draw_line();
        String help = """
                Enter a task description to add it to the task list.
                list --list all tasks
                mark [x] --mark task x as done
                unmark [x] --mark task x as not done
                bye --exit""";
        System.out.println(help);
        Nimbus.draw_line();
    }

    public void addTask(String s) {
        Task t = new Task(s);
        this.tasks[numTasks] = t;
        this.numTasks++;
        Nimbus.draw_line();
        System.out.println("Task added: " + s);
        Nimbus.draw_line();
    }

    public void listTasks() {
        Nimbus.draw_line();
        if (this.numTasks == 0) {
            System.out.println("No tasks found.");
        }
        for (int i = 0; i < this.numTasks; i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.print(this.tasks[i].getIsDone() ? "[X] " : "[ ] ");
            System.out.println(this.tasks[i].getDescription());
        }
        Nimbus.draw_line();
    }

    public void markTask(int i) {
        this.tasks[i - 1].setIsDone(true);
        Nimbus.draw_line();
        System.out.println("Task marked as done.");
        Nimbus.draw_line();
    }

    public void unmarkTask(int i) {
        this.tasks[i - 1].setIsDone(false);
        Nimbus.draw_line();
        System.out.println("Task marked as undone.");
        this.listTasks();
    }

    public static void main(String[] args) {
        Nimbus n = new Nimbus();
        Scanner sc = new Scanner(System.in);

        Nimbus.greet();
        while (true) {
            String s = sc.nextLine();
            if (s.isBlank()) {
                System.out.println("Enter -help to see the list of commands");
            } else if (s.equalsIgnoreCase("bye")) {
                Nimbus.exit();
                break;
            } else if (s.equalsIgnoreCase("list")) {
                n.listTasks();
            } else if (s.split(" ")[0].equalsIgnoreCase("mark")) {
                n.markTask(s.split(" ")[1].charAt(0) - '0');
            } else if (s.split(" ")[0].equalsIgnoreCase("unmark")) {
                n.unmarkTask(s.split(" ")[1].charAt(0) - '0');
            } else if (s.equalsIgnoreCase("-help")) {
                Nimbus.help();
            } else {
                n.addTask(s);
            }
        }
    }
}