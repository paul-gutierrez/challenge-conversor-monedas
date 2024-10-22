package models;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class BuscarMoneda {

    private String apiKey = "YOUR-API-KEY";

    // Metodo que obtiene las tasas de conversion y nombres de monedas
    public Map<String, Double> obtenerConversiones(String monedaBase) {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaBase;

        try {
            // Crear la solicitud HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar si la respuesta es exitosa (código 200)
            if (response.statusCode() != 200) {
                System.out.println("Error: No se pudo conectar con la API. Código de estado: " + response.statusCode());
                return null;
            }

            // Parsear el JSON
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            // Convertir las tasas de conversión a un Map
            Map<String, Double> tasasConversion = new HashMap<>();
            for (String moneda : conversionRates.keySet()) {
                tasasConversion.put(moneda, conversionRates.get(moneda).getAsDouble());
            }

            return tasasConversion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metodo que obtiene la tasa especifica entre dos monedas
    public double obtenerTasaConversion(String monedaBase, String monedaDestino) {
        Map<String, Double> tasasConversion = obtenerConversiones(monedaBase);
        if (tasasConversion != null && tasasConversion.containsKey(monedaDestino)) {
            return tasasConversion.get(monedaDestino);
        } else {
            System.out.println("Error: No se pudo obtener la tasa de conversión.");
            return -1;
        }
    }
}


