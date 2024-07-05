package com.id3.currencyservice.config;

import com.id3.currencyservice.csv.CsvToObjectListManager;
import com.id3.currencyservice.csv.ICsvToObectListService;
import com.id3.currencyservice.csv.IObjectToCsvService;
import com.id3.currencyservice.csv.ObjectTOCsvManager;
import com.id3.currencyservice.model.Doviz;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvConfig {

    @Bean
    public IObjectToCsvService<Doviz> dovizObjectToCsvService(){
        return new ObjectTOCsvManager<>(Doviz.class);
    }

    @Bean
    public ICsvToObectListService<Doviz> dovizCsvToObectListService(){return new CsvToObjectListManager<>(Doviz.class);}

}
