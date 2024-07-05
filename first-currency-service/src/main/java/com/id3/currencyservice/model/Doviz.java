package com.id3.currencyservice.model;

import com.id3.currencyservice.helper.FieldAppender;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Currency")
@XmlType(propOrder = {
        "birim",
        "isim",
        "dovizAdi",
        "dovizAlis",
        "dovizSatis",
        "efektifAlis",
        "efektifSatis",
        "dolarKuru",
        "digerKur"
})
public class Doviz {

    private int birim;
    private String isim;
    private String dovizAdi;
    private double dovizAlis;
    private double dovizSatis;
    private double efektifAlis;
    private double efektifSatis;
    private String dolarKuru;
    private String digerKur;
    private String kod;
    private String dovizKodu;

    @XmlAttribute(name = "Kod")
    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @XmlAttribute(name = "CurrencyCode")
    public String getDovizKodu() {
        return dovizKodu;
    }

    public void setDovizKodu(String dovizKodu) {
        this.dovizKodu = dovizKodu;
    }

    @XmlElement(name = "Unit")
    public int getBirim() {
        return birim;
    }

    public void setBirim(int birim) {
        this.birim = birim;
    }

    @XmlElement(name = "Isim")
    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    @XmlElement(name = "CurrencyName")
    public String getDovizAdi() {
        return dovizAdi;
    }

    public void setDovizAdi(String dovizAdi) {
        this.dovizAdi = dovizAdi;
    }

    @XmlElement(name = "ForexBuying")
    public double getDovizAlis() {
        return dovizAlis;
    }

    public void setDovizAlis(double dovizAlis) {
        this.dovizAlis = dovizAlis;
    }

    @XmlElement(name = "ForexSelling")
    public double getDovizSatis() {
        return dovizSatis;
    }

    public void setDovizSatis(double dovizSatis) {
        this.dovizSatis = dovizSatis;
    }

    @XmlElement(name = "BanknoteBuying")
    public double getEfektifAlis() {
        return efektifAlis;
    }

    public void setEfektifAlis(double efektifAlis) {
        this.efektifAlis = efektifAlis;
    }

    @XmlElement(name = "BanknoteSelling")
    public double getEfektifSatis() {
        return efektifSatis;
    }

    public void setEfektifSatis(double efektifSatis) {
        this.efektifSatis = efektifSatis;
    }

    @XmlElement(name = "CrossRateUSD")
    public String getDolarKuru() {
        return dolarKuru;
    }

    public void setDolarKuru(String dolarKuru) {
        this.dolarKuru = dolarKuru;
    }

    @XmlElement(name = "CrossRateOther")
    public String getDigerKur() {
        return digerKur;
    }

    public void setDigerKur(String digerKur) {
        this.digerKur = digerKur;
    }

    @Override
    public String toString(){
        StringBuilder dataBuilder = new StringBuilder();
        FieldAppender.appendFieldValue(dataBuilder, birim);
        FieldAppender.appendFieldValue(dataBuilder, isim);
        FieldAppender.appendFieldValue(dataBuilder, dovizAdi);
        FieldAppender.appendFieldValue(dataBuilder, dovizAlis);
        FieldAppender.appendFieldValue(dataBuilder, dovizSatis);
        FieldAppender.appendFieldValue(dataBuilder, efektifAlis);
        FieldAppender.appendFieldValue(dataBuilder, efektifSatis);
        FieldAppender.appendFieldValue(dataBuilder, dolarKuru);
        FieldAppender.appendFieldValue(dataBuilder, digerKur);
        FieldAppender.appendFieldValue(dataBuilder, kod);
        FieldAppender.appendFieldValue(dataBuilder, dovizKodu);

        // Remove the last comma
        if (dataBuilder.length() > 0) {
            dataBuilder.setLength(dataBuilder.length() - 1);
        }

        return dataBuilder.toString();
    }

}

