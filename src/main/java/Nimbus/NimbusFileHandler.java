package Nimbus;

import Nimbus.Task.Task;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class NimbusFileHandler {
    private final String path;
    private final int contentSize;
    private final ArrayList<Task> content;

    public NimbusFileHandler(ArrayList<Task> content, int contentSize) {
        this.contentSize = contentSize;
        this.content = content;
        this.path = "./data/nimbus.txt";
    }

    public void save() throws IOException {
        if (contentSize == 0) {
            System.out.println("Task List is empty");
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