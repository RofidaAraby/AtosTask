package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
	protected WebDriver driver ;

	public PageBase(WebDriver driver) 
	{ 
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	protected  static void  ClickBtn (WebElement Button ) 
	{
		Button.click(); 
	}
	protected static  void SetTextElementText( WebElement txtElement ,String value )
	{
		txtElement.sendKeys(value);
	}

	public void clearText(WebElement element) 
	{
		element.clear();
	}
}
