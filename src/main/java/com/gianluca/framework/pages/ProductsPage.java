package com.gianluca.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private static final String EXPECTED_TITLE = "Swag Labs";
    private static final String EXPECTED_URL_PART = "inventory.html";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private By container_ProductsTile = By.className("title");

    public String getTitlePage() {
        return actions.getText(container_ProductsTile);
    }

    public boolean isPageProductsLoaded() {
        return actions.isDisplayed(container_ProductsTile)
                && getCurrentUrl().contains(EXPECTED_URL_PART)
                && getTitlePageHtml().equalsIgnoreCase(EXPECTED_TITLE);
    }

}
