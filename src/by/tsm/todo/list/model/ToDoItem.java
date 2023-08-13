package by.tsm.todo.list.model;

import by.tsm.todo.list.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ToDoItem implements Serializable {
    private Integer caseNumber;
    private String task;
    private String description;
    private Status status;
    private LocalDateTime localDateTimeCreate;
    private String DateTimeEnd;

    public ToDoItem(Integer caseNumber, String task, String description,
                    Status status,
                    LocalDateTime localDateTimeCreate,
                    String DateTimeEnd) {
        this.caseNumber = caseNumber;
        this.task = task;
        this.description = description;
        this.status = status;
        this.localDateTimeCreate = localDateTimeCreate;
        this.DateTimeEnd = DateTimeEnd;
    }

    public void setDateTimeEnd(String dateTimeEnd) {DateTimeEnd = dateTimeEnd;}
    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}
    public void setTask(String task) {this.task = task;}
    public String getTask() {return task;}
    public void setDescription(String description) {this.description = description;}
    public Integer getCaseNumber() {return caseNumber;}

    @Override
    public String toString() {
        return "TodoItem{" +
                "caseNumber=" + caseNumber +
                ", task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", DateTimeCreate=" + localDateTimeCreate +
                ", DateTimeEnd=" + DateTimeEnd +
                '}';
    }
}
