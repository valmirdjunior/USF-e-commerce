package br.com.dpaulla.service;

import java.util.List;

import br.com.dpaulla.model.Order;
import br.com.dpaulla.model.User;

public interface TransacaoService {

	public Order saveAndReturn(Order transacao);
	public List<Order> findAllByUsuarioId(User user);
	public void updateOrderStatus(Order transacao);
	public Order getOrder(int orderId);
	
}