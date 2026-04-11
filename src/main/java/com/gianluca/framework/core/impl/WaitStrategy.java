package com.gianluca.framework.core.impl;

import com.gianluca.framework.core.interfaces.IWaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitStrategy implements IWaitStrategy {

    private WebDriver driver;
    private WebDriverWait defaultWait;

    private static final int DEFAULT_TIMEOUT = 10;

    public WaitStrategy(WebDriver driver) {
        this.driver = driver;
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    private WebDriverWait getWait(int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    // ===================== VISIBLE =====================

    @Override
    public WebElement waitForElementVisible(By locator) {
        return defaultWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public WebElement waitForElementVisible(By locator, int timeout) {
        return getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ===================== CLICKABLE =====================

    @Override
    public WebElement waitForElementClickable(By locator) {
        return defaultWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Override
    public WebElement waitForElementClickable(By locator, int timeout) {
        return getWait(timeout).until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ===================== PRESENT =====================

    @Override
    public WebElement waitForElementPresent(By locator) {
        return defaultWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ===================== INVISIBLE =====================

    @Override
    public void waitForElementInvisible(By locator) {
        defaultWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
