package com.id3.currencyservice;

import com.github.drapostolos.rdp4j.DirectoryPoller;
import com.github.drapostolos.rdp4j.spi.PolledDirectory;
import com.id3.currencyservice.ftpclient.FtpClientImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class CurrencyServiceApplication {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        SpringApplication.run(CurrencyServiceApplication.class, args);

    }

}
