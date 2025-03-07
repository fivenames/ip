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

    /**
     * Load the storage data if the storage file exists.
     * @param line the current line read from the text file.
     *  */
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
        task = switch (type) {
            case 'T' -> new Todo(arg);
            case 'D' -> {
                arg = arg.substring(0, arg.length() - 1);
                yield new Deadline(arg.replace("(by:", "/by"));
            }
            case 'E' -> {
                arg = arg.substring(0, arg.length() - 1);
                yield new Event(arg.replace("(from:", "/from").replace("to:", "/to"));
            }
            default -> task;
        };
        ;
        task.setIsDone(done);
        return task;
    }

    /**
     * Save the current program memory into the storage file.
     * @param content the task list.
     *  */
    public void save(ArrayList<Task> content) throws IOException {
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

    /**
     * Create the file if it does not exist.
     * @throws IOException if fail to create file.
     *  */
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