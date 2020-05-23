package com.wesley.cursomc.services;

import com.wesley.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
}