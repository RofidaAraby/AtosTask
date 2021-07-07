package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistration extends PageBase {

	public UserRegistration(WebDriver driver) {
		super(driver);
	
	}
	
	@FindBy(name="firstname")
	WebElement firstNameTxtBox;
	@FindBy(name="lastname")
	WebElement lastNametBox;
	@FindBy(name ="phone")
	WebElement phoneTxtBox;
	@FindBy(name ="email")
	WebElement emailTxtBox;
	@FindBy(name ="password")
	WebElement passTxtBox;	
	@FindBy(name="confirmpassword")
	WebElement confirmPassTxtBox;
	@FindBy(css="button.signupbtn.btn_full.btn.btn-success.btn-block.btn-lg")
	WebElement signUpBtn; 
	@FindBy(xpath = "//h3[@class = 'text-align-left']")
	public WebElement registerSuccessMessage;
	
	@FindBy(xpath="//div[@class='alert alert-danger']")
	public WebElement registerFaildMessage;

	

	@Step("Perform User Registration")
	public void UserRegistrationForm(String firstName ,String lastName,String phone ,String email ,String pass  )
	{
	SetTextElementText(firstNameTxtBox, firstName);
	SetTextElementText(lastNametBox,lastName);
	SetTextElementText(phoneTxtBox,phone);
	SetTextElementText(emailTxtBox,email);
	SetTextElementText(passTxtBox,pass);
	SetTextElementText(confirmPassTxtBox,pass);
		ClickBtn(signUpBtn);
		
	}
	

	
}
