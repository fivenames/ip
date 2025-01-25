public class Nimbus {
    private static void greet() {
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
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        Nimbus.greet();
        Nimbus.exit();
    }
}
