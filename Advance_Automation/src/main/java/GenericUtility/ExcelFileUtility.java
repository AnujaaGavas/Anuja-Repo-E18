package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility 
{
	
	public String readFromExcelFileUtility(String sheet,int row,int cell) throws EncryptedDocumentException, IOException
	{
		
		FileInputStream fis1 = new FileInputStream("C:\\E20\\Advance_Automation\\src\\test\\resources\\Create_Contact.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		
		String data = book.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		return data;
		
		
		
	}

}
