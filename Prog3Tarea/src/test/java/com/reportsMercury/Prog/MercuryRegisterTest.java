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

public class MercuryRegisterTest {
	
	WebDriver driver;
	
	//Localizadores de registro
	By registerLinkLocator = By.linkText("REGISTER");
	By registerPageLocator = By.xpath("//img[@src=\"images/mast_register.gif\"]");
		
	By firstNameLocator = By.name("firstName");
	By lastNameLocator = By.name("lastName");
	By phoneLocator = By.name("phone");
	By emailLocator = By.id("userName");
		
	By addressLocator = By.name("address1");
	By cityLocator = By.name("city");
	By stateLocator = By.name("state");
	By postalCodeLocator = By.name("postalCode");
	By countrySelectorLocator = By.name("country");
	By countrySelectorRDLocator = By.xpath("//option[@value=\"DOMINICAN REPUBLIC\"]");
		
	By userNameLocator = By.name("email");
	By passwordLocator = By.name("password");
	By confirmPassLocator = By.name("confirmPassword");
		
	By registerBtnLocator = By.xpath("//input[@src=\"images/submit.gif\"]");  
	By registerConfirmLocator = By.tagName("font");
	
	@Test
	public void registerUser() 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(registerLinkLocator).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		if(driver.findElement(registerPageLocator).isDisplayed()) 
		{			
			driver.findElement(firstNameLocator).sendKeys("Diego");
			driver.findElement(lastNameLocator).sendKeys("Olivero Matos");
			driver.findElement(phoneLocator).sendKeys("(+1) 809 405 6313");
			driver.findElement(emailLocator).sendKeys("diegooliveromatos@gmail.com");
			
			driver.findElement(addressLocator).sendKeys("Flor de loto #4, Lotes y Servicios");
			driver.findElement(cityLocator).sendKeys("Sabana Perdida");
			driver.findElement(stateLocator).sendKeys("Santo Domingo");
			driver.findElement(postalCodeLocator).sendKeys("11404");
			driver.findElement(countrySelectorLocator).click();
			driver.findElement(countrySelectorRDLocator).click();
			
			driver.findElement(userNameLocator).sendKeys("DiegoGo");
			driver.findElement(passwordLocator).sendKeys("Mateo5:3");
			driver.findElement(confirmPassLocator).sendKeys("Mateo5:3");
			
			driver.findElement(registerBtnLocator).click();
		}
		else
		{
			System.out.println("Register page was not found");
		}
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("Register "+System.currentTimeMillis()+".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<WebElement> fonts = driver.findElements(By.tagName("font"));
		assertEquals("Note: Your user name is DiegoGo.", fonts.get(5).getText());
	}
	  
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
}
