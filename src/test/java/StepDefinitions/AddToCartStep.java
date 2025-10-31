package StepDefinitions;

import Page.HomePage;
import Page.LoginPage;
import Page.ProductDetailPage;
import Setup.ServerSetup;
import Utility.PropertiesLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AddToCartStep {
    HomePage homePage = new HomePage(ServerSetup.driver);
    ProductDetailPage productDetailPage = new ProductDetailPage(ServerSetup.driver);
    LoginPage loginPage = new LoginPage(ServerSetup.driver);
    PropertiesLoader properties = ServerSetup.properties;

    @When("user clicks on product {string}")
    public void user_clicks_on_product(String product) {
        homePage.clickProduct(product);
        Assert.assertTrue("product name is correct",productDetailPage.isProductNameCorrect(product));
    }

    @And("user selects color {string}")
    public void user_selects_color(String color) {
        productDetailPage.selectColor(color);
        Assert.assertTrue("correct color is selected",productDetailPage.isColorSelected(color));
    }

    @And("user increases product quantity by {int}")
    public void user_increases_product_quantity_by(Integer qty) {
        Assert.assertEquals(productDetailPage.getQty(),"1");
        productDetailPage.addQuantity(qty);
        Assert.assertEquals(productDetailPage.getQty(),String.valueOf(qty+1));
    }

    @And("user clicks on Add to Cart Button")
    public void user_clicks_on(){
        productDetailPage.clickAddToCart();
    }

    @Then("cart badge should show count {string}")
    public void cart_badge_should_show_count(String expectedCount) {
        Assert.assertTrue(productDetailPage.isCartNumberCorrect(expectedCount));
    }

    @And("user selects Catalogs from the menu")
    public void user_selects_from_the_menu() {
        homePage.tapCatalogMenu();
        Assert.assertTrue("Homepage is loaded", homePage.isHomepageLoaded());
    }
}
