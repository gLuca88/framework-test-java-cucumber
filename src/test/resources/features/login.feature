  # ✔ LOGIN VALIDO
  Scenario: Accesso valido con utente registrato
  Given l'utente apre il sito
  When inserisce username "standard_user" e password "secret_sauce"
  And clicca sul bottone login
  Then viene reindirizzato alla pagina dei prodotti

  # ❌ LOGIN INVALID COMPLETO
  Scenario Outline: Login con credenziali non valide
  Given l'utente apre il sito
  When inserisce username "<username>" e password "<password>"
  And clicca sul bottone login
  Then visualizza il messaggio di errore "<errore>"

  Examples:
  | username        | password        | errore                                                                    |
  | locked_out_user | secret_sauce    | Epic sadface: Sorry, this user has been locked out.                       |
  | standard_user   | wrong_password  | Epic sadface: Username and password do not match any user in this service |
  | standard_user   | SECRET_SAUCE    | Epic sadface: Username and password do not match any user in this service |
  | standard_user   | Secret_sauce    | Epic sadface: Username and password do not match any user in this service |
  | standard_user   | secret_sauc     | Epic sadface: Username and password do not match any user in this service |
  | standard_user   | secret_saucee   | Epic sadface: Username and password do not match any user in this service |
  | standard_user   | sauc            | Epic sadface: Username and password do not match any user in this service |
  | standard_user   |  secret_sauce   | Epic sadface: Username and password do not match any user in this service |
  | wrong_user      | secret_sauce    | Epic sadface: Username and password do not match any user in this service |
  | wrong_user      | wrong_password  | Epic sadface: Username and password do not match any user in this service |
  | WRONG_USER      | secret_sauce    | Epic sadface: Username and password do not match any user in this service |
  | standard_user1  | secret_sauce    | Epic sadface: Username and password do not match any user in this service |
  | standard_user_  | secret_sauce    | Epic sadface: Username and password do not match any user in this service |
  | user            | pass            | Epic sadface: Username and password do not match any user in this service |
  | admin           | admin           | Epic sadface: Username and password do not match any user in this service |
  | test            | test123         | Epic sadface: Username and password do not match any user in this service |
  |                 | secret_sauce    | Epic sadface: Username is required                                        |
  | standard_user   |                 | Epic sadface: Password is required                                        |
  |                 |                 | Epic sadface: Username is required                                        |

  # 🔓 LOGOUT
  Scenario: Logout utente
  Given l'utente apre il sito
  When inserisce username "standard_user" e password "secret_sauce"
  And clicca sul bottone login
  And effettua il logout
  Then viene reindirizzato alla pagina di login