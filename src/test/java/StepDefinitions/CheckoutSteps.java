package StepDefinitions;

import Page.*;
import Setup.ServerSetup;
import Utility.PropertiesLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

public class CheckoutSteps {
    HomePage homePage = new HomePage(ServerSetup.driver);
    ProductDetailPage productDetailPage = new ProductDetailPage(ServerSetup.driver);
    LoginPage loginPage = new LoginPage(ServerSetup.driver);
    BookingPage bookingPage = new BookingPage(ServerSetup.driver);
    CheckoutPage checkoutPage = new CheckoutPage(ServerSetup.driver);
    CartPage cartPage  = new CartPage(ServerSetup.driver);
    PropertiesLoader properties = ServerSetup.properties;
    private Double subPrice;

    private String fullName = properties.getProperty("fullName");
    private String address1 = properties.getProperty("address1");
    private String address2 = properties.getProperty("address2");
    private String city = properties.getProperty("city");
    private String state = properties.getProperty("state");
    private String postalCode = properties.getProperty("postalCode");
    private String country = properties.getProperty("country");
    private String cardName = properties.getProperty("cardName");
    private String cardNumber = properties.getProperty("cardNumber");
    private String cardExp = properties.getProperty("cardExp");
    private String cardCvv = properties.getProperty("cardCvv");
    @And("user clicks on shopping cart icon")
    public void user_clicks_on_shopping_cart_icon() {
        subPrice = productDetailPage.getProductPrice();
        productDetailPage.clickCartIcon();
    }

    @And("user clicks on Proceed To Checkout button")
    public void user_clicks_on_proceed_to_checkout_button() {
        Assert.assertEquals(subPrice,cartPage.getPrice("total"));
        Assert.assertEquals(subPrice,cartPage.getPrice("sub"));
        cartPage.pressCheckoutButton();
    }

    @And("user fills out shipping details")
    public void user_fills_out_shipping_details() {
        checkoutPage.fillShippingDetails(fullName,address1,address2,city,state,postalCode,country);
        checkoutPage.clickContinueButton();
    }

    @And("user fills out payment details")
    public void user_fills_out_payment_details() {
        checkoutPage.fillPaymentDetails(cardName, cardNumber, cardExp, cardCvv);
        checkoutPage.clickContinueButton();
    }

    @And("user presses Place Order button")
    public void user_presses_place_order_button() {
        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("FULL_NAME_RESULT", fullName);
        expected.put("ADDRESS_1_RESULT", address1);
        expected.put("CITY_RESULT", city);
        expected.put("STATE_RESULT", state);
        expected.put("COUNTRY_RESULT", country);
        expected.put("POSTAL_CODE_RESULT", postalCode);
        expected.put("CARD_FULL_NAME_RESULT", cardName);
        expected.put("CARD_NUMBER_RESULT", cardNumber);
        expected.put("CARD_EXPIRY_DATE_RESULT", cardExp);

        Assert.assertTrue(checkoutPage.isFilledDataCorrect(expected));
        Assert.assertTrue(checkoutPage.isPriceCorrect(subPrice));
        checkoutPage.clickContinueButton();
    }

    @Then("user should see Checkout Complete page")
    public void user_should_see_checkout_complete_page() {
        Assert.assertTrue(checkoutPage.isCheckoutComplete());
    }

    @When("user presses Continue Shopping button")
    public void user_presses_continue_shopping_button() {
        checkoutPage.pressContinueShoppingButton();
    }
}
