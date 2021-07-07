package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.Assert;
//import org.testng.annotations.BeforeClass;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
//import org.testng.annotations.DataProvider;

import pages.HomePage;
//import io.qameta.allure.Description;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistration;
import utilities.ExcelFileManager;


public class UserRegitrationTest extends TestBase {
    HomePage HomePageObject;
    UserRegistration RegisterObject;
    MyAccountPage myAccountPage;
    LoginPage loginPage;
    String[] TC_Fields;
    ExcelFileManager excel;
    String FilePathOfTestData = "src/test/resources/TestData/TestData.xlsx";

    @BeforeMethod
    public void setup() {
        HomePageObject = new HomePage(driver);
        RegisterObject = new UserRegistration(driver);
        myAccountPage = new MyAccountPage(driver);
        loginPage = new LoginPage(driver);
        HomePageObject.navigateTOUserRegistrationPage();


    }

	
	@DataProvider(name="ExcelDataForHappySecnario")
	public Object[][] ReadFromexcel_HappySecnario()
	{
        excel = new ExcelFileManager(new File(FilePathOfTestData));
        excel.switchToSheet("SignUp_Data");
		TC_Fields = new String[] {"FirstName", "LastName", "Phone", "Email", "Password","Expected Result"};
		Object[][]Locator = new Object[1][6];
		for (int i=0;i<TC_Fields.length;i++) {
			Locator[0][i] =excel.getCellData(TC_Fields[i],2);
			}	 
		return Locator;
	}



    @DataProvider(name = "ExcelDataForbadSecnario")
    public Object[][] ReadFromexcel_badSecnario() {
        excel = new ExcelFileManager(new File(FilePathOfTestData));
        excel.switchToSheet("SignUp_Data");
        TC_Fields = new String[]{"FirstName", "LastName", "Phone", "Email", "Password", "Expected Result"};
        Object[][] Temp = new Object[5][6];
        for (int j = 3; j < 8; j++) {
            for (int i = 0; i < TC_Fields.length; i++) {
                Temp[j - 3][i] = excel.getCellData(TC_Fields[i], j);
            }
        }

        return Temp;
    }

	@Test (alwaysRun=true ,dataProvider="ExcelDataForHappySecnario")
	@Description("Registration with valid inputs and verify user can login correctly")
	public void registerWithValidIputs_Happysecnario(String firstname ,String lastname,String phone , String email , String password ,String ExpectedResult) throws InterruptedException {
		HomePageObject.navigateTOUserRegistrationPage();
		RegisterObject.UserRegistrationForm(firstname ,lastname,phone,email,password);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(RegisterObject.registerSuccessMessage.getText(),ExpectedResult);
		Thread.sleep(2000);
		myAccountPage.performLogout();
		loginPage.userLogin(email, password);
	}

    @Test(alwaysRun = true, dataProvider = "ExcelDataForbadSecnario")
    @Description("Verify user can't register with invalid data and detect cases which system doesn't handle")
    public void registerWithInvalidIputs_badSecnario(String firstname, String lastname, String phone, String email, String password, String ExpectedResult) throws InterruptedException {
        RegisterObject.UserRegistrationForm(firstname, lastname, phone, email, password);
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(2000);


        if (driver.getCurrentUrl().contains("account")){
            myAccountPage.performLogout();
            Assert.fail("User registration shouldn't be performed");
        } else {
            Assert.assertEquals(RegisterObject.registerFaildMessage.getText(), ExpectedResult);
        }


        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterClass
    public void tearDown() {
        StopDriver();
    }
    @AfterMethod
    @Step("Take Image")
    public void takePhoto(ITestResult result, Method method) {
        captureScreenshot(result.getName());
    }
}




/*


	@Test (alwaysRun=true )
	public void RegisterwithinvalidFirstName()
	{   
		RegisterObject=new UserRegistration(driver);
    homePageObject.navigateTOUserRegistrationPage();
	RegisterObject.UserRegistrationForm(excel.getCellData("FirstName",3),excel.getCellData("LastName",3),excel.getCellData("Phone",3),excel.getCellData("Email",3),excel.getCellData("Password",3));
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Assert.assertEquals(RegisterObject.registerFaildMessage.getText(),excel.getCellData("Expected Result",3));
	
	 } 
@Test (alwaysRun=true )
public void RegisterwithinvalidLastName()
{RegisterObject=new UserRegistration(driver);
	homePageObject.navigateTOUserRegistrationPage();
	RegisterObject.UserRegistrationForm(excel.getCellData("FirstName",4),excel.getCellData("LastName",4),excel.getCellData("Phone",4),excel.getCellData("Email",4),excel.getCellData("Password",4));
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Assert.assertEquals(RegisterObject.registerFaildMessage.getText(),excel.getCellData("Expected Result",4));

}
@Test (alwaysRun=true )
public void RegisterwithOreadyExitEmail()
{RegisterObject=new UserRegistration(driver);
	homePageObject.navigateTOUserRegistrationPage();
	RegisterObject.UserRegistrationForm(excel.getCellData("FirstName",5),excel.getCellData("LastName",5),excel.getCellData("Phone",5),excel.getCellData("Email",5),excel.getCellData("Password",5));
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Assert.assertEquals(RegisterObject.registerFaildMessage.getText(),excel.getCellData("Expected Result",5));


}
@Test (alwaysRun=true )
public void RegisterwithOreadyInvalidFormat()
{RegisterObject=new UserRegistration(driver);
	homePageObject.navigateTOUserRegistrationPage();
	RegisterObject.UserRegistrationForm(excel.getCellData("FirstName",6),excel.getCellData("LastName",6),excel.getCellData("Phone",6),excel.getCellData("Email",6),excel.getCellData("Password",6));
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Assert.assertEquals(RegisterObject.registerFaildMessage.getText(),excel.getCellData("Expected Result",6));


}
@Test (alwaysRun=true )
public void RegisterwithOreadyInvalidPassword()
{RegisterObject=new UserRegistration(driver);
	homePageObject.navigateTOUserRegistrationPage();
	RegisterObject.UserRegistrationForm(excel.getCellData("FirstName",7),excel.getCellData("LastName",7),excel.getCellData("Phone",7),excel.getCellData("Email",6),excel.getCellData("Password",7));
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Assert.assertEquals(RegisterObject.registerFaildMessage.getText(),excel.getCellData("Expected Result",7));
}

}*/
