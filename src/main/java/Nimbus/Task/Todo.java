package Nimbus.Task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public void print() {
        System.out.print("[T]");
        super.print();
        System.out.println();
    }
}