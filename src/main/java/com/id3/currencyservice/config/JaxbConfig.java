package com.id3.currencyservice.config;


import com.id3.currencyservice.model.Doviz;
import com.id3.currencyservice.model.DovizListesi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Configuration
@Slf4j
public class JaxbConfig {

    @Bean
    public Unmarshaller dovizListesiUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(DovizListesi.class);
        return context.createUnmarshaller();
    }

    @Bean
    public Unmarshaller anotherApiUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Doviz.class);
        return context.createUnmarshaller();
    }
}
