import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;

class TimeTracker {
    private static final String DATA_FILE = "data/time_entries.dat";

    public TimeTracker() {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
    }
    public void createTask(String taskName) {
        List<Task> tasks = loadTasks();
        for(Task task:tasks)
        {
            if(task.getName().equals(taskName)){
                System.out.println("Task already exists.");
                return;
            }
        }
        tasks.add(new Task(taskName));
        saveTasks(tasks);
        System.out.println("Task '" + taskName + "' created.");
    }

    public void startTask(String taskName) {
        List<Task> tasks = loadTasks();
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                foundTask = task;
                break;
            }
        }

        if (foundTask == null) {
            System.out.println("Task '" + taskName + "' does not exist. Create it? (yes/no)");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                createTask(taskName);
                startTask(taskName);
            } else {
                System.out.println("Task not started.");
            }
            return;
        }

        if(foundTask.isRunning()){
            System.out.println("Task '" + taskName + "' is already running.");
            return;
        }

        foundTask.start();
        saveTasks(tasks);
        System.out.println("Task '" + taskName + "' started.");
    }

    public void stopTask(String taskName) {
        List<Task> tasks = loadTasks();
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                foundTask = task;
                break;
            }
        }

        if (foundTask == null) {
            System.out.println("Task '" + taskName + "' does not exist.");
            return;
        }
        if(!foundTask.isRunning()){
            System.out.println("Task '" + taskName + "' is not running.");
            return;
        }

        foundTask.stop();
        saveTasks(tasks);
        System.out.println("Task '" + taskName + "' stopped.");
    }

    public void listTasks() {
        List<Task> tasks = loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.println("- " + task.getName() + (task.isRunning() ? " (Running)" : ""));
        }
    }

    public void reportTask(String taskName) {
        List<Task> tasks = loadTasks();
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                foundTask = task;
                break;
            }
        }

        if (foundTask == null) {
            System.out.println("Task '" + taskName + "' does not exist.");
            return;
        }
        Duration totalTime = foundTask.getTotalTime();
        long hours = totalTime.toHours();
        long minutes = totalTime.toMinutesPart();
        long seconds = totalTime.toSecondsPart();
        System.out.println("Report for task '" + taskName + "': " + hours + "h " + minutes + "m " + seconds + "s");
    }

    public void allReport() {
        List<Task> tasks = loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.println("All Tasks Report:");
        for (Task task : tasks) {
            Duration totalTime = task.getTotalTime();
            long hours = totalTime.toHours();
            long minutes = totalTime.toMinutesPart();
            long seconds = totalTime.toSecondsPart();
            System.out.println("- " + task.getName() + ": " + hours + "h " + minutes + "m " + seconds + "s");
        }
    }

    public void deleteTask(String taskName) {
        List<Task> tasks = loadTasks();
        boolean removed = tasks.removeIf(task -> task.getName().equals(taskName));
        if (removed) {
            saveTasks(tasks);
            System.out.println("Task '" + taskName + "' deleted.");
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }
    }

    private List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            tasks = (List<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore, file will be created on first save
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}