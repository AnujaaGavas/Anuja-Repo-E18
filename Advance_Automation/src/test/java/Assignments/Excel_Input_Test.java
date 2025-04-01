package Assignments;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Input_Test {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		
		FileInputStream fis = new FileInputStream("C:\\E20\\Advance_Automation\\src\\test\\resources\\Compaign_Targetsize.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet s = book.getSheet("Sheet1");
		
		String Comp_Name = s.getRow(1).getCell(2).getStringCellValue();
		double Target_Size = s.getRow(1).getCell(3).getNumericCellValue();
		
		System.out.println(Comp_Name);
		System.out.println(Target_Size);
		
		
		
		
		

	}

}
