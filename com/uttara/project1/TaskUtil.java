package com.uttara.project1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskUtil {
	public static boolean validateName(String str)
	{
		if(str==null)
			return false;
		if(str.trim().equals(""))
			return false;
		if(!Character.isLetter(str.charAt(0)))
			return false;

		if(str.split(" ").length>1)    
			return false;

		for(int i = 1;i<str.length();i++)
		{
			char c = str.charAt(i);
			if(!(Character.isDigit(c) || Character.isLetter(c)))
				return false;
		}
		return true;
	}

	public static boolean validateTaskName(String str)
	{
		if(str==null)
			return false;
		if(str.trim().equals(""))
			return false;
		return true;

	}

	public static boolean validateDescName(String str)
	{
		if(str==null)
			return false;
		if(str.trim().equals(""))
			return false;
		return true;

	}

	public static boolean validateTags(String str)
	{
		if(str==null)
			return false;
		if(str.trim().equals(""))
			return false;
		return true;

	}

	public static boolean validateDate(String strDate) 
	{

		if (strDate.trim().equals(""))
		{
			return false;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try
			{
				sdf.setLenient(false);
				Date date = sdf.parse(strDate);  
				return new Date().before(date);
			}
			catch(ParseException r)
			{
				return false;
			}
		}


	}
}