package com.id3.currencyservice.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.id3.currencyservice.model.Doviz;

import java.io.File;
import java.util.List;

public class CsvToObjectListManager<T> implements ICsvToObectListService<T>{

    private Class<T> clazz;

    public CsvToObjectListManager(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> convertCsvToObjectList(File csvFile) {
        List<T> list = null;
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Başlık satırını dikkate alacak şekilde şema oluştur
            MappingIterator<T> iter = mapper.readerFor(clazz).with(schema).readValues(csvFile);
            list = iter.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
