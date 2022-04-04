# Qs3-RestServer 🗄️
![Java CI with Maven](https://github.com/IoT-Students/QS3-RestServer/actions/workflows/maven.yml/badge.svg)

**generelt:**

- Dette repoet representerer backend for Qs3 klientsiden.
- Er bygget opp med REST arkitektur
- Har kontakt med mySQL database 

## Eksterne avhengigheter

### Maven
- Maven er brukt som rammeverk i dette prosjektet.

### Spring-Boot
- I server-prosjektet er Spring-Boot blitt benyttet. 

### JUnit
- JUnit er blitt brukt for å teste kritisk funksjonalitet.


## Installasjon/Instruksjon 🗃️

Les gjennom alle stegene før bruk!

### Start Spring-boot server:

- Naviger til mappen der filene ble lagret etter nedlastingen.
- Kjør ekstern spring-boot server ved å kjøre kommando:
```
java -jar Qs3-RestServer.jar
```
- Eventuelt kan man navigere til selve prosjekt-mappen og kjøre kommandoen:

```bash
mvn spring-boot:run 
```

- Nå venter server på forespørsler fra klientside

### Kjør tester:
For å kjøre tester kan denne kommandoen brukes:
```
mvn clean test
```

## Dokumentasjon
Alle klasser i REST server er dokumenter med JavaDoc. Du finner lenke her: [JavaDoc](https://magnus-farstad.github.io/Qs3-Docs/)

I tillegg er endepunktene dokumentert med Swagger. Du finner lenke her: [Swagger](http://localhost:8085/swagger-ui.html) NB! Kjør server først!
