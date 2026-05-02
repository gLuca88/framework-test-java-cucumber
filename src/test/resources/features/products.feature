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

  @ui @functional
  Scenario: L'utente apre il dettaglio cliccando sul nome del prodotto
    When l'utente clicca sul prodotto "Sauce Labs Backpack"
    Then viene aperta la pagina di dettaglio prodotto
    And il nome del prodotto è corretto

  @cart @functional
  Scenario: Il carrello è vuoto al primo accesso
    Then il badge del carrello non è visibile

  @cart @functional
  Scenario: Aggiunta prodotto aggiorna badge e stato pulsante
    When l'utente aggiunge un prodotto al carrello
    Then il badge del carrello mostra 1
    And il pulsante cambia in "Remove"