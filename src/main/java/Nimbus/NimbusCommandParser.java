package Nimbus;

/**
 * Handles the input from the user.
 */
public class NimbusCommandParser {
    public static String[] validCommands = {"bye", "list", "mark", "unmark", "todo", "deadline", "event", "-help", "delete", "save", "find"};
    private String commandWord;
    private String arguments;

    /**
     * Parser the string from the command line for program logic.
     * @param input user input string
     * @param range number of tasks in the task list
     * @throws NimbusException based on the type of invalid usage.
     *  */
    public NimbusCommandParser(String input, int range) throws NimbusException {
        this.commandWord = null;
        this.arguments = null;
        String[] commands = input.trim().split(" ", 2);
        if (commands.length == 0) {
            throw new NimbusException("empty command");
        }

        this.commandWord = commands[0].toLowerCase();
        if (!this.isValidCommandWord()) {
            throw new NimbusException("invalid command word");
        }

        if (commands.length == 2) {
            this.arguments = commands[1];
        }

        checkValidUsage(commands, range);
    }

    /**
     * Validate each supported command words to prevent invalid arguments from inputting into the program.
     * @param commands: commands of user.
     * @param range: valid range of task index.
     * @throws NimbusException based on what is the type of the invalid usage.
     *  */
    private void checkValidUsage(String[] commands, int range) throws NimbusException {
        switch (this.commandWord) {
        case "bye":
        case "-help":
        case "save":
        case "list": {
            if (commands.length != 1) {
                throw new NimbusException("invalid usage");
            }
            break;
        }
        case "delete":
        case "mark":
        case "unmark":
            if (commands.length != 2) {
                throw new NimbusException("invalid usage");
            }
            try {
                int index = Integer.parseInt(this.arguments);
                if (index < 0 || index > range) {
                    throw new NimbusException("out of range");
                }
            } catch (NumberFormatException e) {
                throw new NimbusException("invalid index");
            }
            break;
        case "find":
        case "todo":
            if (commands.length != 2) {
                throw new NimbusException("invalid usage");
            }
            break;
        case "deadline":
            if (commands.length != 2) {
                throw new NimbusException("invalid usage");
            }
            if (!this.arguments.contains("/by")) {
                throw new NimbusException("no deadline specified");
            }
            break;
        case "event":
            if (commands.length != 2) {
                throw new NimbusException("invalid usage");
            }
            if (!this.arguments.contains("/from") || !this.arguments.contains("/to")) {
                throw new NimbusException("no event specified");
            }
            break;
        }
    }


    /**
     * Validate if the command words inputted is supported by the program.
     * @return true if command word is supported, false otherwise.
     *  */
    private boolean isValidCommandWord() {
        boolean valid = false;
        for (String validCommand : validCommands) {
            if (validCommand.equals(this.commandWord)) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public String getCommandWord() {
        return this.commandWord;
    }

    public String getArguments() {
        return this.arguments;
    }
}
