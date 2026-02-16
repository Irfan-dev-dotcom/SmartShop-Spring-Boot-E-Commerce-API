package com.example.SmartShop.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String message) {
        // Dummy email logic (later integrate JavaMailSender)
        System.out.println("Email sent to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
