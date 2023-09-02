package by.tsm.todo.list.service;

import by.tsm.todo.list.model.Status;
import by.tsm.todo.list.model.ToDoItem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;
import static by.tsm.todo.list.service.ToDoService.todolist;
public class Validator {
    public static Integer inputNumberMenu(Scanner scanner) {
        Integer number = null;
        while (number == null) {
            System.out.println("Input number:");
            try {
                number = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number" + e.getMessage());
            }
        }
        return number;
    }
    public static Integer CaseNumber() {                      //check
        return todolist.size()+1;
    }
    public static String inputTask(Scanner scanner) {
        System.out.println("Enter to-do name");
        String task = null;
        try {
            task = scanner.nextLine();
            if(task.length() <= 25){}
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return task;
    }
    public static String inputDescription(Scanner scanner) {
        System.out.println("Enter description");
        String description = scanner.nextLine();
        return description;
    }
    public static Optional<ToDoItem> findByCaseNumber(Scanner scanner){
        Optional<ToDoItem> todoListWithNumberOpt = null;
        do{
            try {
                int number = Integer.parseInt(scanner.nextLine());
                todoListWithNumberOpt = todolist.stream()
                        .filter(todoList -> todoList.getCaseNumber() == number)
                        .findFirst();
            }catch (Exception e){
                System.out.println("Invalid number" + e.getMessage());
            }
        }while (todoListWithNumberOpt == null);
        return todoListWithNumberOpt;
    }
    public static Status findByStatus(Scanner scanner){
        Status status = null;
        do{
            try {
                 status = Status.valueOf((scanner.nextLine().toUpperCase()));
                Status finalStatus = status;
                todolist.stream()
                        .filter(todoList -> todoList.getStatus() == finalStatus)
                        .findFirst();
            }catch (Exception e){
                System.out.println("Invalid status" + e.getMessage() + " Enter status ");
            }
        }while (status == null);
        return status;
    }
    public static LocalDateTime validationInputTime(Scanner scanner){
        LocalDateTime strDate = null;
        try{
        strDate = LocalDateTime.parse(scanner.nextLine());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        dateTimeFormatter.format(strDate);

        }catch (Exception e){
            System.out.println("Invalid input " + e.getMessage());
        }

        return strDate;
    }
}
