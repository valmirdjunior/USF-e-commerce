package br.com.dpaulla.mail.server.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.dpaulla.mail.server.services.PagSeguroApiClient;


@Service
public class PagSeguroApiClientImpl implements PagSeguroApiClient{
	
	@Value("${app.config.pagseguro.token}")
	private String tokenPag;

	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public ResponseEntity<String> getReturnOfTransactionCardCredit(String notificationCode) {
		
		notificationCode = notificationCode.replace("-", "");
		String uri = "https://sandbox.api.pagseguro.com/digital-payments/v1/transactions/" + notificationCode + "/status";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.setBearerAuth(tokenPag);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	}

}
