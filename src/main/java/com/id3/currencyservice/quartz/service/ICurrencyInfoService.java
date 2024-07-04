package com.id3.currencyservice.quartz.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ICurrencyInfoService<T> {

    public List<T> getCurrencyInfo();
    public File writeCsvFromCurrencyInfo(List<T> info);
    public void sendCsvToFtpServer(File file) throws IOException;
}
