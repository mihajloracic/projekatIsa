package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Qualifier("getJavaMailSender")
    @Autowired
    JavaMailSender sender;


    @Async
    public void sendNotificaitionAsync(String recipientEmail, String subject, String message) throws MailException, InterruptedException, MessagingException {

        Thread.sleep(1000);
        System.out.println("Slanje emaila...");

        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");

        helper.setTo(recipientEmail);
        helper.setFrom("${spring.mail.username}");
        helper.setSubject(subject);
        helper.setText(message, true);
        sender.send(msg);

        System.out.println("Email poslat!");
    }
}
