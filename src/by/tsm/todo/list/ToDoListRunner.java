package by.tsm.todo.list;

import java.util.Scanner;
import static by.tsm.todo.list.service.FileStorage.readDeserializedToDoList;
import static by.tsm.todo.list.service.ToDoService.*;
import static by.tsm.todo.list.service.Validator.inputNumberMenu;

public class ToDoListRunner {
    public static void main(String[] args) {
        todolist = readDeserializedToDoList();
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
                case 7 -> performOperationDetail(scanner);
                case 8 -> performOperationFilterByStatus(scanner);
                case 9 -> performOperationFilterByTimeEnd(scanner);
                case 10 -> performOperationFilterByToDoList(scanner);
            }
        } while (number != 0);
    }

}
