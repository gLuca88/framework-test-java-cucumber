package com.gianluca.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    private static final String EXPECTED_TITLE = "Swag Labs";
    private static final String EXPECTED_URL_PART = "inventory.html";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private By container_ProductsTile = By.className("title");
    private By button_MenuHamburgher = By.id("react-burger-menu-btn");
    private By button_LogOut = By.cssSelector("a[data-test='logout-sidebar-link']");

    private By container_Product = By.className("inventory_item");
    private By container_NameProduct = By.cssSelector("div[class='inventory_item_name ']");
    private By container_Descricption = By.className("inventory_item_desc");
    private By container_Price = By.className("inventory_item_price");


    public String getTitlePage() {
        return actions.getText(container_ProductsTile);
    }


    public boolean isPageProductsLoaded() {
        return actions.isDisplayed(container_ProductsTile)
                && getCurrentUrl().contains(EXPECTED_URL_PART)
                && getTitlePageHtml().equalsIgnoreCase(EXPECTED_TITLE);
    }

    public void executeLogout() {
        actions.click(button_MenuHamburgher);
        actions.click(button_LogOut);
    }

    public boolean areProductsLoaded(int nProducts) {

        List<WebElement> products = actions.getElements(container_Product);
        if (products == null || products.isEmpty()) {
            return false;
        }
        if (products.size() < nProducts) {
            return false;
        }

        return products.stream().allMatch(WebElement::isDisplayed);
    }

    public boolean areAllProductsComplete(){
        List<WebElement> productList=actions.getElements(container_Product);

        if(productList==null||productList.isEmpty()){
            return false;
        }

        for(WebElement product: productList){

            String nameP=product.findElement(container_NameProduct).getText();
            String descriptionP=product.findElement(container_Descricption).getText();
            String priceP=product.findElement(container_Price).getText();

            if (nameP.trim().isEmpty() || descriptionP.trim().isEmpty() || priceP.trim().isEmpty()) {
                return false;
            }

        }

        return true;
    }


}
