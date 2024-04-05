package tests;

import org.testng.annotations.Test;
import pages.ProductsPage;


public class ProductsPageTest extends Base {
    ProductsPage productsPage;

    @Test
    public void sortProductsTest(){
        productsPage = new ProductsPage(driver);
        productsPage.titleIsDisplayed();
        productsPage.chooseSortingOption("nameAsc");
    }
}

