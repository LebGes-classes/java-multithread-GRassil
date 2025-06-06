package data;

import models.Employee;
import models.EmployeeReport;
import org.apache.poi.ss.usermodel.Row;

public class EmployeeReportTable extends Table<EmployeeReport>{

    public EmployeeReportTable(){
        super();
        sheetName = "EmployeeReport";
    }

    @Override
    EmployeeReport mkClass(Row row) {
        return null;
    }

    @Override
    void mkRow(Row row, EmployeeReport employeeReport) {
        Employee employee = employeeReport.getEmployee();

        row.getCell(0).setCellValue(row.getRowNum()-1); // id
        row.getCell(1).setCellValue(employeeReport.getDay()); // день

        // инфа про работника
        row.getCell(2).setCellValue(employee.getId()); // его id
        row.getCell(3).setCellValue(employee.getTimeAtWork()); // время на работе
        row.getCell(4).setCellValue(employee.getTimeBusy()); // занятое время
        row.getCell(5).setCellValue(employee.getTimeAtWork() - employee.getTimeBusy()); // простой, безделье
        row.getCell(6).setCellValue(employee.getCompletedTasksValue()); // сколько тасков закрыл

        row.getCell(7).setCellValue(employeeReport.getEfficiency()); // эффективность
    }
}
