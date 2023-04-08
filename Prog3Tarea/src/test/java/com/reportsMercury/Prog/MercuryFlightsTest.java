package com.reportsMercury.Prog;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class MercuryFlightsTest {
	
	WebDriver driver;
	
	//Localizadores de Flights
	By flightsBtnLocator = By.linkText("Flights");
	
	By flightsPassenLocator = By.xpath("//select[@name=\"passCount\"]");
	By flightsPassenCuantLocator = By.xpath("//option[@value=\"3\"]");
	
	By flightsDeptLocator = By.xpath("//select[@name=\"fromPort\"]");
	By flightsDeptFromLocator = By.xpath("//option[@value=\"New York\"]");
	
	By flightsMonthLocator = By.xpath("//select[@name=\"fromMonth\"]");
	By flightsMonthSelectLocator = By.xpath("//option[@value=\"9\"]");
	By flightsDayLocator = By.xpath("//select[@name=\"fromDay\"]");
	By flightsDaySelectLocator = By.xpath("//option[@value=\"17\"]");
	
	By flightsPortLocator = By.xpath("//select[@name=\"toPort\"]");
	By flightsPortSelectLocator = By.xpath("//option[@value=\"Paris\"]");

	By flightsRetMonthLocator = By.xpath("//select[@name=\"toMonth\"]");
	By flightsRetMonthSelectLocator = By.xpath("//option[@value=\"12\"]");
	By flightsRetDayLocator = By.xpath("//select[@name=\"toDay\"]");
	By flightsRetDaySelectLocator = By.xpath("//option[@value=\"4\"]");
	
	By flightsClassSelectLocator = By.xpath("//input[@value=\"First\"]");
	
	By flightsAirlineLocator = By.xpath("//select[@name=\"airline\"]");
	By flightsAirlineValueLocator = By.xpath("//option[@value=\"Paris\"]");
	
	By flightsSendLocator = By.xpath("//input[@src=\"images/continue.gif\"]");
	
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
	public void testFlights() 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(flightsBtnLocator).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(flightsPassenLocator).click();
		driver.findElement(flightsPassenCuantLocator).click();
		
		driver.findElement(flightsDeptLocator).click();
		driver.findElement(flightsDeptFromLocator).click();
		
		driver.findElement(flightsMonthLocator).click();
		driver.findElement(flightsMonthSelectLocator).click();
		driver.findElement(flightsDayLocator).click();
		driver.findElement(flightsDaySelectLocator).click();
		
		driver.findElement(flightsPortLocator).click();
		driver.findElement(flightsPortSelectLocator).click();
		
		driver.findElement(flightsRetMonthLocator).click();
		driver.findElement(flightsRetMonthSelectLocator).click();
		driver.findElement(flightsRetDayLocator).click();
		driver.findElement(flightsRetDaySelectLocator).click();
		
		driver.findElement(flightsClassSelectLocator).click();
		List<WebElement> opciones = driver.findElements(By.xpath("//select[@name=\"airline\"]//option"));
		opciones.get(1).click();
		
		driver.findElement(flightsSendLocator).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("Register "+System.currentTimeMillis()+".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<WebElement> fonts = driver.findElements(By.tagName("font"));
		assertEquals("Please press your browser's back button to return to the previous page or click the \"BACK TO HOME\" icon below to go to the homepage.", fonts.get(12).getText());
	}
}
