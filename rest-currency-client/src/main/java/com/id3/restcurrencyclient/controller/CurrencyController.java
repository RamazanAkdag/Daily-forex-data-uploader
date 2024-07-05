package com.id3.restcurrencyclient.controller;

import com.id3.restcurrencyclient.model.EnglishCurrency;
import com.id3.restcurrencyclient.model.SpanishCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class CurrencyController {

    @PostMapping("/en")
    public ResponseEntity<String> getCurrencyEn(@RequestBody List<EnglishCurrency> currencies){
        log.info("currencies : " + currencies);
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/esp")
    public ResponseEntity<String> getCurrencyEsp(@RequestBody List<SpanishCurrency> currencies){

        return ResponseEntity.ok("hello");
    }
}
