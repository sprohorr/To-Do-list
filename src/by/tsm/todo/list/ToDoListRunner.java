package by.tsm.todo.list;

import by.tsm.todo.list.model.ToDoItem;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import static by.tsm.todo.list.service.ToDoService.*;

public class ToDoListRunner {
    private static final String FILENAME = "C:\\Users\\User\\IdeaProjects\\todo-list\\src\\textFile.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            printMenu();
            number = inputNumberMenu(scanner);
            switch (number) {
                case 1 -> performOperationAdd(scanner);
                case 2 -> performOperationEdit(scanner);
                case 3 -> performOperationCancel(scanner);
                case 4 -> performOperationComplete(scanner);
                case 5 -> performOperationDelete(scanner);
                case 6 -> performOperationShortReview();
                case 7 -> performOperationDetail();
            }
        } while (number != 0);
        todolist = (List<ToDoItem>) readDeserializedToDoList();
        System.out.println(todolist);
        writeSerializedToDoList(todolist);
        System.out.println(todolist);
    }
    private static ToDoItem readDeserializedToDoList() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (ToDoItem) input.readObject();
        } catch (IOException e) {
            System.out.println("Cannot read to file " + e.getClass().getName() + " " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find class " + e.getClass().getName() + " " + e.getMessage());
        }
        return null;
    }
    private static void writeSerializedToDoList(List<ToDoItem> todolist) {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(todolist);
        } catch (IOException e) {
            System.out.println("Cannot write to file " + e.getClass().getName() + " " + e.getMessage());
        }
    }
}
