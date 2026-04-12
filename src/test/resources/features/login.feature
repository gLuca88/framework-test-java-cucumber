Feature: Autenticazione utente

  Background:
    Given l'utente apre il sito

  Scenario: Accesso valido con utente registrato
    When inserisce username "standard_user" e password "secret_sauce"
    And clicca sul bottone login
    Then viene reindirizzato alla pagina dei prodotti

  Scenario Outline: Login fallisce con credenziali non valide
    When inserisce username "<username>" e password "<password>"
    And clicca sul bottone login
    Then visualizza il messaggio di errore "<errore>"

    Examples:
      | username       | password       | errore                                                                    |
      | standard_user  | wrong_password | Epic sadface: Username and password do not match any user in this service |
      | wrong_user     | secret_sauce   | Epic sadface: Username and password do not match any user in this service |
      | WRONG_USER     | secret_sauce   | Epic sadface: Username and password do not match any user in this service |
      | standard_user1 | secret_sauce   | Epic sadface: Username and password do not match any user in this service |
      | standar_user   | secret_sauce   | Epic sadface: Username and password do not match any user in this service |
      | standard_user  | secret_sauc    | Epic sadface: Username and password do not match any user in this service |

  Scenario: Login con utente bloccato
    When inserisce username "locked_out_user" e password "secret_sauce"
    And clicca sul bottone login
    Then visualizza il messaggio di errore "Epic sadface: Sorry, this user has been locked out."

  Scenario Outline: Validazione campi obbligatori login
    When inserisce username "<username>" e password "<password>"
    And clicca sul bottone login
    Then visualizza il messaggio di errore "<errore>"

    Examples:
      | username      | password     | errore                             |
      |               | secret_sauce | Epic sadface: Username is required |
      | standard_user |              | Epic sadface: Password is required |
      |               |              | Epic sadface: Username is required |

  Scenario: Logout utente dopo accesso
    Given l'utente apre il sito
    When inserisce username "standard_user" e password "secret_sauce"
    And clicca sul bottone login
    Then viene reindirizzato alla pagina dei prodotti
    When effettua il logout
    Then viene reindirizzato alla pagina di login