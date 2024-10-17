package service;

import model.Task;
import repository.TaskRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static model.TaskStatus.TODO;

public class TaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;
    private final Set<Task> tasks;

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
        // TODO Auto-generated method stub
    }

    @Override
    public void markTaskAsDone(int id) {
        // TODO Auto-generated method stub
    }

    @Override
    public void markTaskAsToDo(int id) {
        // TODO Auto-generated method stub
    }

    @Override
    public void markTaskAsDoing(int id) {
        // TODO Auto-generated method stub
    }

    private int getNewId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public List<Task> getAllTasks() {
        // TODO Auto-generated method stub
        return null;
    }
}
