package task_trecker.services;

import task_trecker.data.TaskTable;
import task_trecker.models.Task;

import java.util.List;

public class TaskService {
    private static TaskTable taskTable = new TaskTable();
    private static List<Task> tasks = taskTable.read();

    /// Возвращаем задачу
    public synchronized static Task getTask(){
        if (tasks.isEmpty()) return null;
        return tasks.removeFirst();
    }

    ///  Пуста ли очередь задач
    public static boolean isEmpty(){
        return tasks.isEmpty();
    }
}
