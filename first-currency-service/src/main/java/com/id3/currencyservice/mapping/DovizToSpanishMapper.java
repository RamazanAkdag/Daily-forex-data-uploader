package com.id3.currencyservice.mapping;

import com.id3.currencyservice.mail_rest_currency_client.model.SpanishCurrency;
import com.id3.currencyservice.model.Doviz;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DovizToSpanishMapper implements IDovizMapper<SpanishCurrency> {
    @Override
    public List<SpanishCurrency> map(List<Doviz> dovizList) {
        return dovizList.stream()
                .map(doviz -> SpanishCurrency.builder()
                        .unidad(doviz.getBirim())
                        .nombre(doviz.getIsim())
                        .nombreDivisa(doviz.getDovizAdi())
                        .compraDivisa(doviz.getDovizAlis())
                        .ventaDivisa(doviz.getDovizSatis())
                        .compraEfectiva(doviz.getEfektifAlis())
                        .ventaEfectiva(doviz.getEfektifSatis())
                        .tasaDolar(doviz.getDolarKuru())
                        .otraTasa(doviz.getDigerKur())
                        .codigo(doviz.getKod())
                        .codigoDivisa(doviz.getDovizKodu())
                        .build())
                .collect(Collectors.toList());
    }
}
