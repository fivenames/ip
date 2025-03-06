package Nimbus;

import Nimbus.Task.Deadline;
import Nimbus.Task.Event;
import Nimbus.Task.Task;
import Nimbus.Task.Todo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class NimbusStorage {
    private final String path;;

    public NimbusStorage() {
        this.path = "./data/nimbus.txt";
    }

    // Load from file
    public Nimbus load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        int numTasks = 0;

        File file = initFile();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = loadTask(line);
                tasks.add(task);
                numTasks++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return new Nimbus(tasks, numTasks);
    }

    // Parse the task in one line of the file
    private static Task loadTask(String line) {
        char type = line.charAt(1);
        String arg = line.substring(3);

        boolean done = false;
        if (arg.charAt(1) == 'X') {
            done = true;
            arg = arg.replace("[X]", "");
        } else {
            arg = arg.replace("[ ] ", "");
        }
        Task task = new Task(arg);
        switch (type) {
            case 'T':
                task = new Todo(arg);
                break;
            case 'D':
                arg = arg.substring(0, arg.length() - 1);
                task = new Deadline(arg.replace("(by:", "/by"));
                break;
            case 'E':
                arg = arg.substring(0, arg.length() - 1);
                task = new Event(arg.replace("(from:", "/from").replace("to:", "/to"));
                break;
        };
        task.setIsDone(done);
        return task;
    }

    // Save into file
    public void save(int size, ArrayList<Task> content) throws IOException {
        File file = initFile();

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : content) {
                writer.write(task.toString() + '\n');
            }
            System.out.println("Task list has been saved to " + this.path);
        } catch (IOException e) {
            System.err.println("Failed to save file!");
        }
    }

    // Create file object if not exist
    private File initFile() throws IOException {
        File file = new File(this.path);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                System.err.println("Failed to create file!");
            }
        }
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.err.println("Failed to create file!");
            }
        }

        return file;
    }
}