package Nimbus.Task;

public class Event extends Task {
    private final String from;

    public Event(String description) {
        super(description.split("/from")[0]);
        this.from = description.split("/from", 2)[1];
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from:" + this.from.replace("/to", "to:") + ')';
    }
}
