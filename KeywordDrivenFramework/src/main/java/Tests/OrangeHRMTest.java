package Tests;

import com.base.TestBase;

import KeywordDriven.KeywordExecutor;
import Utils.ExcelReader;



public class OrangeHRMTest extends TestBase {

    public static void main(String[] args) {
        // Specify the path to your Excel sheet
        String excelPath = "C:\\Users\\1000066986\\Downloads\\book1.xlsx";
        ExcelReader excelReader = new ExcelReader(excelPath);
        int rowCount = excelReader.getRowCount("Sheet1");
         
        for (int i = 1; i <= rowCount; i++) {
            String testStep = excelReader.getCellData("Sheet1", "TestStep", i);
            String locator = excelReader.getCellData("Sheet1", "Locator", i);
            String locatorType = excelReader.getCellData("Sheet1", "LocatorType", i);
            String action = excelReader.getCellData("Sheet1", "Action", i);
            String value = excelReader.getCellData("Sheet1", "Value", i);

            KeywordExecutor.executeKeywords(testStep, locator, locatorType, action, value);
        }
    }
}
