package GenericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.graphbuilder.math.func.RandFunction;

public class JavaUtility 
{
	public int getRandomNum(int limit)
	{
		Random ran = new Random();
		int randomNum = ran.nextInt(limit);
		return randomNum;
	}

	
	public String generateSystemDate()
	{
		Date DateObj = new Date();
	    SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String todayDate = sim.format(DateObj);
		return todayDate;
	}
	
	
	
	public String generateReqDate(int days)
	{
		Date DateObj = new Date();
	    SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String todayDate = sim.format(DateObj);
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String closedate = sim.format(cal.getTime());
		
		return closedate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
