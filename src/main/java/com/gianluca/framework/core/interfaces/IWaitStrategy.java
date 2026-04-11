package com.gianluca.framework.core.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IWaitStrategy {

    WebElement waitForElementVisible(By locator);

    WebElement waitForElementVisible(By locator, int timeout);

    WebElement waitForElementClickable(By locator);

    WebElement waitForElementClickable(By locator, int timeout);

    WebElement waitForElementPresent(By locator);

    void waitForElementInvisible(By locator);
}