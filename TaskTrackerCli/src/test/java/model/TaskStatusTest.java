package model;

import org.junit.jupiter.api.Test;
import static model.TaskStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskStatusTest {
    
    @Test
    void statuses_should_have_a_title_case_status() {
        assertEquals("To Do", TODO.toString());
        assertEquals("In Progress", IN_PROGRESS.toString());
        assertEquals("Done", DONE.toString());
    }   
}
