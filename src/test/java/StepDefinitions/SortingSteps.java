package StepDefinitions;

import Page.HomePage;
import Page.LoginPage;
import Page.ProductDetailPage;
import Setup.ServerSetup;
import Utility.PropertiesLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SortingSteps {
    HomePage homePage = new HomePage(ServerSetup.driver);
    ProductDetailPage productDetailPage = new ProductDetailPage(ServerSetup.driver);
    LoginPage loginPage = new LoginPage(ServerSetup.driver);
    PropertiesLoader properties = ServerSetup.properties;

    @When("user clicks on the filter icon")
    public void user_clicks_on_the_filter_icon() {
        homePage.openSortingMenu();
    }

    @And("user selects sorting option {string}")
    public void user_selects_sorting_option(String sortingOption){
        homePage.clickSelectedSortingOption(sortingOption);
    }

    @Then("product list {string} should be sorted by {string}")
    public void product_list_should_be_sorted_by(String option, String order) {
        Assert.assertTrue(homePage.verifySortingResult(option,order));
    }
}
