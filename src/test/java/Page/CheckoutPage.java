package Page;

import Utility.DriverTools;
import Utility.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class CheckoutPage {
    Locator FULL_NAME = new Locator("FULL_NAME", By.id("fullNameET"));
    Locator ADDRESS_1 = new Locator("ADDRESS_1", By.id("address1ET"));
    Locator ADDRESS_2 = new Locator("ADDRESS_2", By.id("address2ET"));
    Locator CITY = new Locator("CITY", By.id("cityET"));
    Locator STATE = new Locator("STATE", By.id("stateET"));
    Locator POSTAL_CODE = new Locator("POSTAL_CODE", By.id("zipET"));
    Locator COUNTRY = new Locator("COUNTRY", By.id("countryET"));
    Locator PAYMENT_BUTTON = new Locator("PAYMENT_BUTTON", By.id("paymentBtn"));
    Locator CARD_USERNAME = new Locator("CARD_USERNAME", By.id("nameET"));
    Locator CARD_NUMBER = new Locator("CARD_NUMBER", By.id("cardNumberET"));
    Locator CARD_EXPIRY_DATE = new Locator("CARD_EXPIRY_DATE", By.id("expirationDateET"));
    Locator CARD_CVV = new Locator("CARD_CVV", By.id("securityCodeET"));
    Locator FULL_NAME_RESULT = new Locator("FULL_NAME_RESULT", By.id("fullNameTV"));
    Locator ADDRESS_1_RESULT = new Locator("ADDRESS_1_RESULT", By.id("addressTV"));
    Locator CITY_RESULT = new Locator("CITY_RESULT", By.id("cityTV"));
    Locator COUNTRY_RESULT = new Locator("COUNTRY_RESULT", By.id("countryTV"));
    Locator CARD_FULL_NAME_RESULT = new Locator("CARD_FULL_NAME_RESULT", By.id("cardHolderTV"));
    Locator CARD_NUMBER_RESULT = new Locator("CARD_NUMBER_RESULT", By.id("cardNumberTV"));
    Locator CARD_EXPIRY_DATE_RESULT = new Locator("CARD_EXPIRY_DATE_RESULT", By.id("expirationDateTV"));
    Locator DELIVERY_FEE = new Locator("DELIVERY_FEE", By.id("amountTV"));
    Locator TOTAL_PRICE = new Locator("TOTAL_PRICE", By.id("totalAmountTV"));
    Locator HORSE_IMAGE = new Locator("HORSE_IMAGE", By.id("horseIV"));
    Locator CONTINUE_SHOPPING_BUTTON = new Locator("CONTINUE_SHOPPING_BUTTON", By.id("shoopingBt"));
    private DriverTools driverTools;
    public CheckoutPage(AndroidDriver driver){this.driverTools = new DriverTools(driver);}

    public void fillShippingDetails(String fullName,String address1,String address2,String city,String state,String postalCode,String country){
        driverTools.sendKeys(FULL_NAME,fullName);
        driverTools.sendKeys(ADDRESS_1,address1);
        driverTools.sendKeys(ADDRESS_2,address2);
        driverTools.sendKeys(CITY,city);
        driverTools.sendKeys(STATE,state);
        driverTools.sendKeys(POSTAL_CODE,postalCode);
        driverTools.sendKeys(COUNTRY,country);
    }

    public void clickContinueButton(){
        driverTools.click(PAYMENT_BUTTON);
    }
    
    public void fillPaymentDetails(String cardName, String cardNumber, String cardExp, String cardCVV){
        driverTools.sendKeys(CARD_USERNAME,cardName);
        driverTools.sendKeys(CARD_NUMBER,cardNumber);
        driverTools.sendKeys(CARD_EXPIRY_DATE,cardExp);
        driverTools.sendKeys(CARD_CVV,cardCVV);
    }

    public Boolean isFilledDataCorrect(Map<String, String> expected){
        driverTools.scrollToEndAction();
        boolean allMatch = true;
        Map<String, String> actual = new HashMap<>();
        actual.put("FULL_NAME_RESULT", driverTools.getText(FULL_NAME_RESULT));
        actual.put("ADDRESS_1_RESULT", driverTools.getText(ADDRESS_1_RESULT));
        actual.put("CITY_RESULT", driverTools.getText(CITY_RESULT).split(",",2)[0].trim());
        actual.put("STATE_RESULT", driverTools.getText(CITY_RESULT).split(",",2)[1].trim());
        actual.put("COUNTRY_RESULT", driverTools.getText(COUNTRY_RESULT).split(",",2)[0].trim());
        actual.put("POSTAL_CODE_RESULT", driverTools.getText(COUNTRY_RESULT).split(",",2)[1].trim());
        actual.put("CARD_FULL_NAME_RESULT", driverTools.getText(CARD_FULL_NAME_RESULT));
        actual.put("CARD_NUMBER_RESULT", driverTools.getText(CARD_NUMBER_RESULT));
        actual.put("CARD_EXPIRY_DATE_RESULT", driverTools.getText(CARD_EXPIRY_DATE_RESULT).split(":")[1].trim().replace("/",""));

        for (Map.Entry<String, String> entry : expected.entrySet()) {
            String key = entry.getKey();
            String expectedValue = entry.getValue().trim();
            String actualValue = actual.get(key).trim();

            if (!expectedValue.equalsIgnoreCase(actualValue)) {
                allMatch = false;
                System.out.println("Mismatch in: " + key);
                System.out.println("Expected: " + expectedValue);
                System.out.println("Actual  : " + actualValue);
            }else {
                System.out.println("Match: " + key + " -> " + actualValue);
            }
        }
        return allMatch;
    }

    public Boolean isPriceCorrect(Double subTotalPrice){
        Double deliveryFee = driverTools.trimPrice(driverTools.getText(DELIVERY_FEE));
        Double totalPrice = driverTools.trimPrice(driverTools.getText(TOTAL_PRICE));

        if(subTotalPrice + deliveryFee != totalPrice) return false;

        return true;
    }

    public Boolean isCheckoutComplete(){
        return driverTools.waitForElementToAppear(HORSE_IMAGE,5);
    }

    public void pressContinueShoppingButton(){
        driverTools.click(CONTINUE_SHOPPING_BUTTON);
    }
}
