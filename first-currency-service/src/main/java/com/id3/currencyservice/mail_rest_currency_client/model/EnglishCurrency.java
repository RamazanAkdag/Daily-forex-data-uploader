package com.id3.currencyservice.mail_rest_currency_client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnglishCurrency implements IDto {
    private int unit;
    private String name;
    private String currencyName;
    private double currencyBuying;
    private double currencySelling;
    private double effectiveBuying;
    private double effectiveSelling;
    private String dollarRate;
    private String otherRate;
    private String code;
    private String currencyCode;

}
