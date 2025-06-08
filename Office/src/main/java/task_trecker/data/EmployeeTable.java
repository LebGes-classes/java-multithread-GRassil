package task_trecker.data;

import task_trecker.models.Employee;
import org.apache.poi.ss.usermodel.Row;

public class EmployeeTable extends Table<Employee> {
    public EmployeeTable() {
        super();
        this.sheetName = "Employee";
    }

    @Override
    Employee mkClass(Row row) {
        return new Employee(
                (int) row.getCell(0).getNumericCellValue(),
                row.getCell(1).getStringCellValue()
        );
    }

    @Override
    void mkRow(Row row, Employee employee) {
        row.getCell(0).setCellValue(employee.getId());
        row.getCell(1).setCellValue(employee.getName());
    }
}
