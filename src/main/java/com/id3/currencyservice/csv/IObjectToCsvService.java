package com.id3.currencyservice.csv;

import java.util.List;

public interface IObjectToCsvService<T> {
    public void writeToCsv(List<T> tList);
}
