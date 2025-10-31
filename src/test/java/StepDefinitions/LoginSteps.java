package StepDefinitions;

import Page.HomePage;
import Page.LoginPage;
import Setup.ServerSetup;
import Utility.PropertiesLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {
    HomePage homePage = new HomePage(ServerSetup.driver);
    LoginPage loginPage = new LoginPage(ServerSetup.driver);
    PropertiesLoader properties = ServerSetup.properties;

    @Given("user launches the app")
    public void user_launches_the_app(){
        Assert.assertTrue("Homepage is loaded", homePage.isHomepageLoaded());
    }

    @When("user taps on the hamburger button")
    public void user_taps_hamburger_button(){
        homePage.openHamburgerMenu();
    }
    @And("user selects Log In from the menu")
    public void user_taps_login_menu(){
        homePage.tapLoginMenu();
        Assert.assertTrue("Login page is loaded", loginPage.isLoginPageLoaded());
    }
    @And("user enters username and password")
    public void user_enters_credentials(){
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        loginPage.fillCredentials(email,password);
        Assert.assertTrue("Credentials filled", loginPage.isCredentialsFilled(email));
    }
    @And("user taps on the login button")
    public void user_taps_login_button(){
        loginPage.clickLoginButton();
    }
    @Then("user should be redirected to the homepage")
    public void user_redirected_to_homepage(){
        homePage.isHomepageLoaded();
    }



}
