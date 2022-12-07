package br.com.dpaulla.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.dpaulla.model.Order;
import br.com.dpaulla.model.User;
import br.com.dpaulla.repository.TransacaoRepository;

@Service
public class TransacaoServiceImpl implements TransacaoService {
	@Autowired
	TransacaoRepository transacaoRepository;
	
	@Transactional
	public Order saveAndReturn(Order transacao) {
		transacaoRepository.save(transacao);
		
		return transacao;
	}


	@Override
	public List<Order> findAllByUsuarioId(User user) {
		List<Order> listTransacao = new ArrayList<Order>();
		transacaoRepository.findAll().stream().forEach(action -> {
			if (action.getUserId() == user.getUserId()) {
				listTransacao.add(action);
			}
		});		
		
		return listTransacao;
	}


	@Override
	public void updateOrderStatus(Order transacao) {
		transacaoRepository.saveAndFlush(transacao);
	}


	@Override
	public Order getOrder(int orderId) {
		return transacaoRepository.getOne(orderId);
	}
	
}