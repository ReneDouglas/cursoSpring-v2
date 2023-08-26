package com.cursospring.spring.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.cursospring.entities.Cliente;
import com.cursospring.entities.Pedido;

public interface MailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage msg);
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	void sendHtmlEmail(MimeMessage msg);
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
