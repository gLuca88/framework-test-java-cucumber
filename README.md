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
src
├── main
│ └── java
│ └── com.gianluca.framework
│ ├── config
│ ├── driver
│ ├── core
│ │ ├── interfaces
│ │ └── implementations
│ └── pages
│
├── test
│ ├── java
│ │ └── com.gianluca.framework
│ │ ├── context
│ │ ├── hooks
│ │ ├── reporting
│ │ ├── steps
│ │ └── utils
│ │
│ └── resources
│ ├── features
│ ├── config.properties
│ └── log4j2.xml

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