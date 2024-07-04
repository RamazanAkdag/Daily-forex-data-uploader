package com.id3.currencyservice.ftpclient;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FtpClientImpl extends FtpClient{
    public void uploadFile(File file, String remotePath) {
        try (FileInputStream fis = new FileInputStream(file)) {
            boolean done = getFtpClient().storeFile(remotePath, fis);
            if (done) {
                System.out.println("The file is uploaded successfully.");
            } else {
                System.out.println("Failed to upload the file.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
