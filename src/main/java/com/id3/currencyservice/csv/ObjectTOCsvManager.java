package com.id3.currencyservice.csv;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import java.util.List;


public class ObjectTOCsvManager<T> implements IObjectToCsvService<T>{

    private final Class<T> type;

    public ObjectTOCsvManager(Class<T> type) {
        this.type = type;
    }

    public File writeToCsv(List<T> tList) {
        File file = null;
        try {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(formatter);
            String filename = "currency_"+formattedDate +".csv";
            file = new File(filename);

            PrintWriter writer = new PrintWriter(file);

            T instance = type.getDeclaredConstructor().newInstance();
            String[] fieldNames = Arrays.stream(instance.getClass().getDeclaredFields())
                    .map(Field::getName)
                    .toArray(String[]::new);

            String header = String.join(",", fieldNames);
            writer.println(header);

            for (T t : tList) {
                writer.println(t.toString());
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return file;
    }

}
