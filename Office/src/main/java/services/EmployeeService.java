package services;

import data.EmployeeTable;
import models.Employee;

import java.util.List;

public class EmployeeService {
    private static EmployeeTable employeeTable = new EmployeeTable();

    public static List<Employee> getAll() {
        return employeeTable.read();
    }
}
