package Nimbus.Task;

public class Event extends Task {
    private final String from;

    public Event(String description) {
        super(description.split("/from")[0]);
        this.from = description.split("/from", 2)[1];
    }

    @Override
    public void print() {
        System.out.print("[E]");
        super.print();
        System.out.println("(from:" + from.replace("/to", "to:") + ")");
    }
}