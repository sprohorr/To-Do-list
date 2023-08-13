package by.tsm.todo.list.service;

import by.tsm.todo.list.Status;
import by.tsm.todo.list.model.ToDoItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ToDoService {
    public static List<ToDoItem> todolist = new ArrayList<>();
    public static void printMenu() {
        System.out.println("add          1");
        System.out.println("edit         2");
        System.out.println("cancel       3");
        System.out.println("complete     4");
        System.out.println("delete       5");
        System.out.println("short review 6");
        System.out.println("details      7");
        System.out.println("exit         0");
    }
    public static Integer inputNumberMenu(Scanner scanner) {
        Integer number = null;
        while (number == null) {
            try {
                number = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input number:");
                try {
                    number = Integer.valueOf(scanner.nextLine());
                } catch (NumberFormatException e1) {
                    System.out.println("Input number! Please!");
                }
            }
        }
        return number;
    }
    public static Integer inputCaseNumber(Scanner scanner) {
        System.out.println("Enter number to-do");
        Integer caseNumber = null;
        while (caseNumber == null) {
            try {
                caseNumber = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input number:");
                try {
                    caseNumber = Integer.valueOf(scanner.nextLine());
                } catch (NumberFormatException e1) {
                    System.out.println("Input number! Please!");
                }
            }
        }
        return caseNumber;
    }
    public static String inputTask(Scanner scanner) {
        System.out.println("Enter to-do name");
        String task = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\w+\\d+ {25}");
        Matcher matcher = pattern.matcher(task);
        while (matcher.find()) {
            matcher.group();
        }
        return task;
    }
    public static String inputDescription(Scanner scanner) {
        System.out.println("Enter description");
        String description = scanner.nextLine();
        return description;
    }
    public static LocalDateTime printDateCreate() {
        return LocalDateTime.now();
    }
    public static String printDateEnd(Scanner scanner) {
        System.out.println("Enter date end to-do \"yyyy-MM-dd HH:mm\"");
        String date = scanner.findInLine("(\\d{4})\\s(\\d{2})\\s(\\d{2})\\s(\\d{2})[:](\\d{2})");

        return date;
    }


    //perform operation
    public static void performOperationAdd(Scanner scanner) {
        todolist.add(new ToDoItem(inputCaseNumber(scanner), inputTask(scanner), inputDescription(scanner), Status.ACTIVE, printDateCreate(), printDateEnd(scanner)));
        System.out.print(todolist);
        System.out.println();
    }
    public static void performOperationEdit(Scanner scanner) {
        System.out.println("For edit");
        int number = inputCaseNumber(scanner);
        //while () {
            Optional<ToDoItem> todoListWithNumberOpt = todolist.stream()
                    .filter(todoList -> todoList.getCaseNumber() == number)
                    .findFirst();
            if (todoListWithNumberOpt.isPresent()) {
                ToDoItem todoItem = todoListWithNumberOpt.get();
                System.out.println("For edit");
                todoItem.setTask(inputTask(scanner));
                System.out.println("For edit");
                todoItem.setDescription(inputDescription(scanner));
                System.out.println("For edit");
                todoItem.setDateTimeEnd(printDateEnd(scanner));
            } else {
                System.out.println("No such case number exists!");
                //if absent user should input again in while
            }
       // }
        System.out.println(todolist);
    }
    public static void performOperationCancel(Scanner scanner) {
        System.out.println("For cancel");
        int number = inputCaseNumber(scanner);
        Optional<ToDoItem> todoListWithNumberOpt = todolist.stream()
                .filter(todoList -> todoList.getCaseNumber() == number)
                .findFirst();
        ToDoItem todoItem = todoListWithNumberOpt.get();               // while
        todoItem.setStatus(Status.CANCELED);
        System.out.println(todolist);
    }
    public static void performOperationComplete(Scanner scanner) {
        System.out.println("For completed");
        int number = inputCaseNumber(scanner);
        Optional<ToDoItem> todoListWithNumberOpt = todolist.stream()
                .filter(todoList -> todoList.getCaseNumber() == number)
                .findFirst();
        ToDoItem todoItem = todoListWithNumberOpt.get();               // while
        if (todoItem.getStatus() == Status.CANCELED) {
            todoItem.setStatus(Status.CANCELED);
            System.out.println(todolist);
        } else {
            todoItem.setStatus(Status.COMPLETED);
            System.out.println(todolist);
        }
    }
    public static void performOperationDelete(Scanner scanner){
        System.out.println("For delete");
        int number = inputCaseNumber(scanner);
        Optional<ToDoItem> todoListWithNumberOpt = todolist.stream()
                .filter(todoList -> todoList.getCaseNumber() == number)
                .findFirst();
        ToDoItem todoItem = todoListWithNumberOpt.get();
        try {
            boolean isRemoved = todolist.remove(todoItem);
            System.out.println("Delete -> " + isRemoved);
        }catch (Exception e){
            System.out.println("mistake!");
        }

    }
    public static void performOperationShortReview(){
        System.out.println("Short review ");
        todolist.stream()
                .forEach(i -> System.out.println(i.getCaseNumber() + " " + i.getTask()));
    }
    public static void performOperationDetail(){
        System.out.println("Detail ");
        System.out.println(todolist + " ");
    }
}
