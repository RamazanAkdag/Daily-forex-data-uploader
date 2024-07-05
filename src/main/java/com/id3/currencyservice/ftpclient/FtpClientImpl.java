package com.id3.currencyservice.ftpclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@Slf4j
public class FtpClientImpl extends FtpClient{
    public void uploadFile(File file, String remotePath) {
        try (FileInputStream fis = new FileInputStream(file)) {
            boolean done = getFtpClient().storeFile(remotePath, fis);
            if (done) {
                log.info("The file is uploaded successfully.");
            } else {
                log.error("Failed to upload the file.");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
