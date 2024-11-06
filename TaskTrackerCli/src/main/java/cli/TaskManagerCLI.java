package cli;

import java.util.NoSuchElementException;
import java.util.Scanner;

import cli.helper.TaskListPrinter;
import service.TaskService;

public class TaskManagerCLI {

    private final Scanner scanner;
    private final TaskService taskService;
    private final TaskListPrinter taskListPrinter;

    public TaskManagerCLI(TaskService taskService) {
        this.scanner = new Scanner(System.in);
        this.taskService = taskService;
        this.taskListPrinter = new TaskListPrinter();
    }

    public void run(){
        System.out.println("Welcome to Task Manager CLI!");
        String command;
        do {
            System.out.print("Enter a command: ");
            command = scanner.next();
            switch (command) {
                case "add" -> addTask();
                case "update" -> updateTask();
                case "delete" -> deleteTask();
                case "mark-todo" -> markTaskAsTodo();
                case "mark-done" -> markTaskAsDone();
                case "mark-in-progress" -> markTaskAsInProgress();
                case "list" -> listTasks();
                case "list-todo" -> listToDoTasks();
                case "list-in-progress" -> listInProgressTasks();
                case "list-done" -> listDoneTasks();
                case "help" -> printHelpMenu();
                case "exit" -> exit();
                default -> System.out.println("Invalid command! Type 'help' to see the list of commands.");
            }
        } while (!command.equals("exit"));
        scanner.close();
    }

    private void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.next();
        taskService.addTask(description);
        System.out.println("Task added successfully!");
    }

    private void updateTask() {
        try{
            int id = scanner.nextInt();
            String description = scanner.next();
            taskService.updateTask(id, description);
            System.out.println("Task updated successfully!");
        }catch (NoSuchElementException e){
            System.out.println("Invalid input! Please enter a valid task id and description.");
        }
    }

    public void deleteTask() {
        try{
            int id = scanner.nextInt();
            taskService.removeTask(id);
            System.out.println("Task deleted successfully!");
        }catch (NoSuchElementException e){
            System.out.println("Invalid input! Please enter a valid task id.");
        }
    }

    private void markTaskAsTodo() {
        try{
            int id = scanner.nextInt();
            taskService.markTaskAsToDo(id);
            System.out.println("Task marked as todo successfully!");
        }catch (NoSuchElementException e){
            System.out.println("Invalid input! Please enter a valid task id.");
        }
    }

    private void markTaskAsDone() {
        try{
            int id = scanner.nextInt();
            taskService.markTaskAsDone(id);
            System.out.println("Task marked as done successfully!");
        }catch (NoSuchElementException e){
            System.out.println("Invalid input! Please enter a valid task id.");
        }
    }

    private void markTaskAsInProgress() {
        try{
            int id = scanner.nextInt();
            taskService.markTaskAsDoing(id);
            System.out.println("Task marked as in progress successfully!");
        }catch (NoSuchElementException e){
            System.out.println("Invalid input! Please enter a valid task id.");
        }
    }

    private void listTasks() {
        taskListPrinter.printTaskList(taskService.getAllTasks());
        scanner.nextLine();
    }
    
    private void listInProgressTasks() {
        taskListPrinter.printTaskList(taskService.getInProgressTasks());
        scanner.nextLine();
    }

    private void listToDoTasks() {
        taskListPrinter.printTaskList(taskService.getToDoTasks());
        scanner.nextLine();
    }
    
    private void listDoneTasks() {
        taskListPrinter.printTaskList(taskService.getDoneTasks());
        scanner.nextLine();
    }

    private void printHelpMenu(){
        String helpMenu = """
                - add [Description] : Add a new task
                - update [Task ID] [Description] : Update a task
                - delete [Task ID] : Delete a task
                - mark-todo [Task ID] : Mark a task as todo
                - mark-done [Task ID] : Mark a task as done
                - mark-in-progress [Task ID] : Mark a task as in progress
                - list : List all tasks
                - list-todo : List all todo tasks
                - list-in-progress : List all in progress tasks
                - list-done : List all done tasks
                - exit : Exit the program
                """;
                System.out.println(helpMenu);
    }

    private void exit(){
        try {
            System.out.println("Saving the tasks...");
            taskService.saveTasks();
            System.out.println("Tasks saved successfully!");
        } catch (Exception e) {
            System.out.println("Failed to save tasks!");
        }
    }
}
