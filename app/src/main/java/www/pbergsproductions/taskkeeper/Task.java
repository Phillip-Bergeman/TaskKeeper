package www.pbergsproductions.taskkeeper;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String dueDate;
    private int priority;
    private String description;

    public Task() {
        super();
    }

    public Task(int id, String name, String dueDate, int priority, String description) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
    }

    public Task(String name, String dueDate, int priority, String description) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
