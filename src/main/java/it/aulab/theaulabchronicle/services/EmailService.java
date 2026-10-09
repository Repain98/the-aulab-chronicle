package it.aulab.theaulabchronicle.services;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
}