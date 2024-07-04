package com.id3.currencyservice.config;

import com.id3.currencyservice.model.Tarih_Date;
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
    public Unmarshaller tarihDateUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Tarih_Date.class);
        return context.createUnmarshaller();
    }
}
