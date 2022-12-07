package br.com.dpaulla.mail.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.dpaulla.mail.server.model.dto.Order;

@Repository("transacaoRepository")
public interface TransacaoRepository extends JpaRepository<Order, Integer> {
	
	@Query("select o from Order o where o.orderStatus not in ('Paga', 'Cancelado')")
	public List<Order> findAllOrderPending();
	
}