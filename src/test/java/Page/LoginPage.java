package Page;

import Utility.DriverTools;
import Utility.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage {
    Locator LOGIN_TEXT = new Locator("LOGIN_TEXT", By.id("menuIV"));
    Locator USERNAME_FORM = new Locator("USERNAME_FORM", By.id("nameET"));
    Locator PASSWORD_FORM = new Locator("PASSWORD_FORM", By.id("passwordET"));
    Locator LOGIN_BUTTON = new Locator("Login button", By.id("loginBtn"));
    private DriverTools driverTools;
    public LoginPage(AndroidDriver driver){this.driverTools = new DriverTools(driver);}
    public boolean isLoginPageLoaded(){
        return driverTools.waitForElementToAppear(LOGIN_TEXT,5);
    }
    public void fillCredentials(String email, String password){
        driverTools.sendKeys(USERNAME_FORM,email);
        driverTools.sendKeys(PASSWORD_FORM,password);
    }
    public boolean isCredentialsFilled(String email){
        return (driverTools.getText(USERNAME_FORM).equals(email) &&
                driverTools.getText(PASSWORD_FORM).length()>0);
    }
    public void clickLoginButton(){
        driverTools.click(LOGIN_BUTTON);
    }
}
