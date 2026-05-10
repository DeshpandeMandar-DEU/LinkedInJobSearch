package com.PoJos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormater
{
	public static String formatDateAndTime()
	{
		LocalDateTime localDateAndTime = LocalDateTime.now();
		DateTimeFormatter formatedDateAndTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		
		String formatedDateTime = localDateAndTime.format(formatedDateAndTime);
		
		return formatedDateTime;
	}
}
