package service;

import model.Task;
import repository.TaskRepository;
import repository.helper.TaskHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static model.TaskStatus.TODO;

public class TaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;
    private final Set<Task> tasks;
    public TaskHelper taskHelper;

    public TaskServiceImpl(TaskRepository taskRepository, Set<Task> tasks) {
        this.taskRepository = taskRepository;
        this.tasks = tasks;
    }

    @Override
    public void saveTasks() throws IOException {
        taskRepository.saveTasks(tasks);
    }

    @Override
    public int addTask(String Description) {
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task(getNewId(), Description, TODO, now, now);
        tasks.add(task);
        return task.getId();
    }

    @Override
    public void removeTask(int id) {
        tasks.remove(taskHelper.findTaskById(id));
    }

    @Override
    public void markTaskAsDone(int id) {
        taskHelper.markTaskStatus(id, model.TaskStatus.DONE);
    }

    @Override
    public void markTaskAsToDo(int id) {
        taskHelper.markTaskStatus(id, model.TaskStatus.TODO);
    }

    @Override
    public void markTaskAsDoing(int id) {
        taskHelper.markTaskStatus(id, model.TaskStatus.IN_PROGRESS);
    }

    private int getNewId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }
    
    @Override
    public List<Task> getDoneTasks() {
        return taskHelper.getTasksByStatus(model.TaskStatus.DONE);
    }

    @Override
    public List<Task> getToDoTasks() {
        return taskHelper.getTasksByStatus(model.TaskStatus.TODO);
    }

    @Override
    public List<Task> getInProgressTasks() {
        return taskHelper.getTasksByStatus(model.TaskStatus.IN_PROGRESS);
    }
    
    @Override
    public List<Task> getAllTasks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateTask(int id, String description) {
        Task originalTask = taskHelper.findTaskById(id);
        Task updatedTask = new Task(
                                    originalTask.getId(),
                                    description,
                                    originalTask.getStatus(), 
                                    originalTask.getCreatedAt(), 
                                    LocalDateTime.now());

        tasks.remove(originalTask);
        tasks.add(updatedTask);
    }


}
