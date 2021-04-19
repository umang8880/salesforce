package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelReader {
	
	@DataProvider
	public static Object[][] getdata(Method m) throws EncryptedDocumentException, IOException{	
		
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");		
		FileInputStream fis = new FileInputStream(f);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet0 = workbook.getSheet(m.getName());
		Row row = sheet0.getRow(0); 
		
		int totalRows = sheet0.getPhysicalNumberOfRows()-1;
		int totalCols = row.getPhysicalNumberOfCells();
		 		
		Object[][] data = new Object[totalRows][1]; //[totalRows][totalCols] Total# rows and cols
		Hashtable<String,String> table = null;
		for (int rownum=1; rownum<=totalRows; rownum++) {
			table = new Hashtable<String,String>();
			row = sheet0.getRow(rownum);
			for(int colnum=0; colnum<totalCols; colnum++) {
				table.put(sheet0.getRow(0).getCell(colnum).getStringCellValue(), row.getCell(colnum).getStringCellValue());
				data[rownum-1][0] = table;
			}
		}
		fis.close();
		return data;
	}
}
