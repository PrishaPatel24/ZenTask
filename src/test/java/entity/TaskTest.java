package entity;

import org.junit.Test;

public class TaskTest {
    @Test
    public void getTaskDescriptionTest() {
        final Task testTask = new Task("title of task");
        assert testTask.getDescription().equals("title of task");
    }
}
