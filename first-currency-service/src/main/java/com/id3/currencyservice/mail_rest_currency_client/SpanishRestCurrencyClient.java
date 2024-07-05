package com.id3.currencyservice.mail_rest_currency_client;

import com.id3.currencyservice.mail_rest_currency_client.model.EnglishCurrency;
import com.id3.currencyservice.mail_rest_currency_client.model.IDto;
import com.id3.currencyservice.mail_rest_currency_client.model.SpanishCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpanishRestCurrencyClient implements IRestCurrencyClient<SpanishCurrency>{
    private final String url = "http://localhost:8082/api/esp";
    private final HttpClient client;


    @Override
    public void sendCurrencyInfo(List<SpanishCurrency> list) {

    }
}
