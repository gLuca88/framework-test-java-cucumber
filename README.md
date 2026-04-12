# 🚀 Test Automation Framework (Java + Selenium + Cucumber)

Framework di test automation sviluppato in Java, progettato seguendo principi di **modularità, riusabilità e manutenibilità**,

## 📌 Obiettivi del progetto

- Creare un framework scalabile e professionale
- Separare chiaramente i layer (test, logica, configurazione)
- Implementare:
  - ✔ Reporting HTML
  - ✔ Logging tecnico
  - ✔ Screenshot automatici
- Applicare design pattern reali utilizzati nei team QA

---

## 🏗️ Architettura del progetto

### 📦 Main (Framework Core)

- **config** → gestione configurazioni
- **driver** → gestione browser (DriverFactory, Provider)
- **core**
  - **interfaces** → contratti (Wait, Actions)
  - **implementations** → implementazioni concrete
- **pages** → Page Object Model

---

### 🧪 Test Layer

- **context** → gestione stato test (TestContext)
- **hooks** → lifecycle Cucumber (Before/After)
- **reporting** → ExtentReports
- **steps** → Step Definitions
- **utils**
  - ScreenshotUtil
  - LoggerUtil

---

### 📂 Resources

- **features** → scenari Cucumber
- **config.properties** → configurazione test
- **log4j2.xml** → configurazione logging

## ⚙️ Tecnologie utilizzate

| Tecnologia        | Scopo |
|------------------|------|
| Java             | Linguaggio principale |
| Selenium         | Automazione browser |
| Cucumber         | BDD (Behavior Driven Development) |
| JUnit 5          | Test runner |
| Maven            | Build e dependency management |
| ExtentReports    | Reporting HTML |
| Log4j2           | Logging tecnico |
| WebDriverManager | Gestione driver |

## 🧩 Design Pattern utilizzati

### ✔ Page Object Model (POM)
- Separazione tra test e logica UI
- Riutilizzo delle pagine

### ✔ Factory Pattern
- Creazione driver tramite `DriverFactory

### ✔ Strategy Pattern
- Gestione delle attese (`IWaitStrategy`)

### ✔ Singleton
- Gestione report (`ExtentManager`)

## 🧪 Gestione Test

- Feature file in stile **BDD**
- Step definitions separate
- Uso di `TestContext` per condividere il driver

## 📊 Reporting
Il framework utilizza **ExtentReports** per generare report HTML 

## 📸 Screenshot automatici
- Catturati automaticamente in caso di failure
- Allegati direttamente nel report HTML

### Livelli di log:

- INFO → flusso test
- ERROR → errori
- WARN → warning

---

## 🌐 Gestione Browser

Supporto multi-browser:

- Chrome
- Edge
- Firefox

### Configurazioni:

- Modalità headless
- Dimensione finestra fissa (test stabili)

### ⚙️ Configurazione (ConfigReader)

Il framework utilizza una classe ConfigReader per caricare dinamicamente i parametri di configurazione dai file .properties.

📂 File di configurazione

I file devono essere posizionati in:

src/main/resources/

Esempio:

config-dev.properties
config-qa.properties


Se non viene specificato il parametro env, il framework utilizza automaticamente:

config-dev.properties

👉 Questo comportamento è gestito internamente dal ConfigReader.

▶️ Esecuzione test da terminale

Il framework permette di eseguire i test tramite Maven, scegliendo ambiente e scenari.

🔹 Eseguire tutti i test
mvn test


🔹 Eseguire i test in ambiente QA
mvn test -Denv=qa


🔹 Esecuzione combinata
mvn test "-Denv=qa" "-Dcucumber.filter.tags=@login"
