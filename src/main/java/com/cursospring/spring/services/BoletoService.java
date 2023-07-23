package com.cursospring.spring.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.cursospring.entities.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instantePagamento) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePagamento);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}

}
