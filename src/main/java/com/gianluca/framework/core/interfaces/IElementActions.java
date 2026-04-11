package com.gianluca.framework.core.interfaces;

import org.openqa.selenium.By;

public interface IElementActions {
    void click(By locator);

    void type(By locator, String text);

    String getText(By locator);

    boolean isDisplayed(By locator);
}
