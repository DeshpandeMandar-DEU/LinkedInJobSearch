package com.HomePage;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.DriverSetup.ChromeDriverSetup;
import com.PoJos.DateFormater;

public class OpenHomePageAndLogin
{
	public static void main(String[] args)
	{
		try
		{
			OpenHomePageAndLogin ohpal = new OpenHomePageAndLogin();
			ohpal.openHomePage();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Test(priority = 1)
	public void openHomePage ()
	{
		try
		{
			//Now try to open the HomePage
			
			Properties URLProps = new Properties();
			FileInputStream readURL = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\URLs.properties");
			URLProps.load(readURL);			
			
			String homePageURL = URLProps.getProperty("LinkedInHomePage");
			
			ChromeDriver chromeDriver = ChromeDriverSetup.setUpDriver();
			
			System.out.println(DateFormater.formatDateAndTime()+" >>>> Inside openHomePage Method");
			
			chromeDriver.get(homePageURL);	
			chromeDriver.manage().window().maximize();
			
			/*Now accept the cookies and close the pop-up for mobile app*/
			
			Properties XpathsLoginPage = new Properties();
			FileInputStream readXPathsfromLoginPage = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\XpathsLoginPage.properties");
			XpathsLoginPage.load(readXPathsfromLoginPage);
			
			String acceptCookiesButtonXPath = XpathsLoginPage.getProperty("acceptCookiesButtonXPath");
			chromeDriver.findElement(By.xpath(acceptCookiesButtonXPath)).click();
			
			Thread.sleep(300);
			
			String closeAppPopUpButton = XpathsLoginPage.getProperty("closeAppPopUpButton");
			chromeDriver.findElement(By.xpath(closeAppPopUpButton)).click();
			
			/*adding implicit wait to hold the home screen before jumping to login screen*/
			
			chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			//System.out.println("Script waited for 5 seconds till: "+DateFormater.formatDateAndTime()); 
			
			System.out.println(DateFormater.formatDateAndTime()+" >>>> HomePage opened successfully!");
			
			/*Now click on the small sign-in button*/
			String smallSignInButton = XpathsLoginPage.getProperty("smallSignInButton");
			chromeDriver.findElement(By.xpath(smallSignInButton)).click();
			
			OpenHomePageAndLogin ohpal= new OpenHomePageAndLogin();
			ohpal.login(chromeDriver, XpathsLoginPage);
			
			/*Add soft asserts to fetch the reports*/
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertAll();
			
			readURL.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Test(priority = 2)
	public void login(ChromeDriver chromeDriver,Properties XpathsLoginPage)
	{
		try
		{
			/*Now fetch user name and password from properties file and click on bigSignInButton*/
			System.out.println(DateFormater.formatDateAndTime()+" >>>> Inside Login Method");
			
			Properties userCredentials = new Properties();
			FileInputStream readUserCredentials = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\UserCredentials.properties");
			userCredentials.load(readUserCredentials);
			
			String emailAddress = userCredentials.getProperty("emailAddress");
			String usernameBox = XpathsLoginPage.getProperty("usernameBox");
			chromeDriver.findElement(By.cssSelector(usernameBox)).sendKeys(emailAddress);
			
			String password = userCredentials.getProperty("password");
			String passwordBox = XpathsLoginPage.getProperty("passwordBox");
			chromeDriver.findElement(By.cssSelector(passwordBox)).sendKeys(password);
			
			/*Thread.sleep(1000);
			
			String stayLoggedInCheckBox = XpathsLoginPage.getProperty("stayLoggedInCheckBox");
			WebElement element = chromeDriver.findElement(By.tagName(stayLoggedInCheckBox));
			element.click();
			System.out.println(element.getTagName()+" "+element.getText());*/
			
			
			Thread.sleep(500);
			
			
			String bigSignInButton = XpathsLoginPage.getProperty("bigSignInButton");
			chromeDriver.findElement(By.xpath(bigSignInButton)).click();
			
			System.out.println(DateFormater.formatDateAndTime()+" >>>> Login Successful!");
			
			SelectJobsOption.selectJobsOptionFromFeeds(chromeDriver);
		}
		catch(ElementClickInterceptedException ecie)
		{
			String stayLoggedInCheckBox = XpathsLoginPage.getProperty("stayLoggedInCheckBox");
			chromeDriver.findElement(By.cssSelector(stayLoggedInCheckBox)).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}