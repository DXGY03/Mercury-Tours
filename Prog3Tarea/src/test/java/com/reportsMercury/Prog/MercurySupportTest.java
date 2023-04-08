package com.reportsMercury.Prog;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class MercurySupportTest {
	
	WebDriver driver;
	
	By SupportBtnLocator = By.linkText("SUPPORT");
	
	By SupportPageLocator = By.xpath("//img[@src=\"images/mast_construction.gif\"]");
	
	@BeforeClass
	public void beforeClass() 
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/newtours/index.php");
	}

	@AfterClass
	public void afterClass() 
	{
		driver.close();
	}
	
	@Test
	public void SupportTest() 
	{
		driver.findElement(SupportBtnLocator).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("Register "+System.currentTimeMillis()+".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("https://demo.guru99.com/test/newtours/images/mast_construction.gif", (driver.findElement(SupportPageLocator).getAttribute("src")));
	}
}
