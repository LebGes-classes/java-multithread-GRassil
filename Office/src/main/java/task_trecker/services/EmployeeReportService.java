package task_trecker.services;

import task_trecker.data.EmployeeReportTable;
import task_trecker.models.Employee;
import task_trecker.models.EmployeeReport;

import java.util.ArrayList;
import java.util.List;

public class EmployeeReportService {
    private static EmployeeReportTable employeeReportTable = new EmployeeReportTable();
    private static List<EmployeeReport> employeeReports = new ArrayList<>();

    public static void addToSaveList(List<Employee> employees, int day) {
        for (Employee employee : employees) {
            employeeReports.add(
                    new EmployeeReport(
                            day,
                            employee.clone()
                    )
            );
        }

        employeeReportTable.write(employeeReports);

        for (Employee employee: employees) {
            employee.setValues();
        }
    }

    public static void finalSave(){
        System.out.println(employeeReports);
        employeeReportTable.write(employeeReports);
    }
}
