package com.id3.restcurrencyclient.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailManager implements IEmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${mail.to}")
    private String to;

    public void sendSimpleMessage(String subject, String text) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true); // İkinci parametre true, içeriğin HTML olduğunu belirtir.
        emailSender.send(message);

    }
}
