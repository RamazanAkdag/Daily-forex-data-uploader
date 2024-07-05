package com.id3.currencyservice.mapping;

import com.id3.currencyservice.mail_rest_currency_client.model.EnglishCurrency;
import com.id3.currencyservice.model.Doviz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DovizToEnglishMapper implements IDovizMapper<EnglishCurrency>{
    @Override
    public List<EnglishCurrency> map(List<Doviz> dovizList) {
        List<EnglishCurrency> currencies = dovizList.stream()
                .map(doviz -> EnglishCurrency.builder()
                        .unit(doviz.getBirim())
                        .name(doviz.getIsim())
                        .currencyName(doviz.getDovizAdi())
                        .currencyBuying(doviz.getDovizAlis())
                        .currencySelling(doviz.getDovizSatis())
                        .effectiveBuying(doviz.getEfektifAlis())
                        .effectiveSelling(doviz.getEfektifSatis())
                        .dollarRate(doviz.getDolarKuru())
                        .otherRate(doviz.getDigerKur())
                        .code(doviz.getKod())
                        .currencyCode(doviz.getDovizKodu())
                        .build())
                .collect(Collectors.toList());

        return currencies;
    }
}
