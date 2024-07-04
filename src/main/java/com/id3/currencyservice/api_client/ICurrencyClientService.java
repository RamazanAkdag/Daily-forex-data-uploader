package com.id3.currencyservice.api_client;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ICurrencyClientService<T> {

    public T getCurrencyInfo();
}
