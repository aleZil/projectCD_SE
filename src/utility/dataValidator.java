package utility;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class dataValidator {

	
	public static boolean checkNumber(String num)
	{
		Pattern p = Pattern.compile("\\+[0-9]{12}");
		
		Matcher m=p.matcher(num);
		
		return m.matches();
	}
	
	public static boolean checkString(String st)
	{
		return !st.isEmpty();
	}

	public static boolean emptyTrackList(DefaultListModel<String> cdTrackList)
	{
		return cdTrackList.isEmpty();
	}

	public static boolean checkDate(String date) throws java.text.ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date.trim());
		}
		catch (ParseException e) 
		{
			return false;
		}
		return true;
	}

	public static boolean checkCdPrice(String cdPrice)
	{
		try
		{
			BigDecimal foo = new BigDecimal(cdPrice);

			if(foo.signum()==-1)
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	public static boolean checkInteger(String amount)
	{
		try
		{
			int foo=Integer.parseInt(amount);
			if(foo<0)
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	/**
	 * @param title
	 * @param trackList
	 * @param price
	 * @param amount
	 * @return boolean 
	 */
	public static boolean validValues(String title, DefaultListModel<String> trackList, String price, String amount)
	{
		if(!dataValidator.checkString(title))
		{
			return false;
		}
		if(dataValidator.emptyTrackList(trackList))
		{
			return false;
		}
		if(!dataValidator.checkCdPrice(price))
		{
			return false;
		}
		if(!dataValidator.checkInteger(amount))
		{
			return false;
		}
		return true;
	}
}
