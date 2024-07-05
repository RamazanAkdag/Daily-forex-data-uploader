package com.id3.currencyservice.mapping;

import com.id3.currencyservice.mail_rest_currency_client.model.IDto;
import com.id3.currencyservice.model.Doviz;

import java.util.List;

public interface IDovizMapper<T extends IDto> {

    public List<T> map(List<Doviz> dovizList);
}
