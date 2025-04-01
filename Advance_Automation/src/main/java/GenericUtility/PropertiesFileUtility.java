package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility 
{
	
	public String readingDataFromPropFile(String key) throws IOException
	{
		
	
	FileInputStream fis = new FileInputStream("C:\\E20\\Advance_Automation\\src\\test\\resources\\common_data.properties");
	
	Properties prop = new Properties();
	prop.load(fis);
	
	 
	String data = prop.getProperty(key);
	return data;
	 
	 
	
	}

}
