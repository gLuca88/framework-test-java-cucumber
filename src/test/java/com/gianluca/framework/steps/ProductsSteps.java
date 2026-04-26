package com.gianluca.framework.steps;

import com.gianluca.framework.context.TestContext;
import com.gianluca.framework.pages.ProductDetailPage;
import com.gianluca.framework.pages.ProductsPage;
import com.gianluca.framework.reporting.ReportUtil;
import com.gianluca.framework.utils.LoggerUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductsSteps {

    private final TestContext context;
    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;
    private String expectedProductName;

    public ProductsSteps(TestContext context) {
        this.context = context;
        this.productsPage = new ProductsPage(context.getDriver());
        this.productDetailPage = new ProductDetailPage(context.getDriver());
    }

    @Then("la lista dei prodotti è visibile")
    public void verifyProducts() {

        ReportUtil.log("Verifico che la lista dei prodotti sia visibile");
        LoggerUtil.info("Inizio verifica lista prodotti");

        boolean actualResult = productsPage.areProductsLoaded(6);
        boolean expectedResult = true;

        LoggerUtil.info("EXPECTED: " + expectedResult);
        LoggerUtil.info("ACTUAL: " + actualResult);

        ReportUtil.log("Atteso: " + expectedResult + " - Ottenuto: " + actualResult);

        assertTrue(actualResult,
                "Errore: la lista prodotti non è visibile o incompleta");

        LoggerUtil.info("Verifica lista prodotti completata con successo");
    }

    @And("ogni prodotto mostra nome, descrizione, prezzo e pulsante di azione")
    public void verifyProductsHaveDetails() {

        ReportUtil.log("Verifico che ogni prodotto abbia nome, descrizione e prezzo");
        LoggerUtil.info("Inizio verifica dettagli prodotti");

        boolean actualResult = productsPage.areAllProductsComplete();
        boolean expectedResult = true;

        LoggerUtil.info("EXPECTED: " + expectedResult);
        LoggerUtil.info("ACTUAL: " + actualResult);

        ReportUtil.log("Atteso: " + expectedResult + " - Ottenuto: " + actualResult);

        assertTrue(actualResult,
                "Errore: uno o più prodotti non hanno tutti i campi");

        LoggerUtil.info("Verifica dettagli prodotti completata con successo");
    }

    @When("l'utente clicca sul prodotto {string}")
    public void clickNameProduct(String productName) {

        expectedProductName = productName;

        ReportUtil.log("Click sul prodotto: " + productName);
        LoggerUtil.info("Click prodotto: " + productName);

        productsPage.clickNameProduct(productName);
    }

    @Then("viene aperta la pagina di dettaglio prodotto")
    public void verifyProductDetailPage() {

        boolean actualResult = productDetailPage.isLoaded();
        boolean expectedResult = true;

        LoggerUtil.info("EXPECTED: " + expectedResult);
        LoggerUtil.info("ACTUAL: " + actualResult);

        ReportUtil.log("Verifica apertura pagina dettaglio prodotto");

        assertTrue(actualResult,
                "Errore: pagina dettaglio prodotto non caricata correttamente");
    }

    @And("il nome del prodotto è corretto")
    public void verifyNameProduct() {

        String actualName = productDetailPage.getProductNameDetailPage();

        LoggerUtil.info("EXPECTED: " + expectedProductName);
        LoggerUtil.info("ACTUAL: " + actualName);

        ReportUtil.log("Verifica nome prodotto - Atteso: "
                + expectedProductName + " - Ottenuto: " + actualName);

        assertEquals(expectedProductName, actualName,
                "Errore: il nome prodotto non corrisponde");
    }
}
