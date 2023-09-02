package by.tsm.todo.list.service;

import by.tsm.todo.list.model.Status;
import by.tsm.todo.list.model.ToDoItem;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static by.tsm.todo.list.service.FileStorage.writeSerializedToDoList;
import static by.tsm.todo.list.service.Validator.*;

public class ToDoService {
    public static List<ToDoItem> todolist = new ArrayList<>();
    public static void printMenu() {
        System.out.println("add                            1");
        System.out.println("edit                           2");
        System.out.println("cancel                         3");
        System.out.println("complete                       4");
        System.out.println("delete                         5");
        System.out.println("short review                   6");
        System.out.println("details                        7");
        System.out.println("show by status                 8");
        System.out.println("show tasks by completion time  9");
        System.out.println("enter the task name to search 10");
        System.out.println("exit                           0");
    }

    public static void printTodoList(){
        todolist.stream()
                .forEach(i -> System.out.println(i.getCaseNumber() + " {"
                        + i.getTask() + "} Description: " + i.getDescription()
                        + " [" + i.getStatus() + "] "));
    }
    public static void performOperationAdd(Scanner scanner) {
        todolist.add(new ToDoItem(CaseNumber(), inputTask(scanner), inputDescription(scanner), Status.ACTIVE,
                LocalDateTime.now(),null));
        writeSerializedToDoList(todolist);
        printTodoList();
    }
    public static void performOperationEdit(Scanner scanner) {
        System.out.println("To edit, enter the task number");
        Optional<ToDoItem> todoListWithNumberOpt = findByCaseNumber(scanner);
        if (todoListWithNumberOpt.isPresent()) {
                ToDoItem todoItem = todoListWithNumberOpt.get();
                System.out.println("For edit");
                todoItem.setTask(inputTask(scanner));
                System.out.println("For edit");
                todoItem.setDescription(inputDescription(scanner));
                todoItem.setEditDateTime(LocalDateTime.now());
        } else {
                System.out.println("No such case number exists!");
        }
        writeSerializedToDoList(todolist);
        printTodoList();
    }
    public static void performOperationCancel(Scanner scanner) {
        System.out.println("To cancel the task, enter the number task");
        Optional<ToDoItem> todoListWithNumberOpt = findByCaseNumber(scanner);
        if(todoListWithNumberOpt.isPresent()){
            ToDoItem todoItem = todoListWithNumberOpt.get();
            if (todoItem.getStatus() == Status.ACTIVE) {
                todoItem.setStatus(Status.CANCELED);
                printTodoList();
            } else if(todoItem.getStatus() == Status.COMPLETED){
                printTodoList();
            }
        }else {
            System.out.println("No such case number exists!");
        }
        writeSerializedToDoList(todolist);
    }
    public static void performOperationComplete(Scanner scanner) {
        System.out.println("For a completed task, enter a number task");
        Optional<ToDoItem> todoListWithNumberOpt = findByCaseNumber(scanner);
        if(todoListWithNumberOpt.isPresent()){
            ToDoItem todoItem = todoListWithNumberOpt.get();
            if (todoItem.getStatus() == Status.ACTIVE) {
                todoItem.setStatus(Status.COMPLETED);
                printTodoList();
            } else if(todoItem.getStatus() == Status.CANCELED){
                printTodoList();
            }
        }else {
            System.out.println("No such case number exists!");
        }
        writeSerializedToDoList(todolist);
    }
    public static void performOperationDelete(Scanner scanner){
        System.out.println("To delete, enter task number");
        int number = Integer.parseInt(scanner.nextLine());
        todolist.stream()
                    .filter(todoList -> todoList.getCaseNumber() == number)
                    .findFirst()
                    .ifPresentOrElse(
                            item -> System.out.println("Delete -> " + todolist.remove(item)),
                            () -> System.out.println("Not found " + number)
                    );
        writeSerializedToDoList(todolist);
        printTodoList();
    }
    public static void performOperationShortReview(){
        System.out.println("Short review ");
        todolist.stream()
                .forEach(i -> System.out.println(i.getCaseNumber() + " " + i.getTask() + " [" + i.getStatus() + "]"));
    }
    public static void performOperationDetail(Scanner scanner){
        System.out.println("Enter the task number to view details ");
        int number = Integer.parseInt(scanner.nextLine());
        todolist.stream()
                .filter(todoList -> todoList.getCaseNumber() == number)
                .findFirst()
                .ifPresentOrElse(
                        item -> System.out.println(todolist),
                        () -> System.out.println("Not found " + number)
                );
    }
    public static void performOperationFilterByStatus (Scanner scanner){
        System.out.println("Enter status");
        Status status = findByStatus(scanner);
        List<ToDoItem>filteredByStatusTasks = todolist.stream()
                .filter(task ->task.getStatus().equals(status))
                .collect(Collectors.toList());
        System.out.println(filteredByStatusTasks);
    }
    public static void performOperationFilterByTimeEnd(Scanner scanner){
        System.out.println("Enter end date yyyy-MM-ddTHH:mm");
        LocalDateTime strDate = validationInputTime(scanner);
        List<ToDoItem>filteredByTimeEndTask = todolist.stream()
                .filter(task ->task.getCreationDateTime().isAfter(strDate))
                .toList();
        for(ToDoItem el:filteredByTimeEndTask){
            if(el.getStatus() == Status.COMPLETED){
                System.out.println(el + " ");
            }
        }
    }
    public static void performOperationFilterByToDoList(Scanner scanner){
        System.out.println("enter the task name ");
        String todoName = scanner.nextLine();
        List<ToDoItem> filterByTodoName = todolist.stream()
                .filter(name -> Objects.equals(name.getTask(), todoName))
                .toList();
        System.out.println(filterByTodoName);
    }
}
