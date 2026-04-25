package com.gianluca.framework.core.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface IWaitStrategy {

    WebElement waitForElementVisible(By locator);

    WebElement waitForElementVisible(By locator, int timeout);

    WebElement waitForElementClickable(By locator);

    WebElement waitForElementClickable(By locator, int timeout);

    WebElement waitForElementPresent(By locator);

    List<WebElement> waitForElementsVisible(By locator);

    List<WebElement> waitForElementsVisible(By locator, int timeout);

    void waitForElementInvisible(By locator);
}