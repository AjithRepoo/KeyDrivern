package Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public  class ExcelReader {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelReader(String excelPath) {
        try {
            FileInputStream fis = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet("Sheet1"); // Change the sheet name as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRowCount(String sheetName) {
        return workbook.getSheet(sheetName).getPhysicalNumberOfRows();
    }

    public String getCellData(String sheetName, String columnName, int rowNum) {
        int colNum = getColNum(sheetName, columnName);
        Row row = sheet.getRow(rowNum);

        if (row == null) {
            return "";  // Return an empty string if the row is null
        }

        Cell cell = row.getCell(colNum);

        if (cell == null) {
            return "";  // Return an empty string if the cell is null
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Check if the column name is "LocatorType"
                if (columnName.equalsIgnoreCase("LocatorType")) {
                    return String.valueOf((int) cell.getNumericCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }


    private int getColNum(String sheetName, String columnName) {
        int colCount = workbook.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
        for (int i = 0; i < colCount; i++) {
            if (workbook.getSheet(sheetName).getRow(0).getCell(i).getStringCellValue().equals(columnName)) {
                return i;
            }
        }
        throw new RuntimeException("Column not found: " + columnName);
    }
}

