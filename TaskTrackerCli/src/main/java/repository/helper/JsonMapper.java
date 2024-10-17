package repository.helper;

import model.Task;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class JsonMapper {

    public static String taskToJson(Set<Task> tasks) {
        if (tasks.isEmpty()){
            return "";
        }
        return tasks.stream()
                .map(JsonMapper::taskToJson)
                .collect(joining(",", "[", "]"));
    }

    private static String taskToJson(Task task) {
        return "{" +
                "\"id\":" + task.getId() + "," +
                "\"description\":\"" + task.getDescription() + "\"," +
                "\"status\":\"" + task.getStatus() + "\"," +
                "\"createdTime\":\"" + task.getCreatedAt() + "\"," +
                "\"updatedTime\":\"" + task.getUpdatedAt() + "\"" +
                "}";
    }
}
