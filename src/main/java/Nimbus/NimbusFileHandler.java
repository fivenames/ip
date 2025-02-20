package Nimbus;

import Nimbus.Task.Task;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class NimbusFileHandler {
    private final String path;
    private final ArrayList<Task> content;

    public NimbusFileHandler(ArrayList<Task> content) {
        this.content = content;
        this.path = "./data/nimbus.txt";
    }

    public void save(int size) throws IOException {
        if (size == 0) {
            System.out.println("Task List is empty");
            return;
        }

        File file = new File(this.path);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                System.err.println("Failed to create file!");
                return;
            }
        }
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.err.println("Failed to create file!");
                return;
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : this.content) {
                writer.write(task.toString() + '\n');
            }
            System.out.println("Task list has been saved to " + this.path);
        } catch (IOException e) {
            System.err.println("Failed to save file!");
        }
    }
}