package com.gianluca.framework.hooks;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.gianluca.framework.context.TestContext;
import com.gianluca.framework.core.expections.FrameworkException;
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

        try {
            context.setDriver(DriverFactory.getDriver());

            ExtentTest test = extent.createTest(scenario.getName());
            ExtentTestManager.setTest(test);

        } catch (Exception e) {
            throw new FrameworkException("Errore nel setup dello scenario", e);
        }
    }

    // ===================== BEFORE STEP =====================

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        ExtentTestManager.getTest().info("START STEP: " + scenario.getName());
    }

    // ===================== AFTER STEP =====================

    @AfterStep
    public void afterStep(Scenario scenario) {

        try {
            if (scenario.isFailed()) {
                ExtentTestManager.getTest().fail("STEP FAILED");
            } else {
                ExtentTestManager.getTest().pass("STEP PASSED");
            }
        } catch (Exception e) {
            ReportUtil.log("Errore nel logging dello step: " + e.getMessage());
        }
    }

    // ===================== AFTER SCENARIO =====================

    @After
    public void tearDown(Scenario scenario) {

        try {

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
                    ReportUtil.log("Errore allegando screenshot: " + e.getMessage());
                }

            } else {
                ExtentTestManager.getTest().pass("Scenario PASSED");
            }

        } catch (Exception e) {
            ReportUtil.log("Errore generale nel teardown: " + e.getMessage());
        } finally {

            try {
                if (context.getDriver() != null) {
                    context.getDriver().quit();
                }
            } catch (Exception e) {
                ReportUtil.log("Errore chiusura driver: " + e.getMessage());
            }

            extent.flush();
        }
    }
}