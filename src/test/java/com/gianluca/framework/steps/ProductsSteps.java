package com.gianluca.framework.steps;

import com.gianluca.framework.context.TestContext;
import com.gianluca.framework.pages.ProductsPage;
import com.gianluca.framework.reporting.ReportUtil;
import com.gianluca.framework.utils.LoggerUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductsSteps {

    private final TestContext context;
    private ProductsPage productsPage;

    public ProductsSteps(TestContext context) {
        this.context = context;
        this.productsPage = new ProductsPage(context.getDriver());
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
}
