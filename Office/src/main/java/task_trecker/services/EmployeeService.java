package task_trecker.services;

import task_trecker.data.EmployeeTable;
import task_trecker.models.Employee;

import java.util.List;

public class EmployeeService {
    private static EmployeeTable employeeTable = new EmployeeTable();

    public static List<Employee> getAll() {
        return employeeTable.read();
    }
}
