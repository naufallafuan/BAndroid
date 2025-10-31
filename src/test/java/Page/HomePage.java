package Page;

import Utility.DriverTools;
import Utility.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {
    Locator SORT_ICON = new Locator("HOMEPAGE_HEADER", By.id("sortIV"));
    Locator HAMBURGER_BUTTON = new Locator("HAMBURGER_BUTTON", By.id("menuIV"));
    Locator LOGIN_MENU = new Locator("LOGIN_MENU", By.xpath("//android.widget.TextView[@text='Log In']"));
    Locator CATALOG_MENU = new Locator("CATALOG_MENU", By.xpath("//android.widget.TextView[@text='Catalog']"));
    Locator PRODUCT_PRICE_LOCATOR = new Locator("PRODUCT_PRICE_LOCATOR", By.id("priceTV"));
    Locator PRODUCT_NAME_LOCATOR = new Locator("PRODUCT_NAME_LOCATOR", By.id("titleTV"));
    private DriverTools driverTools;
    public HomePage(AndroidDriver driver){this.driverTools = new DriverTools(driver);}

    public boolean isHomepageLoaded(){
        return driverTools.waitForElementToAppear(SORT_ICON, 5);
    }
    public void openHamburgerMenu(){
        driverTools.click(HAMBURGER_BUTTON);
    }
    public void tapLoginMenu(){
        driverTools.click(LOGIN_MENU);
    }
    public void clickProduct(String product){
        driverTools.click(getProductLocator(product));
        System.out.println("Clicked product: " + product);
    }
    private Locator getProductLocator(String product){
        return new Locator("Product "+product, By.xpath("//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and @text='"+ product +"']/preceding-sibling::android.widget.ImageView"));
    }
    public void tapCatalogMenu(){
        driverTools.click(CATALOG_MENU);
    }

    public void openSortingMenu(){
        driverTools.click(SORT_ICON);
    }

    public void clickSelectedSortingOption(String sortingOption){
        driverTools.click(getSelectedSortingOption(sortingOption));
    }

    private Locator getSelectedSortingOption(String sortingOption) {
        return new Locator(
                "Selected sorting option " + sortingOption,
                By.xpath("//android.widget.TextView[@text='"+sortingOption+"']")
        );
    }

    public boolean verifySortingResult(String option, String order) {
        List<String> elements;
        List<Double> actualPrice = new ArrayList<>();
        List<String> actualName = new ArrayList<>();
        List<Double> expectedPrice;
        List<String> expectedName;
        if (option.equalsIgnoreCase("Price")) {
            elements = driverTools.getTexts(PRODUCT_PRICE_LOCATOR);
            for (String e : elements) {
                actualPrice.add(driverTools.trimPrice(e));
            }
            expectedPrice = actualPrice;

            if (order.equalsIgnoreCase("ascending")) {
                Collections.sort(expectedPrice);
            } else {
                expectedPrice.sort(Collections.reverseOrder());
            }
            return expectedPrice.equals(actualPrice);
        } else {
            elements = driverTools.getTexts(PRODUCT_NAME_LOCATOR);
            for (String e : elements) {
                actualName.add(e.trim().toLowerCase());
            }
            expectedName = actualName;
            if (order.equalsIgnoreCase("ascending")) {
                Collections.sort(expectedName);
            } else {
                expectedName.sort(Collections.reverseOrder());
            }
            return expectedName.equals(actualName);
        }
    }
}
