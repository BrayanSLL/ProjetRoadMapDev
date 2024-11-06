import java.io.IOException;
import java.nio.file.Path;

import cli.TaskManagerCLI;
import repository.TaskRepositoryImpl;
import service.TaskServiceImpl;

public class Main {
public static final String TASKS_FILE = "tasks.json";
    public static void main(String[] args) throws IOException {
        new TaskManagerCLI(new TaskServiceImpl(new TaskRepositoryImpl(Path.of(TASKS_FILE)))).run();
    }
}
