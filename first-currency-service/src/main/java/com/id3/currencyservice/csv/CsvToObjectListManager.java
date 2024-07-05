package com.id3.currencyservice.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
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
            MappingIterator<T> iter = new CsvMapper().readerWithTypedSchemaFor(clazz).readValues(csvFile);
            list = iter.readAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
