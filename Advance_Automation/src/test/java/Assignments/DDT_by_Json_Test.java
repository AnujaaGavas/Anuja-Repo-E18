package Assignments;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DDT_by_Json_Test {

	public static void main(String[] args) throws IOException, ParseException 
	{

		
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("C:\\E20\\Advance_Automation\\src\\test\\resources\\student_info.json");
		Object javaObj = parser.parse(file);
		
		JSONObject obj = (JSONObject)javaObj;
		
		Object name = obj.get("name");
		String nameString = obj.get("name").toString();
		
		Object id = obj.get("id");
		String idString = obj.get("id").toString();
		
		Object Branch = obj.get("Branch");
		String BranchString = obj.get("Branch").toString();
		
		Object age = obj.get("Age");
		String ageString = obj.get("Age").toString();
		
		Object isStudent = obj.get("isStudent");
		String isStudentString = obj.get("isStudent").toString();
		
		Object backlogs = obj.get("backlogs");
		//String backlogsString = obj.get("backlogs").toString(); It will throw error as Cannot invoke
		
		
		
		System.out.println(name);
		System.out.println(nameString);
		System.out.println(id);
		System.out.println(idString);
		System.out.println(Branch);
		System.out.println(BranchString);
		System.out.println(age);
		System.out.println(ageString);
		System.out.println(isStudent);
		System.out.println(isStudentString);
		System.out.println(backlogs);
		//System.out.println(backlogsString);
		
		
	
	}

}
