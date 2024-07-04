package com.id3.currencyservice.model;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Tarih_Date")
@ToString
public class DovizListesi {

    private String tarih;
    private String tarihIngilizce;
    private String bultenNo;
    private List<Doviz> dovizler;

    @XmlAttribute(name = "Tarih")
    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    @XmlAttribute(name = "Date")
    public String getTarihIngilizce() {
        return tarihIngilizce;
    }

    public void setTarihIngilizce(String tarihIngilizce) {
        this.tarihIngilizce = tarihIngilizce;
    }

    @XmlAttribute(name = "Bulten_No")
    public String getBultenNo() {
        return bultenNo;
    }

    public void setBultenNo(String bultenNo) {
        this.bultenNo = bultenNo;
    }

    @XmlElement(name = "Currency")
    public List<Doviz> getDovizler() {
        return dovizler;
    }

    public void setDovizler(List<Doviz> dovizler) {
        this.dovizler = dovizler;
    }
}
