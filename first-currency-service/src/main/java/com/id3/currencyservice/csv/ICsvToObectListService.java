package com.id3.currencyservice.csv;

import java.io.File;
import java.util.List;

public interface ICsvToObectListService<T> {

    public List<T> convertCsvToObjectList(File csvFile);

}
