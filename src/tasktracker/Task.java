package tasktracker;

public class Task {
    private String title;
    private String priority;
    private String status;

    public Task(String title, String priority, String status) {
        this.title = title;
        this.priority = priority;
        this.status = status;
    }

    public String getTitle() { return title; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String toCSV() {
        return title + "," + priority + "," + status;
    }
}