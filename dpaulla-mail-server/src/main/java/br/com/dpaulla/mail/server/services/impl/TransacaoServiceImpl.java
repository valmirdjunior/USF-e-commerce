package br.com.dpaulla.mail.server.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dpaulla.mail.server.model.dto.Order;
import br.com.dpaulla.mail.server.model.dto.UserBanco;
import br.com.dpaulla.mail.server.repository.TransacaoRepository;
import br.com.dpaulla.mail.server.repository.UserRepository;
import br.com.dpaulla.mail.server.services.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

	@Autowired
	TransacaoRepository transacaoRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void updateOrderStatus(Order transacao) {

		transacaoRepository.saveAndFlush(transacao);

	}

	@Override
	public List<Order> getAllTransactionsPending() {
		List<Order> listAllPending = transacaoRepository.findAllOrderPending();
		
		return listAllPending;
	}

	@Override
	public UserBanco getUser(int idUser) {
		return userRepository.findByUserId(idUser);		
	}
	
}