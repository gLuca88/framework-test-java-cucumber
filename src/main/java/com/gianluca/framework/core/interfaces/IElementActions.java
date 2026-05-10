package com.gianluca.framework.core.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface IElementActions {
    void click(By locator);

    void type(By locator, String text);

    String getText(By locator);

    boolean isDisplayed(By locator);

    List<WebElement> waitForElementsVisible(By locator);

    List<WebElement> getElements(By locator);

    void selectByVisibleText(By locator,String text);
}
