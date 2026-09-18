package tasktracker;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void toggleStatus(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (task.getStatus().equals("Pending")) {
                task.setStatus("Completed");
            } else {
                task.setStatus("Pending");
            }
        }
    }

    public int getCompletedCount() {
        int count = 0;
        for (Task t : tasks) {
            if (t.getStatus().equals("Completed")) count++;
        }
        return count;
    }

    public List<Task> getTasks() { return tasks; }
}