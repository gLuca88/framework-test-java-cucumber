package com.gianluca.framework.steps;

import com.gianluca.framework.context.TestContext;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class GenericsSteps {


    private final TestContext context;
    private final WebDriver driver;

    public GenericsSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }
    @Given("l'utente apre il sito")
    public void opnSite(){
        driver.get("https://www.saucedemo.com/");
    }
}
