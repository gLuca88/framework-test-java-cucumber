package com.gianluca.framework.driver;

import com.gianluca.framework.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverProvider implements WebDriverProvider {
    @Override
    public WebDriver createDriver() {

        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();

        if (ConfigReader.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }

        WebDriver driver = new EdgeDriver(options);

        driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;
    }
}
