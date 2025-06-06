package data;

import models.Task;
import org.apache.poi.ss.usermodel.Row;

public class TaskTable extends Table<Task>{

    public TaskTable(){
        super();
        sheetName = "Task";
    }

    @Override
    Task mkClass(Row row) {
        return new Task(
                (int) row.getCell(0).getNumericCellValue(),
                row.getCell(1).getStringCellValue(),
                (int) row.getCell(2).getNumericCellValue()
        );
    }

    @Override
    void mkRow(Row row, Task task) {
        row.getCell(0).setCellValue(task.getId());
        row.getCell(1).setCellValue(task.getName());
        row.getCell(2).setCellValue(task.getTimeAllocated());
        row.getCell(3).setCellValue(task.getTimeSpent());
    }
}
