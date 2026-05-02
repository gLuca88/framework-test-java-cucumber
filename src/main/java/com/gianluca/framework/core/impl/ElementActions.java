package com.gianluca.framework.core.impl;

import com.gianluca.framework.core.expections.FrameworkException;
import com.gianluca.framework.core.interfaces.IElementActions;
import com.gianluca.framework.core.interfaces.IWaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ElementActions implements IElementActions {

    private WebDriver driver;
    private IWaitStrategy wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitStrategy(driver);
    }

    @Override
    public void click(By locator) {
        try {
            WebElement element = wait.waitForElementClickable(locator);
            element.click();
        } catch (Exception e) {
            throw new FrameworkException("Errore durante il click su: " + locator, e);
        }
    }

    @Override
    public void type(By locator, String text) {
        try {
            WebElement element = wait.waitForElementVisible(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new FrameworkException("Errore durante il type su: " + locator + " con valore: " + text, e);
        }
    }

    @Override
    public String getText(By locator) {
        try {
            return wait.waitForElementVisible(locator).getText();
        } catch (Exception e) {
            throw new FrameworkException("Errore durante il getText su: " + locator, e);
        }
    }

    @Override
    public boolean isDisplayed(By locator) {
        try {
            return wait.waitForElementVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> waitForElementsVisible(By locator) {
        try {
            return wait.waitForElementsVisible(locator);
        } catch (Exception e) {
            throw new FrameworkException("Errore durante waitForElementsVisible su: " + locator, e);
        }
    }
    @Override
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

}
