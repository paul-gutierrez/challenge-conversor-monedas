# Currency Converter

This is a desktop application built in Java that allows you to convert between different currencies using exchange rates obtained from the [ExchangeRate-API](https://www.exchangerate-api.com/). The program features a simple graphical user interface that lets users select a base currency, a target currency, input an amount, and perform the conversion.

## Features

- Select base currency and target currency from dropdown menus.
- Input the amount you want to convert.
- Perform real-time conversion using current exchange rates from the API.
- The currencies in the dropdown menus are listed alphabetically.
- The dropdown menus support autocomplete while typing.

## Requirements

To run this program, you'll need:

- JDK 8 or higher.
- SwingX library for the autocomplete feature.
- Gson library for parsing JSON data from the API.
- A valid API key from ExchangeRate-API. You can get one by registering at [https://www.exchangerate-api.com/](https://www.exchangerate-api.com/).

## Setup

### API Key

In order for the program to function properly, you need to replace the API key in the `BuscarMoneda.java` file. Look for the following line (line 15):

```java
private String apiKey = "YOUR-API-KEY";
