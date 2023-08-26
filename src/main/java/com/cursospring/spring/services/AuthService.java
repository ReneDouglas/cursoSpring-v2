package com.cursospring.spring.services;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cursospring.entities.Cliente;
import com.cursospring.spring.repositories.ClienteRepository;
import com.cursospring.spring.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	private MailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}
		
		String newPass = newPassword();
		cliente.setSenha(passEncoder.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if(opt == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (random.nextInt(26) + 65);
		} else {
			return (char) (random.nextInt(26) + 97);
		}
	}

}
