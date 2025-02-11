package Nimbus.Task;

public class Deadline extends Task{
    private final String by;

    public Deadline(String description){
        super(description.split("/by")[0]);
        this.by = description.split("/by", 2)[1];
    }

    @Override
    public void print() {
        System.out.print("[D]");
        super.print();
        System.out.println("(by:" + by + ")");
    }
}