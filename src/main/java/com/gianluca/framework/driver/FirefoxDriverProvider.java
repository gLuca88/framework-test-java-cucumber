package com.gianluca.framework.driver;

import com.gianluca.framework.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverProvider implements WebDriverProvider{
    @Override
    public WebDriver createDriver() {

        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();

        if (ConfigReader.getBoolean("headless")) {
            options.addArguments("--headless");
        }

        WebDriver driver = new FirefoxDriver(options);

        driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;
    }
}
