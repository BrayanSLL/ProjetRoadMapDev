package cli;

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
