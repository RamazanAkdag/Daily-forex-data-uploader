package com.id3.currencyservice.mail_rest_currency_client;


import com.id3.currencyservice.mail_rest_currency_client.model.EnglishCurrency;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EnglishRestCurrencyClient implements IRestCurrencyClient<EnglishCurrency> {
    private final String url = "http://localhost:8080/api/en";
    private final RestCurrencyClient<EnglishCurrency> restCurrencyClient;

    @Override
    public void sendCurrencyInfo(List<EnglishCurrency> list) {
        restCurrencyClient.sendCurrencyInfo(list, url);
    }
}