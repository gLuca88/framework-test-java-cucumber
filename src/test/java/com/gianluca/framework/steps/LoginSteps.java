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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericsStepsLogin {

    private final TestContext context;

    public GenericsStepsLogin(TestContext context) {
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

    @Then("visualizza il messaggio di errore {string}")
    public void verifyErrorMessage(String expectedError) {

        ReportUtil.log("Verifico messaggio di errore atteso");
        LoggerUtil.info("Inizio verifica messaggio di errore");

        LoginPage login = new LoginPage(context.getDriver());
        String actualError = login.getMexError();

        LoggerUtil.info("EXPECTED: " + expectedError);
        LoggerUtil.info("ACTUAL: " + actualError);

        ReportUtil.log("Atteso: " + expectedError + " - Ottenuto: " + actualError);

        assertEquals(actualError, expectedError,
                "Errore: il messaggio visualizzato non è corretto");

        LoggerUtil.info("Verifica messaggio errore completata con successo");
    }

    @When("effettua il logout")
    public void performLogout() throws InterruptedException {

        ReportUtil.log("Eseguo logout utente");
        LoggerUtil.info("Click su menu hamburger e selezione logout");

        ProductsPage productsPage = new ProductsPage(context.getDriver());
        productsPage.executeLogout();

        LoggerUtil.info("Logout eseguito");
    }

    @Then("viene reindirizzato alla pagina di login")
    public void verifyLoginPageIsLoaded() {

        ReportUtil.log("Verifico che la pagina di login sia visibile");
        LoggerUtil.info("Inizio verifica pagina login");

        LoginPage loginPage = new LoginPage(context.getDriver());
        boolean actualResult = loginPage.isPageLoginLoaded();

        LoggerUtil.info("EXPECTED: true");
        LoggerUtil.info("ACTUAL: " + actualResult);

        ReportUtil.log("Atteso: true - Ottenuto: " + actualResult);

        assertTrue(actualResult, "Errore: la pagina di login non è visibile dopo il logout");

        LoggerUtil.info("Verifica pagina login completata con successo");
    }
}
