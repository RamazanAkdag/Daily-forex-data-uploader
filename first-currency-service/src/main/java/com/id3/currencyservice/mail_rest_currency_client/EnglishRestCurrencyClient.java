package com.id3.currencyservice.mail_rest_currency_client;

import com.id3.currencyservice.mail_rest_currency_client.model.EnglishCurrency;
import com.id3.currencyservice.mail_rest_currency_client.model.IDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnglishRestCurrencyClient implements IRestCurrencyClient<EnglishCurrency>{
    private final String url = "http://localhost:8080/api/en";
    private final HttpClient client;

    @Override
    public void sendCurrencyInfo(List<EnglishCurrency> list) {

    }
}
