package repository.helper;

import model.Task;

import java.util.Set;

public class TaskHelper {

    private final Set<Task> tasks;

    public TaskHelper(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Task findTaskById(int id){
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " not found"));
    }

    public void markTaskStatus(int id, model.TaskStatus status){
        Task originalTask = findTaskById(id);

        Task taskChangedStatus = new Task(
                originalTask.getId(),
                originalTask.getDescription(),
                status,
                originalTask.getCreatedAt(),
                originalTask.getUpdatedAt()
        );
        tasks.remove(originalTask);
        tasks.add(taskChangedStatus);
    }
}
