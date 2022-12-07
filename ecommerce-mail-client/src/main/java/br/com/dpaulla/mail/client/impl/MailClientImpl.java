package br.com.dpaulla.mail.client.impl;

import java.util.concurrent.Future;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;

import br.com.dpaulla.mail.client.MailClient;
import br.com.dpaulla.model.response.CadastroResponse;
import br.com.dpaulla.model.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailClientImpl implements MailClient {
	
	private static String url = "http://localhost:9090/";
	private AsyncRestTemplate client = new AsyncRestTemplate();
	
	@Override
	public Future<ResponseEntity<CadastroResponse>> sendCadastroEmail(CadastroResponse alerts) {
		log.info("Fire AlertsHourWrapper request");
		return alertsCadastroResource(alerts, alertsHourSatsUri());
	}
	
	private String alertsHourSatsUri() {
		return url + "mail/cadastro";
	}
	
	private Future<ResponseEntity<CadastroResponse>> alertsCadastroResource(CadastroResponse alerts, String alertsHourSatsUri) {
		log.info("Fire antennaResource request");
		return exchange(alertsHourSatsUri, HttpMethod.POST, alerts);
	}
	
	private Future<ResponseEntity<CadastroResponse>> exchange(String url, HttpMethod method, CadastroResponse alerts) {
		log.info("Fire exchange request");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CadastroResponse> httpEntity = new HttpEntity<CadastroResponse>(alerts, headers);
		log.info("return client request, url:{}, post:{}, http:{}", url, method, httpEntity);
		return client.exchange(url, method, httpEntity, CadastroResponse.class);
	}

	@Override
	public Future<ResponseEntity<OrderResponse>> sendCompraEmail(OrderResponse alerts) {
		log.info("Fire AlertsHourWrapper request");
		return alertsCompraResource(alerts, alertsCompraUri());
	}
	private String alertsCompraUri() {
		return url + "mail/compra";
	}
	
	private Future<ResponseEntity<OrderResponse>> alertsCompraResource(OrderResponse alerts, String alertsHourSatsUri) {
		log.info("Fire antennaResource request");
		return exchangeCompra(alertsHourSatsUri, HttpMethod.POST, alerts);
	}
	
	private Future<ResponseEntity<OrderResponse>> exchangeCompra(String url, HttpMethod method, OrderResponse alerts) {
		log.info("Fire exchange request");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<OrderResponse> httpEntity = new HttpEntity<OrderResponse>(alerts, headers);
		log.info("return client request, url:{}, post:{}, http:{}", url, method, httpEntity);
		return client.exchange(url, method, httpEntity, OrderResponse.class);
	}


	@Override
	public Future<ResponseEntity<OrderResponse>> sendTransacaoStatus(OrderResponse alerts) {
		return alertsTransacaoResource(alerts, alertsTransacaoUri());
	}
	
	private String alertsTransacaoUri() {
		return url + "mail/transacao";
	}
	
	private Future<ResponseEntity<OrderResponse>> alertsTransacaoResource(OrderResponse alerts, String alertsHourSatsUri) {
		log.info("Fire antennaResource request");
		return exchangeTransacao(alertsHourSatsUri, HttpMethod.POST, alerts);
	}
	
	private Future<ResponseEntity<OrderResponse>> exchangeTransacao(String url, HttpMethod method, OrderResponse alerts) {
		log.info("Fire exchange request");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<OrderResponse> httpEntity = new HttpEntity<OrderResponse>(alerts, headers);
		log.info("return client request, url:{}, post:{}, http:{}", url, method, httpEntity);
		return client.exchange(url, method, httpEntity, OrderResponse.class);
	}
	
}
