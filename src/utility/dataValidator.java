package utility;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class dataValidator {

	
	
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
		catch (ParseException pe) 
		{
			return false;
		}
		return true;
	}

	public static boolean checkCdPrice(String cdPrice)
	{
		try
		{
			BigDecimal foo=new BigDecimal(cdPrice);
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


	public static boolean validValues(String title,DefaultListModel<String> trackList,String price,String amount)
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
