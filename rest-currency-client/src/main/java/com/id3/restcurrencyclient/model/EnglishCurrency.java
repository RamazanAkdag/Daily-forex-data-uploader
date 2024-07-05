package com.id3.restcurrencyclient.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnglishCurrency implements IDto {
    private String unit;
    private String name;
    private String currencyName;
    private double currencyBuying;
    private double currencySelling;
    private double effectiveBuying;
    private double effectiveSelling;
    private double dollarRate;
    private double otherRate;
    private String code;
    private String currencyCode;

}
