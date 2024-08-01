package com.haydikodlayalim.services.abstracts;

public interface SendEmailService {

    void sendEmail(String to, String subject, String text);
    
}
