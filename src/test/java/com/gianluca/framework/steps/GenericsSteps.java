package com.gianluca.framework.steps;

import com.gianluca.framework.context.TestContext;
import com.gianluca.framework.pages.LoginPage;
import com.gianluca.framework.pages.ProductsPage;
import com.gianluca.framework.reporting.ReportUtil;
import com.gianluca.framework.utils.LoggerUtil;
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
    public void openSite() {

        ReportUtil.log("Apro il sito SauceDemo");
        LoggerUtil.info("Navigazione verso SauceDemo");

        context.getDriver().get("https://www.saucedemo.com/");
    }

    @When("inserisce username {string} e password {string}")
    public void enterCredentials(String userName, String password) {

        ReportUtil.log("Inserisco username: " + userName + " e password");
        LoggerUtil.info("Inserimento credenziali utente: " + userName);

        LoginPage login = new LoginPage(context.getDriver());
        login.login(userName, password);
    }

    @And("clicca sul bottone login")
    public void clickLogin() {

        ReportUtil.log("Clicco sul bottone login");
        LoggerUtil.info("Click sul bottone login");

        LoginPage login = new LoginPage(context.getDriver());
        login.clickLogin();
    }

    @Then("viene reindirizzato alla pagina dei prodotti")
    public void verifyPageProductsIsLoaded() {

        ReportUtil.log("Verifico che la pagina prodotti sia caricata");
        LoggerUtil.info("Inizio verifica pagina prodotti");
        ProductsPage productsPage = new ProductsPage(context.getDriver());
        boolean actualResult = productsPage.isPageProductsLoaded();
        boolean expectedResult = true;
        LoggerUtil.info("EXPECTED: " + expectedResult);
        LoggerUtil.info("ACTUAL: " + actualResult);
        ReportUtil.log("Atteso: " + expectedResult + " - Ottenuto: " + actualResult);
        assertTrue(actualResult, "Login fallito: pagina prodotti non caricata correttamente");
        LoggerUtil.info("Verifica completata con successo");
    }
}