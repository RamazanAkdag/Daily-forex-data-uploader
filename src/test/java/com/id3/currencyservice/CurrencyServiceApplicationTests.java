package com.id3.currencyservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CurrencyServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void givenRemoteFile_whenDownloading_thenItIsOnTheLocalFilesystem() throws IOException {
        String ftpUrl = String.format(
                "ftp://ramo:root@localhost:21/1.gün.docx");

        URLConnection urlConnection = new URL(ftpUrl).openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        Files.copy(inputStream, new File("ramo.docx").toPath());
        inputStream.close();

        assertThat(new File("ramo.docx")).exists();

        //new File("1.gün.docx").delete(); // cleanup
    }
}
