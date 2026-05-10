package com.DriverSetup;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.PoJos.DateFormater;

public class ChromeDriverSetup {

	@Test(priority =1)
	public static ChromeDriver setUpDriver()
	{
		ChromeDriver chromeDriver = null;
		try
		{
			/*First set the Properties, then FileInputStream, load the FileInputStream to Properties 
			 and then fetch the values in String*/
			System.out.println(DateFormater.formatDateAndTime()+" >>>> Setting-up ChromeDriver...");

			Properties filePaths = new Properties();
			FileInputStream readFilePaths = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\FilePaths.properties");
			filePaths.load(readFilePaths);
						
			String webDriverPath = filePaths.getProperty("webDriverPath");
			String chromeDriverFilePath = filePaths.getProperty("ChromeDriverFilePath");
			
			//initially start the DriverSetup first
			System.setProperty(webDriverPath, chromeDriverFilePath);
			chromeDriver = new ChromeDriver();
			
			System.out.println(DateFormater.formatDateAndTime()+" >>>> ChromeDriver set-up Successfully!");
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.assertAll();
			//WebDriverManager.chromedriver().setup();
			
			readFilePaths.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return chromeDriver;
	}
}
