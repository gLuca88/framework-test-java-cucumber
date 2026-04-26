package com.gianluca.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    private final String  partOfUrl="inventory-item.html?id=";
    private By container_ProductName = By.cssSelector("[data-test='inventory-item-name']");
    private By button_BackToTheProduct = By.cssSelector("[data-test='back-to-products']");



    public boolean isLoaded() {
        return getCurrentUrl().contains(partOfUrl)
                && actions.isDisplayed(button_BackToTheProduct);
    }

    public String getProductNameDetailPage() {
        return actions.getText(container_ProductName);
    }


}
