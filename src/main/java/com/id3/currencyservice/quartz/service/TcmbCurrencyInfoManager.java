package com.id3.currencyservice.quartz.service;

import com.id3.currencyservice.api_client.tcmb.ITcmbCurrencyClientService;
import com.id3.currencyservice.csv.IObjectToCsvService;
import com.id3.currencyservice.ftpclient.FtpClient;
import com.id3.currencyservice.model.Doviz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TcmbCurrencyInfoManager implements ICurrencyInfoService<Doviz> {

    private final ITcmbCurrencyClientService currencyClientService;
    private final IObjectToCsvService<Doviz> objectToCsvService;
    private final FtpClient ftpClient;


    @Override
    public List<Doviz> getCurrencyInfo() {
        var info = currencyClientService.getCurrencyInfo();
        log.info("Currency info : " + info);
        return info;
    }

    @Override
    public File writeCsvFromCurrencyInfo(List<Doviz> info) {
        var csvFile = objectToCsvService.writeToCsv(info);
        log.info("Csv file : " + csvFile);
        return csvFile;
    }

    @Override
    public void sendCsvToFtpServer(File file) throws IOException {
        ftpClient.open();
        ftpClient.uploadFile(file, file.getName());
        log.info(file.getName() + " named file uploaded to ftp server");
        ftpClient.close();

        //delete file after operation
        file.delete();

    }
}
