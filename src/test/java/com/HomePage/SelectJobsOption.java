package com.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;

import com.JobPage.SearchTestJobsInGermany;
import com.PoJos.DateFormater;

public class SelectJobsOption 
{
	public static void selectJobsOptionFromFeeds(ChromeDriver chromeDriver) throws IOException
	{
		try
		{
			System.out.println(DateFormater.formatDateAndTime()+" >>>> Inside selectJobsOptionFromFeeds Method");
			Thread.sleep(1000);
			
			Properties xPathsFeedPage = new Properties();
			FileInputStream readFeedPageXpaths = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\XpathsFeedPage.properties");
			xPathsFeedPage.load(readFeedPageXpaths);
			
			String secondAcceptCookiesButton = xPathsFeedPage.getProperty("secondAcceptCookiesButton");
			chromeDriver.findElement(By.cssSelector(secondAcceptCookiesButton)).click();
			
			String jobsButton = xPathsFeedPage.getProperty("jobsButtonCSS");
			chromeDriver.findElement(By.cssSelector(jobsButton)).click();
			
			System.out.println(DateFormater.formatDateAndTime()+" >>>> Clicked on Jobs Button");
			
			Thread.sleep(1000);
			SearchTestJobsInGermany stjig = new SearchTestJobsInGermany();
			stjig.setLocationPreference(chromeDriver);
		}
		catch (StaleElementReferenceException e)
		{		
			SelectJobsOption.selectJobsOptionFromFeeds(chromeDriver);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
