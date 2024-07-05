package com.id3.restcurrencyclient.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpanishCurrency implements IDto {
    private String unidad;
    private String nombre;
    private String nombreDivisa;
    private double compraDivisa;
    private double ventaDivisa;
    private double compraEfectiva;
    private double ventaEfectiva;
    private double tasaDólar;
    private double otraTasa;
    private String código;
    private String códigoDivisa;
}
