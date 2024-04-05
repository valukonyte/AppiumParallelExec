package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends PageBase{

    @AndroidFindBy( uiAutomator =  "text(\"Products\")")
    WebElement productsTitle;

    @AndroidFindBy( accessibility =  "sort button")
    WebElement sortButton;

    public ProductsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void titleIsDisplayed() {
        elementIsDisplayed(productsTitle);
    }

    public void chooseSortingOption(String sortingOption) {
        sortButton.click();
        elementIsDisplayed(driver.findElement(AppiumBy.accessibilityId(sortingOption)));
    }

}
