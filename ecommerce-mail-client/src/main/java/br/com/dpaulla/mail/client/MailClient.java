package br.com.dpaulla.mail.client;

import java.util.concurrent.Future;

import org.springframework.http.ResponseEntity;

import br.com.dpaulla.model.response.CadastroResponse;
import br.com.dpaulla.model.response.OrderResponse;

public interface MailClient {
	
	Future<ResponseEntity<CadastroResponse>> sendCadastroEmail(CadastroResponse alerts);
	Future<ResponseEntity<OrderResponse>> sendCompraEmail(OrderResponse alerts);
	Future<ResponseEntity<OrderResponse>> sendTransacaoStatus(OrderResponse alerts);
}
