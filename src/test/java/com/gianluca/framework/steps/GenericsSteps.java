package com.gianluca.framework.steps;

import com.gianluca.framework.context.TestContext;
import com.gianluca.framework.pages.LoginPage;
import com.gianluca.framework.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericsSteps {


    private final TestContext context;


    public GenericsSteps(TestContext context) {
        this.context = context;
    }
    @Given("l'utente apre il sito")
    public void openSite(){
        context.getDriver().get("https://www.saucedemo.com/");
    }

    @When("inserisce username {string} e password {string}")
    public void enterCredentials(String userName, String password){
         LoginPage login=new LoginPage(context.getDriver());
         login.login(userName,password);
    }

    @And("clicca sul bottone login")
    public void clickLogin(){
        LoginPage login=new LoginPage(context.getDriver());
        login.clickLogin();
    }
    @Then("viene reindirizzato alla pagina dei prodotti")
    public void verifyPageProductsIsLoaded(){
        ProductsPage productsPage = new ProductsPage(context.getDriver());
        assertTrue(productsPage.isPageProductsLoaded(),"Login fallito: pagina prodotti non caricata correttamente");
    }
}
