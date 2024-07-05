package com.id3.restcurrencyclient.controller;

import com.id3.restcurrencyclient.helper.Formatter;
import com.id3.restcurrencyclient.model.EnglishCurrency;
import com.id3.restcurrencyclient.model.SpanishCurrency;
import com.id3.restcurrencyclient.service.IEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CurrencyController {

    private final IEmailService emailService;
    private final Formatter formatter;

    @PostMapping("/en")
    public ResponseEntity<String> getCurrencyEn(@RequestBody List<EnglishCurrency> currencies) throws MessagingException {
        log.info("currencies: " + currencies);
        String emailContent = formatter.formatCurrencyList(currencies);
        emailService.sendSimpleMessage("Successful mail English", emailContent);
        return ResponseEntity.ok("Currencies received and email sent.");
    }

    @PostMapping("/esp")
    public ResponseEntity<String> getCurrencyEsp(@RequestBody List<SpanishCurrency> currencies) throws MessagingException {
        log.info("currencies: " + currencies);
        String emailContent = formatter.formatCurrencyList(currencies);
        emailService.sendSimpleMessage("Successful mail Spanish", emailContent);
        return ResponseEntity.ok("Currencies received and email sent.");
    }
}
