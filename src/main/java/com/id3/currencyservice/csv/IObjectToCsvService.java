package com.id3.currencyservice.csv;

import java.io.File;
import java.util.List;

public interface IObjectToCsvService<T> {
    public File writeToCsv(List<T> tList);
}
