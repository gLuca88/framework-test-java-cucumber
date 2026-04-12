package com.gianluca.framework.reporting;

public class ReportUtil {

    public static void log(String message){
        ExtentTestManager.getTest().info(message);
    }
}
