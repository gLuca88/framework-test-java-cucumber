package com.gianluca.framework.pages;

import com.gianluca.framework.core.impl.ElementActions;
import com.gianluca.framework.core.interfaces.IElementActions;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected IElementActions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ElementActions(driver);
    }

    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
    protected String getTitlePageHtml(){
        return driver.getTitle();
    }
}
