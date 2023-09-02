package by.tsm.todo.list.model;

import java.io.Serializable;
import java.time.LocalDateTime;
public class ToDoItem implements Serializable {
    private Integer caseNumber;
    private String task;
    private String description;
    private Status status;
    private LocalDateTime creationDateTime;
    private LocalDateTime editDateTime;
    public ToDoItem(Integer caseNumber, String task, String description,
                    Status status,
                    LocalDateTime creationDateTime,
                    LocalDateTime editDateTime) {
        this.caseNumber = caseNumber;
        this.task = task;
        this.description = description;
        this.status = status;
        this.creationDateTime = creationDateTime;
        this.editDateTime = editDateTime;
    }
    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}
    public void setTask(String task) {this.task = task;}
    public String getTask() {return task;}
    public void setDescription(String description) {this.description = description;}
    public String getDescription() {return description;}
    public Integer getCaseNumber() {return caseNumber;}
    public LocalDateTime getCreationDateTime() {return creationDateTime;}
    public void setEditDateTime(LocalDateTime editDateTime) {this.editDateTime = editDateTime;}

    @Override
    public String toString() {
        return "To-Do{" +
                "Number:" + caseNumber +
                ", {" + task +
                "}, description: " + description +
                ", [" + status +
                "], Creation: " + creationDateTime +
                ", Edit: " + editDateTime +
                "} ";
    }
}
