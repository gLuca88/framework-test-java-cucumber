package com.gianluca.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By usernameField= By.id("user-name");
    private By passwordField=By.id("password");
    private By loginButton=By.id("login-button");
    private By errorMessageLogin=By.cssSelector("h3[data-test='error']");

    public void insertUserName(String text){
        actions.type(usernameField,text);
    }

    public void insertPassword(String text){
        actions.type(passwordField,text);
    }

    public void clickLogin(){
        actions.click(loginButton);
    }

    public void login(String user,String passw){
        insertUserName(user);
        insertPassword(passw);
    }

    public String getMexError(){
        return  actions.getText(errorMessageLogin);
    }

}
