package task_trecker;

import task_trecker.models.Employee;
import task_trecker.services.EmployeeService;
import task_trecker.services.EmployeeReportService;
import task_trecker.services.TaskService;

import java.util.ArrayList;
import java.util.List;

public class Office {
    private List<EmployeeThread> employeeThreads = new ArrayList<>();

    private List<Employee> employees;

    public void work(){
        employees = EmployeeService.getAll();
        int day = 1;
        while (!TaskService.isEmpty()) {
            System.out.println("--- День " + day + " ---");

            workDay(); // рабочий день

            // Сохраняем промежуточную статистику
            EmployeeReportService.addToSaveList(employees, day);

            day++;
        }
        EmployeeReportService.finalSave(); //Отправляем инфу в эксель
    }

    public void workDay(){
        // Создаем новые потоки для каждого дня
        employeeThreads.clear();
        for (Employee employee : employees) {
            employeeThreads.add(new EmployeeThread(employee));
        }

        // Запускаем все потоки
        for (EmployeeThread thread : employeeThreads) {
            thread.start();
        }

        // Ожидаем завершения всех потоков, окончание рабочего дня
        for (EmployeeThread thread : employeeThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Поток был прерван: " + e.getMessage());
            }
        }
    }
}
