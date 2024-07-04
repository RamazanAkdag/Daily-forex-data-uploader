package com.id3.currencyservice.api_client.tcmb;


import com.id3.currencyservice.api_client.ICurrencyClientService;

import com.id3.currencyservice.csv.IObjectToCsvService;
import com.id3.currencyservice.model.Doviz;
import com.id3.currencyservice.model.DovizListesi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TcmbCurrencyClientManager implements ITcmbCurrencyClientService {

    private final HttpClient client;
    private final Unmarshaller dovizListesiUnmarshaller;
    private final IObjectToCsvService<Doviz> objectToCsvService;

    @Value("${tcmb.url}")
    private String url;
    @Override
    public List<Doviz> getCurrencyInfo() {
        DovizListesi liste = null;
        try {
            log.info("url : "+url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            StringReader reader = new StringReader(response.body());

            liste = (DovizListesi) dovizListesiUnmarshaller.unmarshal(reader);

            objectToCsvService.writeToCsv(liste.getDovizler());

        }catch (Exception e){
            e.printStackTrace();
        }


        return liste.getDovizler();
    }
}
