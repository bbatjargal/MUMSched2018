package mum.swe.mumsched.service;

public interface EmailService {

    void sendMessage(String to,
                           String subject,
                           String text);
}
