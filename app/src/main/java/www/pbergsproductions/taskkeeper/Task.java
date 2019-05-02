package www.pbergsproductions.taskkeeper;

import java.util.Date;

public class Task {
    private String name;
    private Date dueDate;
    private int priority;
    private String description;

    public Task() {
        super();
    }

    public Task(String name, Date dueDate, int priority, String description) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
    }

    public Task(String name, Date dueDate, int priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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
