package com.lucascolaco.curso.services;

import org.springframework.mail.SimpleMailMessage;

import com.lucascolaco.curso.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	

}
