package com.id3.currencyservice.mail_rest_currency_client;

import com.id3.currencyservice.mail_rest_currency_client.model.IDto;

import java.util.List;

public interface IRestCurrencyClient<T extends IDto> {

    public void sendCurrencyInfo(List<T> list);

}
