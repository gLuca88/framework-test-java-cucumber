package com.gianluca.framework.steps;

import com.gianluca.framework.context.TestContext;
import com.gianluca.framework.pages.ProductDetailPage;
import com.gianluca.framework.pages.ProductsPage;
import com.gianluca.framework.reporting.ReportUtil;
import com.gianluca.framework.utils.LoggerUtil;
import com.gianluca.framework.utils.SortingUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Then("il badge del carrello non è visibile")
    public void verifiyBadgeIsVisible() {

        boolean actual = productsPage.isBadgeIsVisible();
        boolean expected = false;

        LoggerUtil.info("EXPECTED: " + expected);
        LoggerUtil.info("ACTUAL: " + actual);

        ReportUtil.log("Verifica badge non visibile - Atteso: "
                + expected + " - Ottenuto: " + actual);

        assertFalse(actual,
                "Errore: il badge del carrello è visibile quando non dovrebbe");

        LoggerUtil.info("Verifica badge non visibile completata con successo");
    }

    @When("l'utente aggiunge un prodotto al carrello")
    public void addProductsToCart() {

        ReportUtil.log("Aggiunta prodotto al carrello");
        LoggerUtil.info("Click su add to cart per 1 prodotto");

        productsPage.clickAddProduct(1);

        LoggerUtil.info("Prodotto aggiunto al carrello");
    }

    @Then("il badge del carrello mostra 1")
    public void verifyBadgeCart() {

        int actual = productsPage.getTextBadgeCart();
        int expected = 1;

        LoggerUtil.info("EXPECTED: " + expected);
        LoggerUtil.info("ACTUAL: " + actual);

        ReportUtil.log("Verifica badge carrello - Atteso: "
                + expected + " - Ottenuto: " + actual);

        assertEquals(expected, actual,
                "Errore: numero badge carrello non corretto");

        LoggerUtil.info("Verifica badge completata con successo");
    }

    @And("il pulsante cambia in {string}")
    public void verifyTextButtonAddToCart(String textButton) {

        List<String> extractedText = productsPage.getTextButtonsAddToCart(1);
        String actualText = extractedText.get(0);

        LoggerUtil.info("EXPECTED: " + textButton);
        LoggerUtil.info("ACTUAL: " + actualText);

        ReportUtil.log("Verifica testo bottone - Atteso: "
                + textButton + " - Ottenuto: " + actualText);

        assertEquals(textButton, actualText,
                "Errore: testo bottone non corretto");

        LoggerUtil.info("Verifica testo bottone completata con successo");
    }

    @When("l'utente seleziona {string}")
    public void userSelectsSortingOption(String sortingOption) {

        ReportUtil.log("Selezione ordinamento prodotti: " + sortingOption);
        LoggerUtil.info("Selezione ordinamento: " + sortingOption);

        productsPage.selectSortingOption(sortingOption);

        LoggerUtil.info("Ordinamento selezionato correttamente");
    }

    @Then("i prodotti sono ordinati {string}")
    public void verifyProductsSorting(String sortingType) {

        ReportUtil.log("Verifica ordinamento prodotti: " + sortingType);
        LoggerUtil.info("Verifica ordinamento: " + sortingType);

        boolean actualResult = false;

        switch (sortingType) {

            case "Name (A to Z)":

                actualResult =
                        SortingUtils.isAlphabeticallyAscending(
                                productsPage.getAllProductNames());

                break;

            case "Name (Z to A)":

                actualResult =
                        SortingUtils.isAlphabeticallyDescending(
                                productsPage.getAllProductNames());

                break;

            case "Price (low to high)":

                actualResult =
                        SortingUtils.isPriceAscending(
                                productsPage.getAllProductPrices());

                break;

            case "Price (high to low)":

                actualResult =
                        SortingUtils.isPriceDescending(
                                productsPage.getAllProductPrices());

                break;

            default:

                throw new IllegalArgumentException(
                        "Tipo ordinamento non supportato: "
                                + sortingType);
        }

        LoggerUtil.info("ACTUAL: " + actualResult);

        assertTrue(actualResult,
                "Errore ordinamento prodotti: " + sortingType);

        LoggerUtil.info("Verifica ordinamento completata con successo");
    }

}
