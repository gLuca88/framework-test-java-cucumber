package com.gianluca.framework.hooks;

import com.gianluca.framework.context.TestContext;
import com.gianluca.framework.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        context.setDriver(DriverFactory.getDriver());
    }

    @After
    public void tearDown() {
        if (context.getDriver() != null) {
            context.getDriver().quit();
        }
    }
}
