package com.id3.currencyservice.quartz.service;

import com.github.drapostolos.rdp4j.DirectoryPoller;
import com.github.drapostolos.rdp4j.spi.PolledDirectory;
import com.id3.currencyservice.rdp_listener.FtpDirectory;
import com.id3.currencyservice.rdp_listener.FtpListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class FtpListenerManager implements IFtpListenerService{
    private final PolledDirectory polledDirectory;
    private final FtpListener ftpListener;

    @Override
    public void startListening() throws InterruptedException {

        DirectoryPoller dp = DirectoryPoller.newBuilder()
                .addPolledDirectory(polledDirectory)
                .addListener(ftpListener)
                .enableFileAddedEventsForInitialContent()
                .setPollingInterval(10, TimeUnit.SECONDS)
                .start();

        TimeUnit.HOURS.sleep(2);

        dp.stop();
    }
}
