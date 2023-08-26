package com.cursospring.spring.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cursospring.entities.Cliente;
import com.cursospring.entities.Pedido;

public abstract class AbstractEmailService implements MailService{

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine template;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage msg = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(msg);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(pedido.getCliente().getEmail());
		msg.setFrom(sender);
		msg.setSubject("Pedido confirmado! código: " + pedido.getId());
		msg.setSentDate(new Date(System.currentTimeMillis()));
		msg.setText(pedido.toString());
		return msg;
	}
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context ctx = new Context();
		ctx.setVariable("pedido", obj);
		return template.process("email/confirmacaoPedido", ctx);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(pedido);
			sendHtmlEmail(mm);
		} catch (Exception e) {
			sendOrderConfirmationEmail(pedido);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
		MimeMessage mm = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mm, true);
		helper.setTo(pedido.getCliente().getEmail());
		helper.setFrom(sender);
		helper.setSubject("Pedido confirmado! código: " + pedido.getId());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplatePedido(pedido), true);
		return mm;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage msg = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(msg);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(cliente.getEmail());
		msg.setFrom(sender);
		msg.setSubject("Solicitação de nova senha. ");
		msg.setSentDate(new Date(System.currentTimeMillis()));
		msg.setText("Nova senha: " + newPass);
		return msg;
	}

}
