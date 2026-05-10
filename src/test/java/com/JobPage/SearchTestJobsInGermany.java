package com.JobPage;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.PoJos.DateFormater;

public class SearchTestJobsInGermany
{
	public void setLocationPreference(ChromeDriver driver)
	{	
		try
		{
			System.out.println(DateFormater.formatDateAndTime()+" >>>> fetching location preference");
			
			Properties jobPageXpaths = new Properties();
			FileInputStream getJobPageXpaths = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\XpathsJobsPage.properties");
			jobPageXpaths.load(getJobPageXpaths);
			
			String locationPreference = jobPageXpaths.getProperty("locationPreference");
			
			SearchTestJobsInGermany.setRolePreference(driver, locationPreference);
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.getCause();
		}		
	}
	
	@Test(priority = 2, dependsOnMethods = {"setLocationPreference"})
	public static void setRolePreference(ChromeDriver driver,String locationPreference)
	{
		try
		{
			System.out.println(DateFormater.formatDateAndTime()+" >>>> fetching role preference");
			
			Properties jobPageXpaths = new Properties();
			FileInputStream getJobPageXpaths = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\XpathsJobsPage.properties");
			jobPageXpaths.load(getJobPageXpaths);
			
			String rolePreference = jobPageXpaths.getProperty("rolePreference");
			
			WebElement roleElement = driver.findElement(By.cssSelector(rolePreference));
			roleElement.sendKeys("Test");
			
			WebElement locationElement = driver.findElement(By.cssSelector(locationPreference));
			locationElement.sendKeys("Germany");		
			
			
			System.out.println(DateFormater.formatDateAndTime()+" >>>> Now check the list of testing jobs in germany");
			
			/*Add Soft Asserts to check the location preference and role preference fetched and entered correctly*/
			SoftAssert softAssert = new SoftAssert();
			Thread.sleep(2000);
			
			/*String locationValueEntered = locationElement.getText();
			String roleValueEntered = roleElement.getText();
			
			softAssert.assertEquals(locationValueEntered,"Germany");
			softAssert.assertEquals(roleValueEntered, "Test");*/
			
			locationElement.sendKeys(Keys.ENTER); //Press the Enter Button now if SoftAsserts are true.
			
			//add Assert All to generate the test reports in TestNG
			softAssert.assertAll();
			
			SearchTestJobsInGermany.filterResultsIn24Hours(driver, jobPageXpaths, getJobPageXpaths);
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.getCause();
		}
	}
	
	public static void filterResultsIn24Hours(ChromeDriver driver, Properties jobPageXpaths, FileInputStream getJobPageXpaths)
	{
		try
		{
			System.out.println(DateFormater.formatDateAndTime()+" >>>> Now showing results within last 24 hours for testing jobs in germany");
			jobPageXpaths.load(getJobPageXpaths);
			
			String searchFilterTimePostedRange = jobPageXpaths.getProperty("searchFilterTimePostedRange");
			
			WebElement dropdownElement = driver.findElement(By.cssSelector(searchFilterTimePostedRange));
			Select dropDownTimeRange = new Select(dropdownElement);
			
			dropDownTimeRange.selectByContainsVisibleText("Past 24 hours");
			
	        WebElement selectedOption = dropDownTimeRange.getFirstSelectedOption();
	        System.out.println("Selected Option: "+selectedOption);

	        String showResultsButton = jobPageXpaths.getProperty("showResultsButton");
	        driver.findElement(By.xpath(showResultsButton)).click();
	        
	        System.out.println(DateFormater.formatDateAndTime()+" >>>> Exiting from Filter Results method...");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}