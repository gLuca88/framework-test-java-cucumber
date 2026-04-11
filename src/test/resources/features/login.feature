  Feature: # TEST LOGIN
    Scenario: Accesso valido con utente registrato
      Given l'utente apre il sito
      When inserisce username "standard_user" e password "secret_sauce"
      And clicca sul bottone login
      Then viene reindirizzato alla pagina dei prodotti