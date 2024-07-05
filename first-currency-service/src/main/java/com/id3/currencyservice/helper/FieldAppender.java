package com.id3.currencyservice.helper;

public class FieldAppender {

    public static void appendFieldValue(StringBuilder dataBuilder, Object fieldValue) {
        if (fieldValue != null) {
            dataBuilder.append(fieldValue.toString()).append(",");
        } else {
            dataBuilder.append("").append(",");
        }
    }
}

