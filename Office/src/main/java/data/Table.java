package data;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Table<T> {
    String filePath;
    String sheetName;

    Table (){
        this.filePath = Path.getXLSXPATH();
    }

    public List<T> read() {
        List<T> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;// скипаем заголовка
                list.add(mkClass(row));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла" + e);
        }
        return list;
    }

    public void write(List<? extends T> list) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rownum = 1;
            for (T t : list) {
                Row newRow = sheet.createRow(rownum++);
                ensureCellsExist(newRow); // Создаем 10 ячеек
                mkRow(newRow, t);
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в Excel", e);
        }
    }

    void ensureCellsExist(Row row) {
        for (int i = 0; i < 10; i++) {
            if (row.getCell(i) == null) {
                row.createCell(i);
            }
        }
    }

    abstract T mkClass(Row row);

    abstract void mkRow(Row row, T t);
}