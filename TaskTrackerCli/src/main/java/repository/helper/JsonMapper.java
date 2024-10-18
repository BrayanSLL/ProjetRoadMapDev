package repository.helper;

import model.Task;
import model.TaskStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

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

    public static Set<Task> jsonToTasks(String json) {
        if (json == null || json.isEmpty())
            return new HashSet<>();
        return Arrays.stream(json.substring(2, json.length() - 2).split("},\\{"))
                .map(JsonMapper::convertJsonToTask)
                .collect(toSet());
    }

    private static Task convertJsonToTask(String json) {
        String[] keyValuePairs = json.replaceAll("\"", "").split(","); // key:value
        int id = 0;
        String description = null;
        TaskStatus status = null;
        LocalDateTime createdTime = null;
        LocalDateTime updatedTime = null;

        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":");
            String key = entry[0];
            String value = entry[1];
            switch (key) {
                case "id":
                    id = Integer.parseInt(value);
                    break;
                case "description":
                    description = value;
                    break;
                case "status":
                    status = TaskStatus.valueOf(value);
                    break;
                case "createdTime":
                    createdTime = LocalDateTime.parse(value);
                    break;
                case "updatedTime":
                    updatedTime = LocalDateTime.parse(value);
                    break;
            }
        }
        return new Task(id, description, status, createdTime, updatedTime);
    }
}
