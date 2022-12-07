package br.com.dpaulla.mail.server.services.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dpaulla.mail.client.MailClient;
import br.com.dpaulla.mail.server.model.dto.Order;
import br.com.dpaulla.mail.server.model.dto.UserBanco;
import br.com.dpaulla.mail.server.services.PagSeguroApiClient;
import br.com.dpaulla.mail.server.services.TasksService;
import br.com.dpaulla.mail.server.services.TransacaoService;
import br.com.dpaulla.model.OrderData;
import br.com.dpaulla.model.User;
import br.com.dpaulla.model.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TasksServiceImpl implements TasksService{
	
	@Autowired
	TransacaoService transacaoService;

	@Autowired
	PagSeguroApiClient clientePagSeguroAPI;
	
	@Autowired
	MailClient mailClient;
	
	@Override
	public void init() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);
		executor.scheduleWithFixedDelay(taskMonitor, 0, 1*60, TimeUnit.SECONDS);		
	}
	
	private Runnable taskMonitor = () ->{
		executeMonitorAtualizacaoTransacao();
		log.info("Check de Transacoes executado com sucesso, próxima execução em {} segundos.", 60);
	};

	private void executeMonitorAtualizacaoTransacao() {
		List<Order> listTransactionPending = transacaoService.getAllTransactionsPending();
		
		listTransactionPending.stream().forEach(action -> {
			log.info("Transacoes: {}", action.getOrderId());

			if(action.getOrderId() != null) {
				ResponseEntity<String> retorno = clientePagSeguroAPI.getReturnOfTransactionCardCredit(action.getOrderId());

				String retornoString = retorno.getBody();
				int httpCode = retorno.getStatusCodeValue();
				log.info("RetornoString: {}", retornoString);
				log.info("httpCode: {}", httpCode);
				
				JSONObject json = new JSONObject(retornoString);  
				Object statusReturn = json.getJSONObject("status");  
				JSONObject jsonStatus = new JSONObject(statusReturn.toString());
				String description = jsonStatus.getString("description");
				log.info("OK...: {}");

				if (!action.getOrderStatus().equals(description)) {
					action.setOrderStatus(description);
					log.info("OK...: {}");

					int idUser = Integer.parseInt(String.valueOf(action.getUserId()));
					log.info("OK...: {}", idUser);

					UserBanco user = transacaoService.getUser(idUser);
					log.info("userName: {}", user.getNome());

					OrderData order = orderConverter(action);
					log.info("ok, chegou aqui");
					Future<ResponseEntity<OrderResponse>> response = mailClient.sendTransacaoStatus(new OrderResponse(1, order, userConverter(user)));
					log.info("ok, chegou aqui 1");
					ResponseEntity<OrderResponse> mailResponse = null;
					try {
						log.info("ok, chegou aqui 2");
						mailResponse = response.get(10, TimeUnit.SECONDS);
					} catch (InterruptedException | ExecutionException | TimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(mailResponse.getStatusCode().is2xxSuccessful()) {
						log.info("email enviado com sucesso!");
					}else {
						log.info("email falhou!");
					}
					
					transacaoService.updateOrderStatus(action);
				}
				
			}			
			
		});
	}
	
	private OrderData orderConverter(Order orderDTO) {
		OrderData or = new OrderData();
		
		or.setId(orderDTO.getId());
		or.setOrderDate(orderDTO.getOrderDate());
		or.setOrderId(orderDTO.getOrderId());
		or.setOrderPaymantDate(orderDTO.getOrderPaymantDate());
		or.setOrderPaymentAmounts(orderDTO.getOrderPaymentAmounts());
		or.setOrderPaymentPrinceAmount(orderDTO.getOrderPaymentPrinceAmount());
		or.setOrderPaymentType(orderDTO.getOrderPaymentType());
		or.setOrderShippingPrice(orderDTO.getOrderShippingPrice());
		or.setOrderStatus(orderDTO.getOrderStatus());
		or.setOrderTotalPrice(orderDTO.getOrderTotalPrice());
		
		return or;
	}
	
	private User userConverter(UserBanco user) {
		User us = new User();
		us.setUserId(user.getUserId());
		us.setNome(user.getNome());
		us.setUsername(user.getUsername());
		us.setSobrenome(user.getSobrenome());
		
		return us;
	}
	
}
