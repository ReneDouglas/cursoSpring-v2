package com.cursospring.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.cursospring.entities.PagamentoComBoleto;
import com.cursospring.entities.PagamentoComCartao;
import com.cursospring.spring.services.MailService;
import com.cursospring.spring.services.MockMailService;
//import com.cursospring.spring.services.SmtpMailService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ApiConfig {
	
	@Bean
	Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				super.configure(objectMapper);
			}
		};
		return builder;
		
	}
	
	@Bean
	public MailService emailService() {
		//return new SmtpMailService();
		return new MockMailService();
	}

}
