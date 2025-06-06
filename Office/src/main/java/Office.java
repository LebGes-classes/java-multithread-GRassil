import models.Employee;
import models.EmployeeReport;
import services.EmployeeService;
import services.EmployeeReportService;
import services.TaskService;

import java.util.ArrayList;
import java.util.List;

public class Office {
    List<EmployeeThread> employeeThreads = new ArrayList<>();

    List<Employee> employees = EmployeeService.getAll();

    public void work(){
        int day = 1;
        while (!TaskService.isEmpty()) {
            System.out.println("--- День " + day + " ---");

            workDay(); // рабочий день

            // Сохраняем промежуточную статистику
            List<EmployeeReport> employeeReports = EmployeeReportService.saveAll(employees, day);

            System.out.println(employeeReports);

            day++;
        }
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

        // Ожидаем завершения всех потоков
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
