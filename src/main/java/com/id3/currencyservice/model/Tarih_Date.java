package com.id3.currencyservice.model;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Tarih_Date")
@ToString
public class Tarih_Date {

    private String tarih;
    private String date;
    private String bultenNo;
    private List<Currency> currencies;

    @XmlAttribute(name = "Tarih")
    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    @XmlAttribute(name = "Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlAttribute(name = "Bulten_No")
    public String getBultenNo() {
        return bultenNo;
    }

    public void setBultenNo(String bultenNo) {
        this.bultenNo = bultenNo;
    }

    @XmlElement(name = "Currency")
    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
