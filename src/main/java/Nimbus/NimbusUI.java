package Nimbus;

public class NimbusUI {
    static void greet() {
        String logo = """
                 _   _    _____    __  __   ____     _   _    ____ \s
                | \\ | |  |_   _|  |  \\/  |  | __ )  | | | |  / ___|\s
                |  \\| |    | |    | |\\/| |  |  _ \\  | | | |  \\___ \\\s
                | . ` |   _| |_   | |  | |  | |_) | | |_| |   ___) |
                |_| \\_|  |_____|  |_|  |_|  |____/   \\___/   |____/
                """;
        NimbusUI.drawLine();
        System.out.println(logo);
        System.out.println("""
                Hello! I'm Nimbus
                What can I do for you?
                """);
        NimbusUI.drawLine();
    }

    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void drawLine() {
        System.out.println("-----------------------------------------------------");
    }

    static void help() {
        String help = """
                todo [task] -- add a task without a deadline
                deadline [task] /by [time] --add a task with a deadline
                event [task] /from [time] /to [time] --add a task with specific start and end time
                list --list all tasks
                mark [x] --mark task x as done
                unmark [x] --mark task x as not done
                delete [x] --delete task x
                save --save tasks to txt file
                bye --exit""";
        System.out.println(help);
    }
}