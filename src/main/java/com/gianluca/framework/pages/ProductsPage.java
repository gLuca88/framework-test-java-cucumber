package com.gianluca.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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
    private By badge_cart = By.cssSelector("[data-test='shopping-cart-badge']");
    private By button_addToCart = By.xpath("//button[contains(@class,'btn btn_primary')]");
    private By button_Remove_AfterAdd = By.xpath("//button[contains(@class,'btn btn_secondary')]");
    private By selectSorting = By.className("product_sort_container");



    public By getProductLink(String productName) {
        return By.xpath("//a[.//div[@data-test='inventory-item-name' and normalize-space()='"
                + productName + "']]");
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

        List<WebElement> products = actions.waitForElementsVisible(container_Product);
        if (products == null || products.isEmpty()) {
            return false;
        }
        if (products.size() < nProducts) {
            return false;
        }

        return products.stream().allMatch(WebElement::isDisplayed);
    }

    public boolean areAllProductsComplete() {
        List<WebElement> productList = actions.waitForElementsVisible(container_Product);

        if (productList == null || productList.isEmpty()) {
            return false;
        }

        for (WebElement product : productList) {

            String nameP = product.findElement(container_NameProduct).getText();
            String descriptionP = product.findElement(container_Descricption).getText();
            String priceP = product.findElement(container_Price).getText();

            if (nameP.trim().isEmpty() || descriptionP.trim().isEmpty() || priceP.trim().isEmpty()) {
                return false;
            }

        }

        return true;
    }

    public void clickNameProduct(String nameproduct) {
        actions.click(getProductLink(nameproduct));
    }

    public boolean isBadgeIsVisible() {
        List<WebElement> listBadge = actions.getElements(badge_cart);

        return !listBadge.isEmpty();
    }

    public void clickAddProduct(int nProduct) {
        List<WebElement> product = actions.waitForElementsVisible(container_Product);

        for (int i = 0; i < nProduct; i++) {
            WebElement singleProduct = product.get(i);
            WebElement buttonAdd = singleProduct.findElement(button_addToCart);
            buttonAdd.click();
        }

    }

    public int getTextBadgeCart() {
        return Integer.parseInt(actions.getText(badge_cart));
    }

    public List<String> getTextButtonsAddToCart(int nProducts) {

        List<WebElement> products = actions.waitForElementsVisible(container_Product);
        List<String> testProducts = new ArrayList<>();
        for (int i = 0; i < nProducts; i++) {
            WebElement singleProduct = products.get(i);
            testProducts.add(singleProduct.findElement(button_Remove_AfterAdd).getText());
        }
        return testProducts;
    }

    public void selectSortingOption(String sortingOption) {

        actions.selectByVisibleText(selectSorting, sortingOption);
    }

    public List<String> getAllProductNames() {

        List<WebElement> products =
                actions.waitForElementsVisible(container_NameProduct);

        return products.stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> getAllProductPrices() {

        List<WebElement> prices =
                actions.waitForElementsVisible(container_Price);

        return prices.stream()
                .map(e -> e.getText().replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

}
