package com.id3.currencyservice;

import com.id3.currencyservice.api_client.tcmb.TcmbCurrencyClientManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

@SpringBootApplication
public class CurrencyServiceApplication {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        SpringApplication.run(CurrencyServiceApplication.class, args);
    }

}
