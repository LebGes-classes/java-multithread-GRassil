package models;

import java.util.Random;

/// Модель задачи
public class Task {
    private int id; // айди
    private String name; // название
    private int timeAllocated; // выделенное время
    private int timeSpent; // реально потраченное время
    private boolean isCompleted; // сделана ли?


    /// **************** Конструкторы *******************
    public Task(int id, String name, int timeAllocated) {
        this.id = id;
        this.name = name;
        this.timeAllocated = timeAllocated;

        timeSpent = 0;
    }

    /// *********** Геттеры и сеттеры ********************

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTimeAllocated() {
        return timeAllocated;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    /// Сделана ли задача
    public boolean isCompleted() {
        return isCompleted;
    }

    /// Выполнение задачи
    public int makeTask(int availableTime) {
        Random random = new Random(); // Подключаем рандом, чтобы симулировать выполнение задачи

        int time = random.nextInt(availableTime + 1); // Сколько реально потратил на задачу (от 0 до availableHours)

        timeSpent += time;// Реально потраченное время увеличивается

        isCompleted = random.nextBoolean(); // Случайным образом определяется, сделана ли задача

        return time; // Возвращаем время, которое реально потратили на задачу (либо весь день, либо что осталось)
    }
}
