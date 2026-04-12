package com.gianluca.framework.hooks;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.gianluca.framework.context.TestContext;
import com.gianluca.framework.driver.DriverFactory;
import com.gianluca.framework.reporting.ExtentManager;
import com.gianluca.framework.reporting.ExtentTestManager;
import com.gianluca.framework.reporting.ReportUtil;
import com.gianluca.framework.utils.ScreenshotUtil;
import io.cucumber.java.*;

public class Hooks {

    private final TestContext context;
    private static final ExtentReports extent = ExtentManager.getInstance();

    public Hooks(TestContext context) {
        this.context = context;
    }

    // ===================== BEFORE SCENARIO =====================

    @Before
    public void setUp(Scenario scenario) {

        context.setDriver(DriverFactory.getDriver());

        ExtentTest test = extent.createTest(scenario.getName());
        ExtentTestManager.setTest(test);
    }

    // ===================== BEFORE STEP =====================

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        ExtentTestManager.getTest().info("START STEP: " + scenario.getName());
    }

    // ===================== AFTER STEP =====================

    @AfterStep
    public void afterStep(Scenario scenario) {

        if (scenario.isFailed()) {
            ExtentTestManager.getTest().fail("STEP FAILED");
        } else {
            ExtentTestManager.getTest().pass("STEP PASSED");
        }
    }

    // ===================== AFTER SCENARIO =====================

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            ReportUtil.log("Scenario fallito - catturo screenshot");

            String screenshotPath = ScreenshotUtil.takeScreenshot(
                    context.getDriver(),
                    scenario.getName()
            );

            try {
                ExtentTestManager.getTest()
                        .fail("Scenario FAILED")
                        .addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                throw new RuntimeException("Errore allegando screenshot", e);
            }

        } else {
            ExtentTestManager.getTest().pass("Scenario PASSED");
        }

        if (context.getDriver() != null) {
            context.getDriver().quit();
        }

        extent.flush();
    }
}