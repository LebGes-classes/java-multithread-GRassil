package services;

import data.EmployeeReportTable;
import models.Employee;
import models.EmployeeReport;

import java.util.ArrayList;
import java.util.List;

public class EmployeeReportService {
    private static EmployeeReportTable employeeReportTable = new EmployeeReportTable();

    public static List<EmployeeReport> saveAll(List<Employee> employees, int day) {
        List<EmployeeReport> employeeReports = new ArrayList<>();

        for (Employee employee : employees) {
            employeeReports.add(
                    new EmployeeReport(
                            day,
                            employee
                    )
            );
        }

        employeeReportTable.write(employeeReports);

        return employeeReports;
    }
}
