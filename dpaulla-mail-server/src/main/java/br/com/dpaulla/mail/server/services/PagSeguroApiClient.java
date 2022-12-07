package br.com.dpaulla.mail.server.services;

import org.springframework.http.ResponseEntity;

public interface PagSeguroApiClient {
	ResponseEntity<String> getReturnOfTransactionCardCredit(String notificationCode);
}
