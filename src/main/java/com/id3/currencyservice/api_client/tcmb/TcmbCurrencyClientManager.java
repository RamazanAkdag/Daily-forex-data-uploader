package com.id3.currencyservice.api_client.tcmb;

import com.id3.currencyservice.CurrencyServiceApplication;
import com.id3.currencyservice.api_client.ICurrencyClientService;
import com.id3.currencyservice.model.Currency;
import com.id3.currencyservice.model.Tarih_Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class TcmbCurrencyClientManager implements ICurrencyClientService<Tarih_Date> {

    private final HttpClient client;
   private final Unmarshaller tarihDateUnmarshaller;

    @Value("${tcmb.url}")
    private String url;
    @Override
    public Tarih_Date getCurrencyInfo() {
        Tarih_Date tarihDate = null;
        try {
            log.info("url : "+url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            StringReader reader = new StringReader(response.body());

            tarihDate = (Tarih_Date) tarihDateUnmarshaller.unmarshal(reader);



        }catch (Exception e){
            e.printStackTrace();
        }
        return tarihDate;
    }
}
