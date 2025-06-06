package models;

public class Employee {
    private int id; // id работника
    private String name; // Имя работника

    private Task currentTask; // Таска, которая сейчас лежит на этом работнике

    private int completedTasksValue; // Сколько тасков закрыл
    private int timeAtWork; // Сколько времени на работе

    private int timeBusy; // Сколько времени на выполнении задачи

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getTimeAtWork() {
        return timeAtWork;
    }

    public void setTimeAtWork(int timeAtWork) {
        this.timeAtWork = timeAtWork;
    }

    public int getTimeBusy() {
        return timeBusy;
    }

    public void addTimeBusy(int value) {
        timeBusy += value;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public int getCompletedTasksValue() {
        return completedTasksValue;
    }

    public void addCompletedTasksValue(int value) {
        completedTasksValue += value;
    }
}
