package Page;

import Utility.DriverTools;
import Utility.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductDetailPage {
    Locator PRODUCT_NAME = new Locator("PRODUCT_NAME", By.id("productTV"));
    Locator SELECTED_COLOR_INDICATOR = new Locator("SELECTED_COLOR_INDICATOR", By.xpath("productTV"));
    Locator ADD_TO_CART_BUTTON = new Locator("ADD_TO_CART_BUTTON", By.id("cartBt"));
    Locator CART_ICON = new Locator("CART_ICON", By.id("cartTV"));
    Locator INCREASE_QTY = new Locator("INCREASE_QTY", By.id("plusIV"));
    Locator PRODUCT_PRICE = new Locator("PRODUCT_PRICE", By.id("priceTV"));
    Locator QTY = new Locator("QTY", By.id("noTV"));
    private DriverTools driverTools;
    public ProductDetailPage(AndroidDriver driver){this.driverTools = new DriverTools(driver);}

    public boolean isProductNameCorrect(String product){
        return driverTools.getText(PRODUCT_NAME).equals(product);
    }
    public void selectColor(String color){
        driverTools.click(getProductColorLocator(color));
    }
    private Locator getProductColorLocator(String color){
        return new Locator("Color "+color, By.xpath("//android.widget.ImageView[@content-desc='"+color+" color']"));
    }
    private Locator getSelectedColorLocator(String color) {
        return new Locator(
                "Selected color " + color,
                By.xpath("//android.widget.ImageView[@content-desc='Indicates when color is selected']"
                        + "/following-sibling::android.widget.ImageView[@content-desc='" + color + " color']")
        );
    }
    public boolean isColorSelected(String color) {
        return driverTools.waitForElementToAppear(getSelectedColorLocator(color),5);
    }
    public void addQuantity(Integer qty){
        for(int i=0;i<qty;i++){
            driverTools.click(INCREASE_QTY);
        }
    }
    public String getQty(){
        return driverTools.getText(QTY);
    }
    public void clickAddToCart(){
        driverTools.click(ADD_TO_CART_BUTTON);
    }
    public boolean isCartNumberCorrect(String expectedCount){
        return driverTools.getText(CART_ICON).equals(expectedCount);
    }

    public void clickCartIcon(){
        driverTools.click(CART_ICON);
    }
    public Double getProductPrice(){
        return driverTools.trimPrice(driverTools.getText(PRODUCT_PRICE));
    }
}
