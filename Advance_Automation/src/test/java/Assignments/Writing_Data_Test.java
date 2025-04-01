package Assignments;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Writing_Data_Test {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{

		FileInputStream fis = new FileInputStream("C:\\E20\\Advance_Automation\\src\\test\\resources\\Compaign_Targetsize.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		wb.createSheet("Writing Date").createRow(0).createCell(2).setCellValue("Selenium");
		
		wb.getSheet("Sheet1").createRow(2).createCell(3).setCellValue("Selenium");
		
		FileOutputStream fos = new FileOutputStream("C:\\E20\\Advance_Automation\\src\\test\\resources\\Compaign_Targetsize.xlsx");

		wb.write(fos);
		wb.close();
		System.out.println("Successful");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
