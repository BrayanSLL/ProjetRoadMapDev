package service;

import model.Task;

import java.io.IOException;
import java.util.List;

public interface TaskService {

    void saveTasks() throws IOException;

    int addTask(String Description);

    void removeTask(int id);

    void markTaskAsDone(int id);

    void markTaskAsToDo(int id);

    void markTaskAsDoing(int id);

    List<Task> getAllTasks();

}
