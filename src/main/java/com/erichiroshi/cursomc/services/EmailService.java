package com.erichiroshi.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.erichiroshi.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}