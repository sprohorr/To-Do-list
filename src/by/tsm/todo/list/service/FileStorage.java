package by.tsm.todo.list.service;

import by.tsm.todo.list.model.ToDoItem;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileStorage{
    private static final String FILENAME = System.getProperty("user.home") + "/tasks.txt";
    public static List<ToDoItem> readDeserializedToDoList() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (List<ToDoItem>) input.readObject();
        } catch (IOException e) {
            System.out.println("Cannot read to file " + e.getClass().getName() + " " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find class " + e.getClass().getName() + " " + e.getMessage());
        }
        return new ArrayList<>();
    }
    public static void writeSerializedToDoList(List<ToDoItem> todolist) {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(todolist);
        } catch (IOException e) {
            System.out.println("Cannot write to file " + e.getClass().getName() + " " + e.getMessage());
        }
    }
}
