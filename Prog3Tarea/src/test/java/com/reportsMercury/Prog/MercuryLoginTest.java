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

public class MercuryLoginTest 
{
	WebDriver driver;
	
	//Localizadores de Login
	By loginBtnLocator = By.linkText("SIGN-ON");
	By loginPageLocator = By.xpath("//img[@src=\"images/mast_signon.gif\"]");	
	
	By loginUserNameLocator = By.name("userName");
	By loginPasswordLocator = By.name("password");
	
	By submitBtnLocator = By.name("submit");
	By submitSuccessLocator = By.xpath("//h3");
	
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
	public void loginUser() 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(loginBtnLocator).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		if(driver.findElement(loginPageLocator).isDisplayed()) 
		{
			driver.findElement(loginUserNameLocator).clear();
			driver.findElement(loginUserNameLocator).sendKeys("DiegoGo");
			
			driver.findElement(loginPasswordLocator).clear();
			driver.findElement(loginPasswordLocator).sendKeys("Mateo5:3");
			
			driver.findElement(submitBtnLocator).click();
		}
		else
		{
			System.out.println("Login page was not found");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("Register "+System.currentTimeMillis()+".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("Login Successfully", driver.findElement(submitSuccessLocator).getText());
	}
}
