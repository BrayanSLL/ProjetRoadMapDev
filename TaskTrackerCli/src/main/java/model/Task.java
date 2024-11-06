package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Task implements Comparable<Task> {

    private int id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Task(int id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        checkId(id);
        checkDescription(description);
        checkUpdateTime(createdAt, updatedAt);
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public boolean isUpdated(){
        return updatedAt.isAfter(createdAt);
    }

    @Override
    public int compareTo(Task task) {
        return Integer.compare(this.id, task.id);
    }

    private void checkId(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("Task ID must be positive");
    }

    private void checkDescription(String description) {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or empty");
        if (description.length() > 255)
            throw new IllegalArgumentException("Description is too long");
    }

    private void checkUpdateTime(LocalDateTime createdTime, LocalDateTime updatedTime) {
        if (updatedTime.isBefore(createdTime))
            throw new IllegalArgumentException("Updated time cannot be before Created time");
    }
}
