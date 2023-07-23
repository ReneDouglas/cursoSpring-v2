package com.cursospring.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cursospring.entities.Cliente;
import com.cursospring.entities.enums.TipoCliente;
import com.cursospring.spring.dto.ClienteNewDTO;
import com.cursospring.spring.repositories.ClienteRepository;
import com.cursospring.spring.resources.exceptions.FieldMessage;
import com.cursospring.spring.services.validation.utils.DocumentUtil;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		if(value.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !DocumentUtil.isValidCPF(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if(value.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !DocumentUtil.isValidCNPJ(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(value.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		for(FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	};

}
