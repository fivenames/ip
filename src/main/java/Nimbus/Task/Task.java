package Nimbus.Task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void print(){
        System.out.print(this.getIsDone() ? "[X] " : "[ ] ");
        System.out.print(this.getDescription());
    }
}