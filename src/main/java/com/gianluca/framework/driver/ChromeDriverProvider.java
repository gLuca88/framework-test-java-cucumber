package com.gianluca.framework.driver;

import com.gianluca.framework.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverProvider implements WebDriverProvider {


    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (ConfigReader.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;
    }
}
