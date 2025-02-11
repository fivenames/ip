public class NimbusException extends Throwable {
    private final String type;

    public NimbusException(String type) {
        this.type = type;
    }

    public void handleException() {
        switch (type) {
        case "empty command": {
            System.out.println("Empty command, enter a command or enter -help for a list of supported commands");
            break;
        }
        case "invalid command word": {
            System.out.println("Invalid command word, please try again or enter -help for a list of supported commands");
            break;
        }
        case "invalid usage": {
            System.out.println("Invalid usage of this command, please try again or enter -help to learn more about the usage");
            break;
        }
        case "out of range": {
            System.out.println("Index of the Task selected is out of range, please enter a valid index");
            break;
        }
        case "invalid index": {
            System.out.println("Enter a number to specify which task to mark or unmark");
            break;
        }
        case "no deadline specified": {
            System.out.println("No deadline specified, specify a deadline using '/by'" +
                    "for example: deadline CS2113 homework /by Friday 2PM");
            break;
        }
        case "no event specified": {
            System.out.println("No duration specified, specify the start timing using '/from' and specify the end timing using '/to'" +
                    "for example: event CS2113 exam /from 2PM /to 4PM");
            break;
        }
        }
    }
}