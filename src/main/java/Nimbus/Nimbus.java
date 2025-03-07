package Nimbus;

import Nimbus.Task.Task;
import Nimbus.Task.TaskList;

import java.util.ArrayList;

import java.io.IOException;
import java.util.Scanner;

public class Nimbus {
    private final TaskList taskList;

    public Nimbus(ArrayList<Task> tasks, int numTasks) {
        this.taskList = new TaskList(tasks, numTasks);
    }

    public static void main(String[] args) {
        NimbusStorage fileHandler = new NimbusStorage();
        Nimbus chatBot = new Nimbus(new ArrayList<>(), 0);
        try {
            chatBot = fileHandler.load();
        } catch (IOException e) {s
            System.err.println("Error loading file");
        }
        Scanner scanner = new Scanner(System.in);

        NimbusUI.greet();
        programExecution:
        while (true) {
            String input = scanner.nextLine();
            try {
                NimbusCommandParser parser = new NimbusCommandParser(input, chatBot.taskList.getNumTasks());
                switch (parser.getCommandWord()) {
                case "bye":
                    NimbusUI.exit();
                    break programExecution;
                case "list":
                    chatBot.taskList.listTasks();
                    break;
                case "mark":
                    chatBot.taskList.markTask(Integer.parseInt(parser.getArguments()));
                    break;
                case "unmark":
                    chatBot.taskList.unmarkTask(Integer.parseInt(parser.getArguments()));
                    break;
                case "delete":
                    chatBot.taskList.deleteTask(Integer.parseInt(parser.getArguments()));
                    break;
                case "save":
                    fileHandler.save(chatBot.taskList.getNumTasks(), chatBot.taskList.getTasks());
                    break;
                case "-help":
                    NimbusUI.help();
                    break;
                default:
                    chatBot.taskList.addTask(parser.getCommandWord(), parser.getArguments());
                    break;
                }
            } catch (NimbusException e) {
                e.handleException();
            } catch (IOException e) {
                System.err.println("Failed to output to file!");
            }
            NimbusUI.drawLine();
        }
        NimbusUI.drawLine();
    }
}