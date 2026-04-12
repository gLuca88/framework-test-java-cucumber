package com.gianluca.framework.utils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String scenarioName) {

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;

            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            String fileName = scenarioName.replaceAll(" ", "_") + "_" + System.currentTimeMillis() + ".png";

            Path path = Paths.get("reports/screenshots", fileName);

            Files.createDirectories(path.getParent());
            Files.write(path, screenshot);

            return "screenshots/" + fileName; // 🔥 FIX

        } catch (Exception e) {
            throw new RuntimeException("Errore durante lo screenshot", e);
        }
    }
}
