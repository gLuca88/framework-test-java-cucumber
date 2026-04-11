package com.gianluca.framework.steps;

import com.gianluca.framework.context.TestContext;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class GenericsSteps {


    private final TestContext context;


    public GenericsSteps(TestContext context) {
        this.context = context;

    }
    @Given("l'utente apre il sito")
    public void opnSite(){
        context.getDriver().get("https://www.saucedemo.com/");
    }
}
