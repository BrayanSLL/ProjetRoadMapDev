package model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskTest {
    
    private final LocalDateTime dateTime;

    public TaskTest() {
        this.dateTime = LocalDateTime.of(2002, 2, 2, 2, 2);
    }

    @Test
    public void task_should_not_be_created_if_id_is_zero() {
        assertThrows(
                IllegalArgumentException.class, 
                () -> new Task(0, "practice java",TaskStatus.TODO, dateTime, dateTime)
            );
    }

    @Test
    public void task_should_not_be_created_if_id_is_negative() {
        assertThrows(
                IllegalArgumentException.class, 
                () -> new Task(-1, "practice java",TaskStatus.TODO, dateTime, dateTime)
            );
    }

    @Test
    public void task_should_not_be_created_if_title_is_null() {
        assertThrows(
                IllegalArgumentException.class, 
                () -> new Task(1, null,TaskStatus.TODO, dateTime, dateTime)
            );
    }

    @Test
    public void task_should_not_be_created_if_title_is_empty() {
        assertThrows(
                IllegalArgumentException.class, 
                () -> new Task(1, "",TaskStatus.TODO, dateTime, dateTime)
            );
    }

    @Test
    public void task_should_not_be_created_if_description_whitespaces() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Task(1, "         ", TaskStatus.TODO, dateTime, dateTime)
        );
    }

    @Test
    void task_should_not_be_created_if_description_length_is_more_than_255() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Task(1, "a".repeat(256), TaskStatus.TODO, dateTime, dateTime)
        );
    }
}
