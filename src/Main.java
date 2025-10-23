import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TimeTracker tracker = new TimeTracker();

        if (args.length == 0) {
            System.out.println("Please enter a command.  Type 'help' for a list of commands.");
            return;
        }

        String command = args[0];

        switch (command) {
            case "create":
                if (args.length > 1) {
                    tracker.createTask(args[1]);
                } else {
                    System.out.println("Please provide a task name.");
                }
                break;
            case "start":
                if (args.length > 1) {
                    tracker.startTask(args[1]);
                } else {
                    System.out.println("Please provide a task name.");
                }
                break;
            case "stop":
                if (args.length > 1) {
                    tracker.stopTask(args[1]);
                } else {
                    System.out.println("Please provide a task name.");
                }
                break;
            case "list":
                tracker.listTasks();
                break;
            case "report":
                if (args.length > 1) {
                    tracker.reportTask(args[1]);
                } else {
                    System.out.println("Please provide a task name for the report.");
                }
                break;
            case "allreport":
                tracker.allReport();
                break;
            case "delete":
                if (args.length > 1) {
                    tracker.deleteTask(args[1]);
                } else {
                    System.out.println("Please provide a task name to delete.");
                }
                break;
            case "help":
                System.out.println("Available commands:");
                System.out.println("  create <taskName> - Creates a new task.");
                System.out.println("  start <taskName> - Starts the timer for a task.");
                System.out.println("  stop <taskName> - Stops the timer for a task.");
                System.out.println("  list - Lists all tasks.");
                System.out.println("  report <taskName> - Generates a report for a task.");
                System.out.println("  allreport - Generates a report for all tasks.");
                System.out.println("  delete <taskName> - Deletes a task.");
                System.out.println("  help - Displays this help message.");
                break;
            default:
                System.out.println("Invalid command. Type 'help' for a list of commands.");
        }

        scanner.close();
    }
}