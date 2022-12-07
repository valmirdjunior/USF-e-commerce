package br.com.dpaulla.mail.server.services;

import java.util.List;

import br.com.dpaulla.mail.server.model.dto.Order;
import br.com.dpaulla.mail.server.model.dto.UserBanco;

public interface TransacaoService {

	public void updateOrderStatus(Order transacao);
	public List<Order> getAllTransactionsPending();
	public UserBanco getUser(int idUser);
	
}
