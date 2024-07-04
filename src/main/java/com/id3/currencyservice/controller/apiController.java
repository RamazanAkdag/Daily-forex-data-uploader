package com.id3.currencyservice.controller;

import com.id3.currencyservice.api_client.ICurrencyClientService;

import com.id3.currencyservice.model.Doviz;
import com.id3.currencyservice.model.DovizListesi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class apiController {
    private final ICurrencyClientService currencyClientService;

    @GetMapping
    public DovizListesi deneme() throws URISyntaxException, IOException, InterruptedException, JAXBException {
        return (DovizListesi) currencyClientService.getCurrencyInfo();
    }


}
