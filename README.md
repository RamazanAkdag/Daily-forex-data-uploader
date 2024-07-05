# Daily Forex Data Uploader

This project, 'Daily Forex Data Uploader,' is designed to automate the process of fetching daily forex exchange rates from the Central Bank of the Republic of Turkey (TCMB) API, converting the data into class objects using JAXB, writing these objects to a CSV file, and uploading the CSV file to an FTP server. The task is scheduled to run daily at the close of the stock market using Quartz Scheduler.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Code Examples](#code-examples)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Features

- Automated daily fetch of forex exchange rates
- Conversion of XML data to class objects using JAXB
- Writing data to a CSV file
- Uploading the CSV file to an FTP server
- Scheduling the task with Quartz Scheduler

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/RamazanAkdag/Daily-forex-data-uploader.git
    ```
2. Navigate to the project directory:
    ```sh
    cd Daily-forex-data-uploader
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

## Configuration

1. Configure the `application.properties` file located in `src/main/resources` with your FTP server details and TCMB API endpoint:
    ```properties
    ftp.server=ftp.example.com
    ftp.port=21
    ftp.username=your-username
    ftp.password=your-password
    tcmb.api.url=https://www.tcmb.gov.tr/kurlar/today.xml
    ```

2. Set up the Quartz Scheduler configuration in the `application.properties` file:
    ```properties
    quartz.scheduler.cron.expression=0 0 17 * * ?
    ```

## Usage

1. Run the application:
    ```sh
    mvn spring-boot:run
    ```

2. The scheduled task will fetch the latest exchange rates at the configured time, convert the data, write it to a CSV file, and upload the file to the FTP server.

## Project Structure

- `src/main/java`: Contains the main application code
- `src/main/resources`: Contains the configuration files
- `src/test/java`: Contains the test cases

## Code Examples

### Quartz Scheduler Job

The main job that gets triggered by Quartz Scheduler is defined as follows:

```java
package com.example.forex.scheduler;

import com.example.forex.service.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ForexJob {

    @Autowired
    private ForexService forexService;

    @Scheduled(cron = "${quartz.scheduler.cron.expression}")
    public void fetchForexData() {
        forexService.fetchAndProcessForexData();
    }
}
```
### Forex Service

The `ForexService` is responsible for fetching the data from the TCMB API, processing it, and triggering the writing and uploading of the CSV file.

```java
package com.example.forex.service;

import com.example.forex.model.ForexData;
import com.example.forex.util.CsvWriter;
import com.example.forex.util.FtpUploader;
import com.example.forex.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForexService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private XmlParser xmlParser;

    @Autowired
    private CsvWriter csvWriter;

    @Autowired
    private FtpUploader ftpUploader;

    public void fetchAndProcessForexData() {
        String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
        String xmlData = restTemplate.getForObject(url, String.class);

        ForexData forexData = xmlParser.parseXmlToForexData(xmlData);
        String csvFilePath = csvWriter.writeDataToCsv(forexData);

        ftpUploader.uploadFileToFTP(csvFilePath);
    }
}
```
### XML Parser

The `XmlParser` is responsible for converting the XML data fetched from the TCMB API into `ForexData` objects using JAXB.

```java
package com.example.forex.util;

import com.example.forex.model.ForexData;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Component
public class XmlParser {

    public ForexData parseXmlToForexData(String xmlData) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ForexData.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (ForexData) unmarshaller.unmarshal(new StringReader(xmlData));
        } catch (JAXBException e) {
            throw new RuntimeException("Error parsing XML data", e);
        }
    }
}
```
### CSV Writer

The `CsvWriter` handles writing the `ForexData` object to a CSV file.

```java
package com.example.forex.util;

import com.example.forex.model.ForexData;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CsvWriter {

    public String writeDataToCsv(ForexData forexData) {
        String filePath = "forex-data.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Currency, Rate\n");
            forexData.getRates().forEach(rate -> {
                try {
                    writer.append(rate.getCurrency())
                          .append(", ")
                          .append(String.valueOf(rate.getRate()))
                          .append("\n");
                } catch (IOException e) {
                    throw new RuntimeException("Error writing to CSV file", e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Error creating CSV file", e);
        }
        return filePath;
    }
}
```

### FTP Uploader

The `FtpUploader` uploads the CSV file to the specified FTP server.

```java
package com.example.forex.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FtpUploader {

    @Value("${ftp.server}")
    private String server;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String user;

    @Value("${ftp.password}")
    private String pass;

    public void uploadFileToFTP(String filePath) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            try (FileInputStream inputStream = new FileInputStream(filePath)) {
                String remoteFile = "forex-data.csv";
                boolean done = ftpClient.storeFile(remoteFile, inputStream);
                if (done) {
                    System.out.println("The file is uploaded successfully.");
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error uploading file to FTP server", ex);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
```
### Forex Data Model

The `ForexData` class represents the structure of the XML data fetched from the TCMB API.

```java
package com.example.forex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Tarih_Date")
public class ForexData {

    private List<CurrencyRate> rates;

    @XmlElement(name = "Currency")
    public List<CurrencyRate> getRates() {
        return rates;
    }

    public void setRates(List<CurrencyRate> rates) {
        this.rates = rates;
    }
}
```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Central Bank of the Republic of Turkey (TCMB) for providing the exchange rate API
- Quartz Scheduler for task scheduling
- Apache Commons Net library for FTP client functionality



