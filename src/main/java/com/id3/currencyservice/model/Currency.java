package com.id3.currencyservice.model;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Currency")
@XmlType(propOrder = {
        "unit",
        "isim",
        "currencyName",
        "forexBuying",
        "forexSelling",
        "banknoteBuying",
        "banknoteSelling",
        "crossRateUSD",
        "crossRateOther"
})
@ToString
public class Currency {

    private int unit;
    private String isim;
    private String currencyName;
    private double forexBuying;
    private double forexSelling;
    private double banknoteBuying;
    private double banknoteSelling;
    private String crossRateUSD;
    private String crossRateOther;
    private String kod;
    private String currencyCode;

    @XmlAttribute(name = "Kod")
    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @XmlAttribute(name = "CurrencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @XmlElement(name = "Unit")
    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    @XmlElement(name = "Isim")
    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    @XmlElement(name = "CurrencyName")
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @XmlElement(name = "ForexBuying")
    public double getForexBuying() {
        return forexBuying;
    }

    public void setForexBuying(double forexBuying) {
        this.forexBuying = forexBuying;
    }

    @XmlElement(name = "ForexSelling")
    public double getForexSelling() {
        return forexSelling;
    }

    public void setForexSelling(double forexSelling) {
        this.forexSelling = forexSelling;
    }

    @XmlElement(name = "BanknoteBuying")
    public double getBanknoteBuying() {
        return banknoteBuying;
    }

    public void setBanknoteBuying(double banknoteBuying) {
        this.banknoteBuying = banknoteBuying;
    }

    @XmlElement(name = "BanknoteSelling")
    public double getBanknoteSelling() {
        return banknoteSelling;
    }

    public void setBanknoteSelling(double banknoteSelling) {
        this.banknoteSelling = banknoteSelling;
    }

    @XmlElement(name = "CrossRateUSD")
    public String getCrossRateUSD() {
        return crossRateUSD;
    }

    public void setCrossRateUSD(String crossRateUSD) {
        this.crossRateUSD = crossRateUSD;
    }

    @XmlElement(name = "CrossRateOther")
    public String getCrossRateOther() {
        return crossRateOther;
    }

    public void setCrossRateOther(String crossRateOther) {
        this.crossRateOther = crossRateOther;
    }
}
