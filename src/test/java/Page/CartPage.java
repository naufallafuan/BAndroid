package Page;

import Utility.DriverTools;
import Utility.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CartPage {

    Locator CHECKOUT_BUTTON = new Locator("CHECKOUT_BUTTON", By.id("cartBt"));
    Locator TOTAL_PRICE = new Locator("TOTAL_PRICE", By.id("totalPriceTV"));
    Locator SUB_PRICE = new Locator("SUB_PRICE", By.id("priceTV"));
    private DriverTools driverTools;
    public CartPage(AndroidDriver driver){this.driverTools = new DriverTools(driver);}

    public void pressCheckoutButton(){
        driverTools.click(CHECKOUT_BUTTON);
    }
    public Double getPrice(String type){
        if(type.equalsIgnoreCase("total")){
            return driverTools.trimPrice(driverTools.getText(TOTAL_PRICE));
        }else{
            return driverTools.trimPrice(driverTools.getText(SUB_PRICE));
        }
    }
}
