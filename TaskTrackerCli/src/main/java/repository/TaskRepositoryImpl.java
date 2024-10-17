package repository;

import model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import repository.helper.JsonMapper;


public class TaskRepositoryImpl implements TaskRepository {

    private final Path path;

    public TaskRepositoryImpl(Path path) {
        this.path = path;
    }

    @Override
    public void saveTasks(Set<Task> tasks) throws IOException {

        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.writeString(path, JsonMapper.taskToJson(tasks));
    }

    @Override
    public Set<Task> getAllTasks() {
        // TODO Auto-generated method stub
        return null;
    }
}
