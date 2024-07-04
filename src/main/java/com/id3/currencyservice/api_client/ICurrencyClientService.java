package com.id3.currencyservice.api_client;

public interface ICurrencyClientService<T> {// T is api response object type

    public T getCurrencyInfo();
}
