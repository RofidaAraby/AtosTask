package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends PageBase{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//div[@class='dropdown dropdown-login dropdown-tab']")
    WebElement accountDropDown;

    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logOut;

    @Step("Perform Logout")
    public void performLogout(){
        ClickBtn(accountDropDown);
        ClickBtn(logOut);
    }

}
