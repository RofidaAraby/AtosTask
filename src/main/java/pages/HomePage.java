package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		
	}
	@Step("Navagiate to Registration Page")
	public void navigateTOUserRegistrationPage()
		{
			driver.get("https://www.phptravels.net/register");
		}
}