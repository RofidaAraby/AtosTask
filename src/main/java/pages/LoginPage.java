package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends  PageBase{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "username")
    WebElement emailTextBox;

    @FindBy(name = "password")
    WebElement passwordTxtBox;
    @FindBy(xpath="//button[contains(text(),'Login')]")
    WebElement lognBtn;

    @Step("Perform User Login")
    public void userLogin(String email ,String password)
    {
        SetTextElementText(emailTextBox,email);
        SetTextElementText(passwordTxtBox,password);
        ClickBtn(lognBtn);
    }

}









