package com.id3.restcurrencyclient.service;

import jakarta.mail.MessagingException;

public interface IEmailService {

    public void sendSimpleMessage(
             String subject, String text) throws MessagingException;

}
