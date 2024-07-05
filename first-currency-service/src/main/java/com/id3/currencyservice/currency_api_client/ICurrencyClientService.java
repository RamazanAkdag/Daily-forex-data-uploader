package com.id3.currencyservice.currency_api_client;

public interface ICurrencyClientService<T> {// T is api response object type

    public T getCurrencyInfo();
}
