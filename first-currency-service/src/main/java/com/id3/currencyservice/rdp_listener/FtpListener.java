package com.id3.currencyservice.rdp_listener;

import com.github.drapostolos.rdp4j.*;
import com.id3.currencyservice.csv.ICsvToObectListService;
import com.id3.currencyservice.ftpclient.FtpClient;
import com.id3.currencyservice.mail_rest_currency_client.IRestCurrencyClient;
import com.id3.currencyservice.mail_rest_currency_client.model.EnglishCurrency;
import com.id3.currencyservice.mail_rest_currency_client.model.SpanishCurrency;
import com.id3.currencyservice.mapping.IDovizMapper;
import com.id3.currencyservice.model.Doviz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class FtpListener implements DirectoryListener, IoErrorListener, InitialContentListener {

    private final IRestCurrencyClient<EnglishCurrency> enRestCurrencyClient;
    private final IRestCurrencyClient<SpanishCurrency> espRestCurrencyClient;
    private final IDovizMapper<EnglishCurrency> englishCurrencyIDovizMapper;
    private final IDovizMapper<SpanishCurrency> spanishCurrencyIDovizMapper;
    private final FtpClient ftpClient;
    private final ICsvToObectListService<Doviz> csvToObectListService;



    @Override
    public void fileAdded(FileAddedEvent event) {
        log.info("Added: " + event.getFileElement());
        log.info("---rest api Operation started---");
        try {
            ftpClient.open();
            File localFile = new File("file.csv");
            ftpClient.downloadFile(event.getFileElement().getName(), localFile);
            List<Doviz> dovizList = csvToObectListService.convertCsvToObjectList(localFile);
            log.debug("");

            var englishList = englishCurrencyIDovizMapper.map(dovizList);
            var spanishList = spanishCurrencyIDovizMapper.map(dovizList);

            enRestCurrencyClient.sendCurrencyInfo(englishList);
            espRestCurrencyClient.sendCurrencyInfo(spanishList);

            log.info("rest api operation ended");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fileRemoved(FileRemovedEvent event) {
        log.info("Removed: " + event.getFileElement());
    }

    @Override
    public void fileModified(FileModifiedEvent event) {
        log.info("Modified: " + event.getFileElement());
    }

    @Override
    public void ioErrorCeased(IoErrorCeasedEvent event) {
        log.info("I/O error ceased.");
    }

    @Override
    public void ioErrorRaised(IoErrorRaisedEvent event) {
        System.out.println("I/O error raised!");
        event.getIoException().printStackTrace();
    }

    @Override
    public void initialContent(InitialContentEvent event) {
        log.debug("initial Content: ^");
    }


}