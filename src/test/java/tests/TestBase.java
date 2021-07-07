package tests;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Helper;

public class TestBase {

	public static WebDriver driver;
	@BeforeSuite   
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome")String browserName )  
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup(); 
			driver= new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();  
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("safari")){
			driver = new SafariDriver();
		}
		else
		{
			WebDriverManager.iedriver().setup(); 
			driver= new InternetExplorerDriver();
		}	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		//driver.get("https://www.phptravels.net/home");
	}
	@AfterSuite  
	public void StopDriver()
	{
		driver.quit();
	}

	/*@AfterMethod
	public void screenshotOnFailure(ITestResult result)
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(driver, result.getName());
		}
	}*/

	@Attachment(value = "Screenshot", type = "image/png")
	public static byte[] captureScreenshot(String screenshotname)
	{
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}
