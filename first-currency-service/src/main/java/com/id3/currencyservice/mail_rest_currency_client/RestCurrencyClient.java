package com.id3.currencyservice.mail_rest_currency_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestCurrencyClient<T> {
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public void sendCurrencyInfo(List<T> list, String url) {
        try {
            // JSON formatına dönüştür
            String requestBody = objectMapper.writeValueAsString(list);

            // POST isteği oluştur
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // İsteği gönder ve yanıtı al
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Yanıtı kontrol et
            if (response.statusCode() == 200) {
                System.out.println("Successfully sent currency info.");
            } else {
                System.err.println("Failed to send currency info. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
