package com.gianluca.framework.driver;

import com.gianluca.framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final Map<String,WebDriverProvider> providers=new HashMap<>();

    static{
        providers.put("chrome",new ChromeDriverProvider());
        providers.put("firefox", new FirefoxDriverProvider());
        providers.put("edge",new EdgeDriverProvider());
    }



    public static WebDriver getDriver(){

        String browser= ConfigReader.getProperty("browser").toLowerCase();

        WebDriverProvider provider=providers.get(browser);

        if(provider==null){
            throw new RuntimeException("browser non supportato:"+browser);
        }

        return  provider.createDriver();

    }




}
