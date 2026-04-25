@products
Feature: Gestione pagina prodotti - SauceDemo

  Background:
    Given l'utente apre il sito
    When inserisce username "standard_user" e password "secret_sauce"
    And clicca sul bottone login
    Then viene reindirizzato alla pagina dei prodotti
  @smoke @ui
  Scenario: Visualizzazione lista prodotti
    Then la lista dei prodotti è visibile
    And ogni prodotto mostra nome, descrizione, prezzo e pulsante di azione