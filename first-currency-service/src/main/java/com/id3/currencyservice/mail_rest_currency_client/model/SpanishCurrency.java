package com.id3.currencyservice.mail_rest_currency_client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpanishCurrency implements IDto {
    private int unidad;
    private String nombre;
    private String nombreDivisa;
    private double compraDivisa;
    private double ventaDivisa;
    private double compraEfectiva;
    private double ventaEfectiva;
    private String tasaDolar;
    private String otraTasa;
    private String codigo;
    private String codigoDivisa;
}
