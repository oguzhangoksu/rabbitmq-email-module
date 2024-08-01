package com.notificationSystem.services.abstracts;

public interface SendEmailService {

    void sendEmail(String to, String subject, String text);
    
}
