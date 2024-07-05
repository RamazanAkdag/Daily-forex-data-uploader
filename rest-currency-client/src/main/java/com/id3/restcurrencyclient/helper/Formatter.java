package com.id3.restcurrencyclient.helper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Formatter {

    public <T> String formatCurrencyList(List<T> currencies) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h1>Currency Information</h1>");
        sb.append("<ul>");
        for (T currency : currencies) {
            sb.append("<li>").append(currency.toString()).append("</li>");
        }
        sb.append("</ul>");
        sb.append("</body></html>");
        return sb.toString();
    }
}

